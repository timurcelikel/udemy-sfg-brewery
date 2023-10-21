package guru.springframework.sfgbrewery.web.mapper;

import guru.springframework.sfgbrewery.domain.Customer;
import guru.springframework.sfgbrewery.web.model.CustomerDto;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {

	CustomerDto customerToCustomerDto(Customer customer);
	Customer customerDtoToCustomer(CustomerDto customerDto);
}
