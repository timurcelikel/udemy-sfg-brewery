package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("snake")
@Slf4j
class SnakeTest extends BaseTest {

	@Test
	void testSnake() throws JsonProcessingException {
		BeerDto dto = getDto();

		String json = objectMapper.writeValueAsString(dto);
		log.info(json);
	}
}
