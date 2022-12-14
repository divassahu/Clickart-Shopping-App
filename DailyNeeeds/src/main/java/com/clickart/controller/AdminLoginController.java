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
import com.clickart.model.CurrentAdminSession;
import com.clickart.model.Login;
import com.clickart.service.AdminLogin;

@RestController
@RequestMapping("/adminlogin")
public class AdminLoginController {
	
	@Autowired
	private AdminLogin adminLogin;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentAdminSession> adminLogin(@RequestBody Login dto) throws LoginException{
		CurrentAdminSession res=adminLogin.adminLog(dto);
		return new ResponseEntity<CurrentAdminSession>(res,HttpStatus.ACCEPTED);
	}
	
	@PostMapping("/logout")
	public ResponseEntity<String> adminLogout(@RequestParam(required = false)String key) throws LoginException{
		String res=adminLogin.adminLogOut(key);
		return new ResponseEntity<String>(res,HttpStatus.ACCEPTED);
	}

}
