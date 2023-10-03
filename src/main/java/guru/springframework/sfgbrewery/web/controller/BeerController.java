package guru.springframework.sfgbrewery.web.controller;

import guru.springframework.sfgbrewery.services.BeerService;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

	private final BeerService beerService;

	public BeerController(final BeerService beerService) {

		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@RequestBody BeerDto beerDto) {
		BeerDto savedDto = beerService.saveNewBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		return new ResponseEntity<HttpHeaders>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("beerId") UUID beerId, @RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<HttpHeaders>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}
}
