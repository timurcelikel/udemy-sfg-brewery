package guru.springframework.sfgbrewery.services;

import guru.springframework.sfgbrewery.web.model.CustomerDto;

import java.util.UUID;

public interface CustomerService {

	CustomerDto getCustomerById(UUID customerId);
}
