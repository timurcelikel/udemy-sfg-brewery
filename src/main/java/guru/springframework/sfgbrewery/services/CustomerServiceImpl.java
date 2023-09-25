package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.BeerDto;
import guru.springframework.sfgbrewery.web.model.CustomerDto;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Override
	public CustomerDto getCustomerById(final UUID customerId) {

		return CustomerDto.builder().id(UUID.randomUUID()).name("John Steinbeck").build();
	}
}
