package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class BeerDtoTest extends BaseTest {

	final String fromJSON =
			"{\"beerName\":\"California Honey\","
					+ "\"beerStyle\":\"ALE\","
					+ "\"upc\":121324343434,\"price\":12.99,\"createdDate\":\"2011-12-03T10:15:30+0100\","
					+ "\"lastUpdatedDate\":\"2011-12-03T10:15:30+0100\","
					+ "\"beerId\":\"2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35\"}";

	final String toJSON =
			"{\"beerName\":\"California Honey\","
					+ "\"beerStyle\":\"ALE\","
					+ "\"upc\":121324343434,\"price\":\"12.99\",\"createdDate\":\"2011-12-03T10:15:30+0100\","
					+ "\"lastUpdatedDate\":\"2011-12-03T10:15:30+0100\","
					+ "\"beerId\":\"2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35\"}";

	@Test
	void testSerializationToJSON() throws JsonProcessingException {
		BeerDto beerDto = getDto();

		String jsonString = objectMapper.writeValueAsString(beerDto);
		log.info(jsonString);
		assertThat(jsonString).isEqualTo(toJSON);
	}

	@Test
	void testDeserializationFromJson() throws JsonProcessingException {

		BeerDto dto = objectMapper.readValue(fromJSON, BeerDto.class);
		log.info(dto.toString());
		assertThat(dto.getId()).isEqualTo(UUID.fromString("2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35"));
		assertThat(dto.getBeerName()).isEqualTo("California Honey");
	}
}
