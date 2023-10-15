package guru.springframework.sfgbrewery.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.sfgbrewery.services.CustomerService;
import guru.springframework.sfgbrewery.web.model.CustomerDto;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

	@MockBean
	CustomerService customerService;

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	CustomerDto validCustomer;

	@BeforeEach
	public void setUp() {

		validCustomer = CustomerDto.builder().id(UUID.randomUUID())
				.name("John Steinbeck")
				.build();
	}

	@Test
	void getCustomer() throws Exception {

		given(customerService.getCustomerById(any(UUID.class))).willReturn(validCustomer);

		mockMvc.perform(get("/api/v1/customer/" + validCustomer.getId().toString()).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", is(validCustomer.getId().toString())))
				.andExpect(jsonPath("$.name", is("John Steinbeck")));
	}

	@Test
	void handlePost() throws Exception {
		//given
		CustomerDto customerDto = validCustomer;
		customerDto.setId(null);
		CustomerDto savedDto = CustomerDto.builder().id(UUID.randomUUID()).name("John Steinbeck").build();
		String customerDtoJson = objectMapper.writeValueAsString(customerDto);

		given(customerService.saveNewCustomer(any())).willReturn(savedDto);

		mockMvc.perform(post("/api/v1/customer")
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerDtoJson))
				.andExpect(status().isCreated());
	}

	@Test
	void handleUpdate() throws Exception {
		//given
		CustomerDto customerDto = validCustomer;
		String customerDtoJson = objectMapper.writeValueAsString(customerDto);

		//when
		mockMvc.perform(put("/api/v1/customer/" + validCustomer.getId())
						.contentType(MediaType.APPLICATION_JSON)
						.content(customerDtoJson))
				.andExpect(status().isNoContent());

		then(customerService).should().updateCustomer(any(), any());
	}
}
