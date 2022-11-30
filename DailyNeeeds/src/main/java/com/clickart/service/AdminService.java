package com.clickart.service;

import java.util.List; 

import com.clickart.exception.AdminException;
import com.clickart.model.Admin;

public interface AdminService {
	
	public Admin registerAdmin(Admin admin, String validationKey)throws AdminException;
	
	public List<Admin> viewAllAdmin(String key)throws AdminException;
	
	public Admin deleteAdmin(Admin admin, String key)throws AdminException;

}
