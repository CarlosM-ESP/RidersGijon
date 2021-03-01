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
	
	
}
//@RequestMapping(value = "/ex/foos", method = RequestMethod.GET)