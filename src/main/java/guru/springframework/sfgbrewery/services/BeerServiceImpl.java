package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.BeerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

	@Service
	public class BeerServiceImpl implements BeerService{

		@Override
		public BeerDto getBeerById(final UUID beerId) {

			return BeerDto.builder().id(UUID.randomUUID()).beerName("Galaxy Cat").beerStyle("Pale Ale").build();
		}
	}
