package guru.springframework.sfgbrewery.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("kebab")
@Slf4j
class KebabTest extends BaseTest {

	@Test
	void kebabTest() throws JsonProcessingException {
		BeerDto dto = getDto();

		String json = objectMapper.writeValueAsString(dto);
		log.info(json);
	}
}
