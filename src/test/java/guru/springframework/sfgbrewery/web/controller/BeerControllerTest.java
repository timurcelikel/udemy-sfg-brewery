package guru.springframework.sfgbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgbrewery.services.BeerService;
import guru.springframework.sfgbrewery.web.model.BeerDto;
import guru.springframework.sfgbrewery.web.model.BeerStyle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BeerController.class)
class BeerControllerTest {

	@MockBean
	BeerService beerService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	BeerDto validBeer;

	@BeforeEach
	public void setUp() {
		validBeer = BeerDto.builder().id(UUID.randomUUID())
				.beerName("Beer1")
				.beerStyle(BeerStyle.ALE)
				.upc(23242342532L)
				.build();
	}

	@Test
	void getBeer() throws Exception {
		final UUID id = validBeer.getId();
		given(beerService.getBeerById(id)).willReturn(validBeer);
		mockMvc.perform(get("/api/v1/beer/" + id))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(id.toString())))
				.andExpect(jsonPath("$.beerName", is("Beer1")));
	}

	@Test
	void handlePost() throws Exception {
		//given
		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		BeerDto savedDto = BeerDto.builder().id(UUID.randomUUID()).beerName("New Beer").build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		given(beerService.saveNewBeer(any())).willReturn(savedDto);
		mockMvc.perform(post("/api/v1/beer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	void handleUpdate() throws Exception {
		//given
		BeerDto beerDto = validBeer;
		validBeer.setId(null);
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		//when
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID())
						.contentType(MediaType.APPLICATION_JSON)
						.content(beerDtoJson))
				.andExpect(status().isNoContent());
		then(beerService).should().updateBeer(any(), any());
	}
}
