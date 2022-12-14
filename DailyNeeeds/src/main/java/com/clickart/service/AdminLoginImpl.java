package com.clickart.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.LoginException;
import com.clickart.model.Admin;
import com.clickart.model.CurrentAdminSession;
import com.clickart.model.Login;
import com.clickart.repository.AdminRepo;
import com.clickart.repository.AdminSessionRepo;

import net.bytebuddy.utility.RandomString;

@Service
public class AdminLoginImpl implements AdminLogin{
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;
	
	
	@Autowired
	private AdminRepo adminRepo;

	@Override
	public CurrentAdminSession adminLog(Login dto) throws LoginException {
		List<Admin>temp=adminRepo.findByAdminMobile(dto.getMobile());
		if(temp.size()==0)
			throw new LoginException("please enter valid mobile number");
		
		Admin admin=temp.get(0);
		
		Optional<CurrentAdminSession> validation=adminSessionRepo.findById(admin.getAdminId());
		if(validation.isPresent()) {
			
			if(admin.getAdminPassword().equals(dto.getPassword())) {
				return validation.get();
			}
			
			throw new LoginException("please enter valid password");
		}
		
		if(admin.getAdminPassword().equals(dto.getPassword())) {
			String key=RandomString.make(6);
			CurrentAdminSession cas=new CurrentAdminSession();
			cas.setLocalDateTime(LocalDateTime.now());
			cas.setUserId(admin.getAdminId());
			cas.setUuid(key);
			adminSessionRepo.save(cas);
			return cas;
		}
		throw new LoginException("please enter valid password");
	}

	@Override
	public String adminLogOut(String key) throws LoginException {
		List<CurrentAdminSession>validation=adminSessionRepo.findByUuid(key);
		if(validation.size()==0) {
			throw new LoginException("user not logged in with this number");
		}
		adminSessionRepo.delete(validation.get(0));
		return "Logged out !";
	}

}
