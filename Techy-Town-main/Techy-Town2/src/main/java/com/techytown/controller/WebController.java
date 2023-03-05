package com.techytown.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {

	@RequestMapping("/")
	  public String techy_town() {
	    return "index";
	  }
	
}
