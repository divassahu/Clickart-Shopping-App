package com.clickart.service;

import java.time.LocalDateTime;  
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.LoginException;
import com.clickart.model.CurrentUserSession;
import com.clickart.model.Customer;
import com.clickart.model.Login;
import com.clickart.repository.CustomerRepo;
import com.clickart.repository.UserSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class CustomerLoginImpl implements CustomerLogin{

	@Autowired
	private UserSessionRepo userSessionRepo;
	
	@Autowired
	private CustomerRepo customerRepo;
	
	@Override
	public String logIntoAccount(Login dto) throws LoginException {
		List<Customer> list=customerRepo.findByMobile(dto.getMobile());
		
		if(list.size()==0) {
			throw new LoginException("please enter valid mobile number");
		}
		
		Customer customer=list.get(0);
		Optional<CurrentUserSession> validation=userSessionRepo.findById(customer.getCustomerId());
		
		if(validation.isPresent()) {
			throw new LoginException("user already logged in with this Mobile number");
		}
		
		if(customer.getPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentUserSession cus=new CurrentUserSession();
			cus.setUserId(customer.getCustomerId());
			cus.setUuid(key);
			cus.setLocalDateTime(LocalDateTime.now());
			userSessionRepo.save(cus);
			return cus.toString();
		}
		
		throw new LoginException("please enter valid password");
	}

	@Override
	public String logOutFromAccount(String key) throws LoginException {
		List<CurrentUserSession> validation=userSessionRepo.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this number");
		}
		userSessionRepo.delete(validation.get(0));
		return "Logged out !";
	}

}
