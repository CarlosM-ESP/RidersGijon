package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	UserService user;	
	
	@GetMapping ("")
	public String clientes(){
		return "/clientes/clientHistory";
	}
	
	
}
