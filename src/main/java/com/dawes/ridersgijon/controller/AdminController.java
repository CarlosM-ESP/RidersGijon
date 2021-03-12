package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PedidoService pedidoService;
	
	@GetMapping ("")
	public String admin(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminOrdersOrderList";
	}
	
	@GetMapping ("/adminList")
	public String adminList(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminAdminsAdminList";
	}
	
	@GetMapping ("/adminDetail")
	public String adminDetail(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminAdminsAdminDetail";
	}
	
	@GetMapping ("/clientList")
	public String clientList(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminClientsClientList";
	}
	
	@GetMapping ("/clientDetail")
	public String clientDetail(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminClientsClientDetail";
	}
	
	@GetMapping ("/riderList")
	public String riderList(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminRidersRiderList";
	}
	
	@GetMapping ("/riderDetail")
	public String riderDetail(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/admin/adminRidersRiderDetail";
	}
		
	@GetMapping ("/orderList")
	public String orderList(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//Le pasamos una colección con todos los pedidos de la BBDD
    	model.addAttribute("listaPedidos", pedidoService.findAll());
		return "/admin/adminOrdersOrderList";
	}
		
	@GetMapping ("/orderDetail")
	public String orderDetail(@RequestParam int id_pedido,  Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//Le pasamos el id del pedido para mostrarlo en la cabecera del form
    	model.addAttribute("id", id_pedido );
    	//Le pasamos el PedidoVO para mostrarlo en formulario de la vista
    	model.addAttribute("detallePedido", pedidoService.findById(id_pedido));
		return "/admin/adminOrdersOrderDetail";
	}
	
}
