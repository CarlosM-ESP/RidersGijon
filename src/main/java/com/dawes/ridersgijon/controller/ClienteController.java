package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	UserService userService;	
	
	@GetMapping ("")
	public String clientes(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/clientes/clientHistory";
	}
	
	@GetMapping ("/history")
	public String clientHistory(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
		return "/clientes/clientHistory";
	}
	
	@GetMapping ("/profile")
	public String clienteProfile(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
		return "/clientes/clientProfile";
	}
	
	@GetMapping ("/orders")
	public String clientOrder(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
		return "/clientes/clientOrder";
	}
	
}
