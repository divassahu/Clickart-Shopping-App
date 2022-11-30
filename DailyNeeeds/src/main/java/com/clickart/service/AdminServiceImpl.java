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
	private AdminRepo adminRepo;
	
	@Autowired
	private AdminSessionRepo adminSessionRepo;

	@Override
	public Admin registerAdmin(Admin admin, String validationKey) throws AdminException {
		if(!validationKey.equals("0546"))
			throw new AdminException("you don't have authority to register as admin");
		
		return adminRepo.save(admin);
	}

	@Override
	public List<Admin> viewAllAdmin(String key) throws AdminException {
		List<CurrentAdminSession>check=adminSessionRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("you don't have authority to see admin list");
		
		return adminRepo.findAll();

	}

	@Override
	public Admin deleteAdmin(Admin admin, String key) throws AdminException {
		List<CurrentAdminSession>check=adminSessionRepo.findByUuid(key);
		
		if(check.size()==0)
			throw new AdminException("You don't have authority to delete admin");
		
		Optional<Admin>opt=adminRepo.findById(admin.getAdminId());
		
		if(opt.isEmpty())
			throw new AdminException("Admin not found with id "+admin.getAdminId());
		
		adminRepo.delete(opt.get());
		
		return opt.get();
				
	}

}
