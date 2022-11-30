package com.clickart.service;

import java.util.List;  
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.CustomerException;
import com.clickart.model.Cart;
import com.clickart.model.CurrentUserSession;
import com.clickart.model.Customer;
import com.clickart.model.Order;
import com.clickart.repository.CartRepo;
import com.clickart.repository.CustomerRepo;
import com.clickart.repository.UserSessionRepo;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@Autowired
	private CartRepo cartRepo;

	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Customer saved=customerRepo.save(customer);
		Cart cart=new Cart();
		cart.setCustomer(saved);
		cartRepo.save(cart);
		customer.setCart(cart);
		return saved;
		
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerException {
		Optional<Customer> c=customerRepo.findById(customerId);
		
		if(c.isPresent()) {
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException {
		List<CurrentUserSession> extCu=userSessionRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customer.getCustomerId())
			throw new CustomerException("invalid customer detail, please login first");
		
		Optional<Customer> c=customerRepo.findById(customer.getCustomerId());
		if(c.isPresent()) {
			return customerRepo.save(customer);
		}
		throw new CustomerException("user not found with id : "+customer.getCustomerId());
	}

	@Override
	public Customer deleteCustomer(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=userSessionRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id, please fill valid id");
		
		
		Optional<Customer> c=customerRepo.findById(customerId);
		if(c.isPresent()) {
			customerRepo.delete(c.get());
			return c.get();
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

	@Override
	public List<Order> viewOrders(int customerId, String key) throws CustomerException {
		List<CurrentUserSession> extCu=userSessionRepo.findByUuid(key);
		if(extCu.size()==0)
			throw new CustomerException("key is not valid");
		
		if(extCu.get(0).getUserId()!=customerId)
			throw new CustomerException("invalid customer id, please fill valid id");
		
		Optional<Customer> c=customerRepo.findById(customerId);
		if(c.isPresent()) {
			List<Order>list=c.get().getOrders();
			if(list.size()==0) {
				throw new CustomerException("order list is empty");
			}
			return list;
		}
		throw new CustomerException("user not found with id : "+customerId);
	}

}
