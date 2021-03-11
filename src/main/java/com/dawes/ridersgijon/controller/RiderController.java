package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/riders")
public class RiderController {
	
	@Autowired
	UserService userService;	
	
	@GetMapping("")	
	public String riders(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/riders/riderHistory";
	}
	
	@GetMapping ("/history")
	public String riderHistory(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/riders/riderHistory";
	}
	
	@GetMapping ("/profile")
	public String ridereProfile(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/riders/riderProfile";
	}
	
	@GetMapping ("/available")
	public String riderOrder(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/riders/riderAvailable";
	}
	
	
}
