package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.BeerDto;

import java.util.UUID;

public interface BeerService {

	BeerDto getBeerById(UUID beerId);
}
