package com.clickart.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickart.exception.CustomerException;
import com.clickart.model.Customer;
import com.clickart.model.Order;
import com.clickart.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		Customer c=customerService.registerCustomer(customer);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/view/{id}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("id") int customerId) throws CustomerException {
		Customer c=customerService.viewCustomer(customerId);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer, @RequestParam(required = false) String key ) throws CustomerException {
		Customer c=customerService.updateCustomer(customer, key);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("id") int customerId, @RequestParam(required = false) String key) throws CustomerException {
		Customer c=customerService.deleteCustomer(customerId, key);
		return new ResponseEntity<Customer>(c,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/viewOrders/{id}")
	public ResponseEntity<List<Order>> viewOrders(@PathVariable("id") int customerId, @RequestParam(required = false) String key) throws CustomerException {
		List<Order>list=customerService.viewOrders(customerId, key);
		return new ResponseEntity<List<Order>>(list,HttpStatus.ACCEPTED);
	}

}
