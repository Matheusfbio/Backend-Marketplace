package com.mfdesenvolvimento.marketplace.Customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerRepository customerRepository;

	// Create
	@PostMapping("/")
	public ResponseEntity<Object> createCustomer(@RequestBody CustomerModel customerModel) {
		CustomerModel createdCustomer = customerRepository.save(customerModel);
		return new ResponseEntity<Object>(createdCustomer, HttpStatus.CREATED);
	}

	// Read
	@GetMapping("/")
	public ResponseEntity<Object> getAllCustomers() {
		List<CustomerModel> customers = customerRepository.findAll();
		return new ResponseEntity<Object>(customers, HttpStatus.OK);
	}
}
