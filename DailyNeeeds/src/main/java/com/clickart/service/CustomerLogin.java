package com.clickart.service;

import com.clickart.exception.LoginException;  
import com.clickart.model.Login;

public interface CustomerLogin {
	
	public String logIntoAccount(Login dto)throws LoginException;

	public String logOutFromAccount(String key)throws LoginException;

}
