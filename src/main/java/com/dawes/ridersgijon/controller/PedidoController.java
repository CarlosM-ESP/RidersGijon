package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;

//ESTE CONTROLADOR NO TIENE FUNCIONES NI VISTAS. LA LOGICA DE PEDIDOS SE INTEGRA TOTALMENTE EN LAS DIFERENTES VISTAS DE USUARIO
//SE AÑADIRÁ ESTA LÓGICA EN FORMA DE fragments DE THYMELEAF QUE SE INCRUSTARÁN EN LAS VISTAS.
	
@Controller
@RequestMapping("/pedidos")
public class PedidoController {
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	UserService userService;
	

	
}
