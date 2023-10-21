package guru.springframework.sfgbrewery.web.mapper;

import guru.springframework.sfgbrewery.domain.Beer;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = { DateMapper.class })
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);
	Beer beerDtoToBeer(BeerDto beerDto);
}
