package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;


	

@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedido;
	
	@Autowired
	UserService userService;
	
	
	@GetMapping ("")
	public String admin(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/pedidos/pedidosPrueba";
	}
	
}
