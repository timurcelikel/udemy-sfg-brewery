package guru.springframework.sfgbrewery.web.controller;

import guru.springframework.sfgbrewery.services.CustomerService;
import guru.springframework.sfgbrewery.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RequestMapping("/api/v1/customer")
@RestController
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(final CustomerService customerService) {

		this.customerService = customerService;
	}

	@GetMapping("/{customerId}")
	ResponseEntity<CustomerDto> getCustomerById(@PathVariable UUID customerId) {
		CustomerDto customerDto = customerService.getCustomerById(customerId);
		log.info("Customer GET: " + customerDto);
		return new ResponseEntity<>(customerDto, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<HttpHeaders> handlePost(@RequestBody CustomerDto customerDto) {

		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);
		log.info("Customer POST: " + savedDto);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Location", "/api/v1/customer" + savedDto.getId().toString());
		return new ResponseEntity<>(headers, HttpStatus.CREATED);
	}

	@PutMapping("/{customerId}")
	public ResponseEntity<HttpHeaders> handleUpdate(@PathVariable("customerId") UUID customerId,
			@RequestBody CustomerDto customerDto) {
		customerService.updateCustomer(customerId, customerDto);
		log.info("Customer UPDATE: " + customerDto);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteById(@PathVariable("customerId") UUID customerId) {
		log.info("Customer DELETE: " + customerId);
		customerService.deleteById(customerId);
	}

}
