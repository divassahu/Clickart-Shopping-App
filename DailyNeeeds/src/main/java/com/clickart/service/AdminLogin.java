package com.clickart.service;

import com.clickart.exception.LoginException;
import com.clickart.model.CurrentAdminSession;
import com.clickart.model.Login;

public interface AdminLogin {
	
	public CurrentAdminSession adminLog(Login dto)throws LoginException;

	public String adminLogOut(String key)throws LoginException;

}
