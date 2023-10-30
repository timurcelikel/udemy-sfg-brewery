package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("kebab")
@Slf4j
class KebabTest extends BaseTest {

	@Test
	void kebabTest() throws JsonProcessingException {
		BeerDto dto = getDto();

		String json = objectMapper.writeValueAsString(dto);
		log.info(json);
		final String expectedJSON =
				"{\"beer-name\":\"California Honey\","
						+ "\"beer-style\":\"ALE\","
						+ "\"upc\":121324343434,\"price\":\"12.99\",\"created-date\":\"2011-12-03T10:15:30+0100\","
						+ "\"last-updated-date\":\"2011-12-03T10:15:30+0100\","
						+ "\"my-local-date\":\"20111203\","
						+ "\"beerId\":\"2d6dec31-6fac-4b00-aa7d-7c5fee3ccc35\"}";
		assertThat(json).isEqualTo(expectedJSON);
	}
}
