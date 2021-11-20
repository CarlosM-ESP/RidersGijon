package com.dawes.ridersgijon.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.ridersgijon.model.EmailVO;
import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	UserService userService;	
	@Autowired
	PedidoService pedidoService;
	
	/**
	 * Pantalla de inicio del Cliente tras autenticación
	 */
	@GetMapping ("")
	public String clientes(Model model){
		//Le pasamos el nick del usuario que será usado en el header de la página como bienvenida
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/clientes/clientHistory";
	}
	/**
	 * Listado histórico de pedidos de un Cliente
	 */
	@GetMapping ("/history")
	public String clientHistory(Model model){
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//determinamos el cliente logueado
    	UserVO user = userService.findUserLogged();
    	//recuperamos una lista de los pedidos de ese cliente
    	model.addAttribute("listaPedidos", pedidoService.findByCliente(user));
		return "/clientes/clientHistory";
	}
	/**
	 * Perfil del cliente
	 */
	@GetMapping ("/profile")
	public String clienteProfile(Model model){
		model.addAttribute("nick", userService.findUserLogged().getNick());
		//determinamos el id del usuario logueado
    	int id_user = userService.findUserLogged().getId_user();
    	//Le pasamos el UserVO para mostrarlo en formulario de la vista    	
    	model.addAttribute("detalleUser", userService.findById(id_user).get());
		return "/clientes/clientProfile";		
	}
	/**
	 * Actualización de datos de un cliente
	 */
	@PostMapping ("/profile")
	public String clientUpdate(@ModelAttribute ("detalleUser") UserVO detalleUser, Model model){	
		userService.save(detalleUser);		
		return  "redirect:/clientes/profile";
	}
	/**
	 * Vista de página de nuevo pedido
	 */
	@GetMapping ("/orders")
	public String clientOrder(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//le pasamos un objeto PedidoVO que devolverá con datos
    	model.addAttribute("pedido", new PedidoVO());	
		return "/clientes/clientOrder";
	}
	
	/*
	 * Creación de nueva order
	 */
	@PostMapping ("/orders")
	public String newOrder(@ModelAttribute PedidoVO pedidoVO, Model model) {
		pedidoVO.setCliente(userService.findUserLogged());
		//Asiganmos fecha actual como fecha de pedido y status 0 => 'nuevo'
		pedidoVO.setFechaPedido(LocalDate.now());
		pedidoVO.setStatus(0);
		
		pedidoService.save(pedidoVO);
		return "redirect:/clientes/history";
	}
	
	/**
	 * Vista de Detalles de un Pedido 
	 */
	@GetMapping ("/orderDetail")
	public String orderDetail(@RequestParam(name="id_pedido") int id_pedido,  Model model){
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	
    	//Le pasamos el id del pedido para mostrarlo en la cabecera del formulario
    	model.addAttribute("id", id_pedido );
    	//Le pasamos el PedidoVO para poder mostrar los datos en formulario de la vista
    	model.addAttribute("detallePedido", pedidoService.findById(id_pedido));    	
    	//Lista de Clientes para usar un select en el formulario    	
    	model.addAttribute("listaClientes", userService.findByUser_type("CLIENT"));
    	//Lista de Riders para usar un select en el formulario 	
    	model.addAttribute("listaRiders", userService.findByUser_type("RIDER"));    	
		return "/clientes/clienteOrderDetail";
	}
	
	/**
	 * TODO para siguiente fase de la aplicación	 * 
	 * Borrar Pedido. Desde Vista de Detalle de Pedido
	 */
	@GetMapping("/orderDelete")
	public String orderDelete(@RequestParam(name="id_pedido") int id_pedido, Model model) {
			pedidoService.deleteById(id_pedido);				
			return "redirect:/clientes/history";				
	}	
}
