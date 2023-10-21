package guru.springframework.sfgbrewery.web.controller;

import guru.springframework.sfgbrewery.services.BeerService;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
@Slf4j
@Validated
public class BeerController {

	private final BeerService beerService;

	public BeerController(final BeerService beerService) {

		this.beerService = beerService;
	}

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@NotNull @PathVariable("beerId") UUID beerId) {
		final BeerDto beerDto = beerService.getBeerById(beerId);
		log.info("Beer GET: " + beerDto);
		return new ResponseEntity<>(beerDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@Valid @RequestBody BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);
		log.info("Beer POST3: " + savedDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		log.info("Additional Change: " + savedDto);
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{beerId}")
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("beerId") UUID beerId,
			@Valid @RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);
		log.info("Beer UPDATE: " + beerDto.getBeerName());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {

		beerService.deleteById(beerId);
		log.info("Beer DELETE: " + beerId);
	}
}
