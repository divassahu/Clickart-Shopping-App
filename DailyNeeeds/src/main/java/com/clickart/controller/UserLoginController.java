package com.clickart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.clickart.exception.LoginException;
import com.clickart.model.Login;
import com.clickart.service.CustomerLogin;

@RestController
@RequestMapping("/userlogin")
public class UserLoginController {
	
	@Autowired
	private CustomerLogin customerLogin;
	
	@PostMapping("/login")
	public ResponseEntity<String> userLogin(@RequestBody Login dto) throws LoginException{
		String res=customerLogin.logIntoAccount(dto);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> userLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=customerLogin.logOutFromAccount(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
