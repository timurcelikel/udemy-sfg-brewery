package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
class BeerDtoTest extends BaseTest {

	@Test
	void testSerializationToJSON() throws JsonProcessingException {
		BeerDto beerDto = getDto();

		String jsonString = objectMapper.writeValueAsString(beerDto);
		log.info(jsonString);
	}

	@Test
	void testDeserializationFromJson() throws JsonProcessingException {

		String json =
				"{\"id\":\"2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35\",\"beerName\":\"BeerName\",\"beerStyle\":\"ALE\","
						+ "\"upc\":121324343434,\"price\":12.99,\"createdDate\":\"2023-10-24T20:17:47.835497-07:00\","
						+ "\"lastUpdatedDate\":\"2023-10-24T20:17:47.835522-07:00\"}";

		BeerDto dto = objectMapper.readValue(json, BeerDto.class);
		log.info(dto.toString());
	}
}
