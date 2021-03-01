package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/riders")
public class RiderController {
	
	@Autowired
	UserService user;	
	
	@GetMapping("")	
	public String riders(){
		return "/riders/riderHistory";
	}
	
	@GetMapping ("/history")
	public String riderHistory(){
		return "/riders/riderHistory";
	}
	
	@GetMapping ("/profile")
	public String ridereProfile(){
		return "/riders/riderProfile";
	}
	
	@GetMapping ("/available")
	public String riderOrder(){
		return "/riders/riderAvailable";
	}
	
	
}
