package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import guru.springframework.sfgbrewery.web.model.BeerStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@JsonTest
class BaseTest {

	@Autowired
	ObjectMapper objectMapper;

	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("California Honey")
				.beerStyle(BeerStyle.ALE)
				.id(UUID.fromString("2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35"))
				.createdDate(OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
				.lastUpdatedDate(
						OffsetDateTime.parse("2011-12-03T10:15:30+01:00", DateTimeFormatter.ISO_OFFSET_DATE_TIME))
				.price(new BigDecimal("12.99"))
				.upc(121324343434L)
				.build();
	}
}
