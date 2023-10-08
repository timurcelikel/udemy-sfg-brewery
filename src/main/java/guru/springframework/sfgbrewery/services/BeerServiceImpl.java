package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.BeerDto;
import guru.springframework.sfgbrewery.web.model.BeerStyle;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(final UUID beerId) {

		return BeerDto.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyle(BeerStyle.ALE).build();
	}

	@Override
	public BeerDto saveNewBeer(final BeerDto beerDto) {

		return BeerDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateBeer(final UUID beerId, final BeerDto beerDto) {
		// todo: add the beer update
	}

	@Override
	public void deleteById(final UUID beerId) {
		log.debug("Deleting a beer...");
	}
}
