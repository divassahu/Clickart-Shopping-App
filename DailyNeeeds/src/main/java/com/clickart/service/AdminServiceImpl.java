package com.clickart.service;

import java.util.List; 
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clickart.exception.AdminException;
import com.clickart.model.Admin;
import com.clickart.model.CurrentAdminSession;
import com.clickart.repository.AdminRepo;
import com.clickart.repository.AdminSessionRepo;

@Service
public class AdminServiceImpl implements AdminService{
	
	@Autowired
	private AdminRepo ar;
	
	@Autowired
	private AdminSessionRepo asRepo;

	@Override
	public Admin registerAdmin(Admin admin, String validationKey) throws AdminException {
		if(!validationKey.equals("0546"))
			throw new AdminException("you don't have authority to register as admin");
		
		return ar.save(admin);
	}

	@Override
	public List<Admin> viewAllAdmin(String key) throws AdminException {
		List<CurrentAdminSession>check=asRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("you don't have authority to see admin list");
		
		return ar.findAll();

	}

	@Override
	public Admin deleteAdmin(Admin admin, String key) throws AdminException {
		List<CurrentAdminSession>check=asRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("You don't have authority to delete admin");
		
		Optional<Admin>opt=ar.findById(admin.getAdminId());
		
		if(opt.isEmpty())
			throw new AdminException("Admin not found with id "+admin.getAdminId());
		
		ar.delete(opt.get());
		
		return opt.get();
				
	}

}
