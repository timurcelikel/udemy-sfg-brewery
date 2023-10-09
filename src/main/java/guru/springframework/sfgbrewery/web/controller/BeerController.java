package guru.springframework.sfgbrewery.web.controller;

import guru.springframework.sfgbrewery.services.BeerService;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
@Slf4j
public class BeerController {

	private final BeerService beerService;

	public BeerController(final BeerService beerService) {

		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		final BeerDto beerDto = beerService.getBeerById(beerId);
		log.info(String.valueOf(beerDto));
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("beerId") UUID beerId,
			@RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {

		beerService.deleteById(beerId);
	}
}
