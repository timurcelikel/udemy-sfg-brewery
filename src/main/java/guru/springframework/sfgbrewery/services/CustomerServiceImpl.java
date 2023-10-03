package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(final UUID customerId) {

		return CustomerDto.builder().id(UUID.randomUUID()).name("John Steinbeck").build();
	}

	@Override
	public CustomerDto saveNewCustomer(final CustomerDto customerDto) {

		return CustomerDto.builder().id(UUID.randomUUID()).build();
	}

	@Override
	public void updateCustomer(final UUID customerId, final CustomerDto customerDto) {
		// todo: add the customer update
	}

	@Override
	public void deleteById(final UUID customerId) {

		log.debug("Deleting a customer...");
	}
}
