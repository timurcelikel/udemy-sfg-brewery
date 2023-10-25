package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import guru.springframework.sfgbrewery.web.model.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@JsonTest
class BaseTest {

	@Autowired
	ObjectMapper objectMapper;

	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("BeerName")
				.beerStyle(BeerStyle.ALE)
				.id(UUID.randomUUID())
				.createdDate(OffsetDateTime.now())
				.lastUpdatedDate(OffsetDateTime.now())
				.price(new BigDecimal("12.99"))
				.upc(121324343434L)
				.build();
	}

}
