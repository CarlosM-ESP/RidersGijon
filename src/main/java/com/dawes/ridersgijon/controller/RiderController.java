package com.dawes.ridersgijon.controller;

import java.time.LocalDate;
import java.util.List;

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
import com.dawes.ridersgijon.service.EmailService;
import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/riders")
public class RiderController {	
	@Autowired
	UserService userService;	
	@Autowired
	PedidoService pedidoService;	
	@Autowired
	EmailService email;
	
	/**
	 * Pantalla de inicio del Rider tras autenticación
	 * 
	 */	
	@GetMapping("")	
	public String riders(Model model){
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
		return "/riders/riderHistory";
	}
	
	/**
	 * Listado histórico de pedidos de un Rider
	 */
	@GetMapping ("/history")
	public String riderHistory(Model model){
		//determinamos el rider logueado
    	model.addAttribute("nick", userService.findUserLogged().getNick());	
    	UserVO user = userService.findUserLogged();
    	//recuperamos la lista de pedidos del rider
    	model.addAttribute("listaPedidos", pedidoService.findByRider(user));
		return "/riders/riderHistory";
	}
	
	/**
	 * Perfil del Rider
	 */
	@GetMapping ("/profile")
	public String ridereProfile(Model model){	
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//determinamos el id del usuario logueado
    	int id_user = userService.findUserLogged().getId_user();
    	//Le pasamos el UserVO para mostrarlo en formulario de la vista    	
    	model.addAttribute("detalleUser", userService.findById(id_user).get());
		return "/riders/riderProfile";
	}
	
	/**
	 * Actualización de datos de un rider
	 */
	@PostMapping ("/profile")
	public String riderUpdate(@ModelAttribute ("detalleUser") UserVO detalleUser, Model model){	
		userService.save(detalleUser);		
		return  "redirect:/riders/history";
	}
	
	/**
	 * Vista de Detalles de un Pedido historico de un Rider 
	 */
	@GetMapping ("/orderDetail")
	public String orderDetail(@RequestParam(name="id_pedido") int id_pedido,  Model model){		
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	
    	//Le pasamos el id del pedido para mostrarlo en la cabecera del formulario
    	model.addAttribute("id", id_pedido );
    	//Le pasamos el PedidoVO para mostrarlo en formulario de la vista
    	model.addAttribute("detallePedido", pedidoService.findById(id_pedido));    	
    	//Lista de Clientes para usar un select en el formulario    	
    	model.addAttribute("listaClientes", userService.findByUser_type("CLIENT"));
    	//Lista de Riders para usar un select en el formulario 	
    	model.addAttribute("listaRiders", userService.findByUser_type("RIDER"));    	
		return "/riders/riderOrderDetail";
	}
	
	/**
	 * Vista de página de pedidos disponibles para un rider (Nuevos. Status = 0)
	 */
	@GetMapping ("/available")
	public String riderAvailableOrders(Model model){
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	    	
    	model.addAttribute("listaPedidos", pedidoService.findByStatus(0));    	
    	return "/riders/riderAvailable";
	}
	
	/**
	 * Vista de Detalles de un Pedido Disponible para un rider 
	 */
	@GetMapping ("/orderDetailAvailable")
	public String orderDetailAvailable(@RequestParam(name="id_pedido") int id_pedido,  Model model){
		//Le pasamos el nombre de usuario autenticado
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	
    	//Le pasamos el id del pedido para mostrarlo en la cabecera del formulario
    	model.addAttribute("id", id_pedido );
    	//Le pasamos el PedidoVO para mostrarlo en formulario de la vista
    	model.addAttribute("detallePedido", pedidoService.findById(id_pedido));    	
    	//Lista de Clientes para usar un select en el formulario    	
    	model.addAttribute("listaClientes", userService.findByUser_type("CLIENT"));
    	//Lista de Riders para usar un select en el formulario 	
    	model.addAttribute("listaRiders", userService.findByUser_type("RIDER"));    	
		return "/riders/riderOrderDetailAvailable";
	}
	
	/**	 
	 * Actualización de status de un pedido a "ASIGNADO" por el rider 
	 */
	@PostMapping("/orderDetailAvailable")
	public String orderAssigned(@ModelAttribute("detallePedido") PedidoVO detallePedido, Model model){
		detallePedido.setStatus(1);
		detallePedido.setRider(userService.findUserLogged());		
		pedidoService.save(detallePedido);	
		
				
		//Email-------------------------------
				EmailVO mailUpdatedOrder = new EmailVO();
				
				mailUpdatedOrder.setFechaEmail(LocalDate.now());				
				mailUpdatedOrder.setName(detallePedido.getRider().getNombre());
				
				mailUpdatedOrder.setSubject("Pedido Asignado al Rider : "+detallePedido.getRider().getNombre());
				mailUpdatedOrder.setMessage("PedidoAsignado al Rider " + detallePedido.getRider().getNombre() + ".\n Mensaje:\n"
				+"Fecha de entrega asignada: "+ detallePedido.getFecha_entregado());
				
				mailUpdatedOrder.setEmail(detallePedido.getCliente().getEmail());						
				
				
				String subject = mailUpdatedOrder.getSubject();				
				String message = mailUpdatedOrder.getMessage();				
				String clientAddress = mailUpdatedOrder.getEmail();
				String riderAddress = detallePedido.getRider().getEmail();
				String AutomaticResponse = "thank you for using our Platform, "+detallePedido.getRider().getNombre()+".";
				//mensaje al cliente
				email.sendSimpleMessage(clientAddress, subject, message);
				//mensaje al administrador
				email.sendSimpleMessage("carlosmdaw2020@gmail.com", subject, message);
				//Mensaje de agradecimiento de la plataforma al Rider				
				email.sendSimpleMessage(riderAddress, "RE: "+subject, AutomaticResponse);
		
		return  "redirect:/riders/history";
	}
	
	/**
	 * Vista de página pedidos "entregables" para un rider (asignados)
	 */
	@GetMapping ("/entregas")
	public String riderEntregasOrders(Model model){
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	    	
    	model.addAttribute("listaPedidos", pedidoService.findByRiderAndStatus(userService.findUserLogged(), 1));
    	return "/riders/riderEntregas";
	}
	
	/**
	 * Vista de Detalles de un Pedido a definir como entregado para Riders 
	 */
	@GetMapping ("/orderDetailEntrega")
	public String orderDetailEntrega(@RequestParam(name="id_pedido") int id_pedido,  Model model){
	//Le pasamos el nombre de usuario autenticado
		model.addAttribute("nick", userService.findUserLogged().getNick());   	
   	//Le pasamos el id del pedido para mostrarlo en la cabecera del formulario
		model.addAttribute("id", id_pedido );
   	//Le pasamos el PedidoVO para mostrarlo en formulario de la vista
		model.addAttribute("detallePedido", pedidoService.findById(id_pedido));
		return "/riders/riderOrderDetailEntrega";	
	}
	
	/**	 
	 * Actualización de un pedido a ENTREGADO por un rider 
	 */
	@PostMapping("/orderDetailEntrega")
	public String orderEntrega(@ModelAttribute("detallePedido") PedidoVO detallePedido, Model model){
		detallePedido.setStatus(2);		
		pedidoService.save(detallePedido);
		
		//Email-------------------------------
		EmailVO mailDeliveredOrder = new EmailVO();
		
		mailDeliveredOrder.setFechaEmail(LocalDate.now());				
		mailDeliveredOrder.setName(detallePedido.getRider().getNombre());
		
		mailDeliveredOrder.setSubject("Pedido " + detallePedido.getId_pedido() + " Entregado");
		mailDeliveredOrder.setMessage("Pedido " + detallePedido.getId_pedido() + " Entregado" + ".\n Mensaje:\n"
		+"Fecha de entrega final: "+ detallePedido.getFecha_entregado());
		
		mailDeliveredOrder.setEmail(detallePedido.getCliente().getEmail());						
		
		
		String subject = mailDeliveredOrder.getSubject();				
		String message = mailDeliveredOrder.getMessage();				
		String clientAddress = mailDeliveredOrder.getEmail();
		String riderAddress = detallePedido.getRider().getEmail();
		String AutomaticResponse = "Good Job, "+detallePedido.getRider().getNombre()+". Keep using our platform";
		String AutomaticResponseClient = "Your Order "+detallePedido.getId_pedido()+ " has been delivered, "+detallePedido.getCliente().getNombre()+". Keep using our platform!";
		
		//mensaje al cliente
		email.sendSimpleMessage(clientAddress, subject, message);
		//mensaje al administrador
		email.sendSimpleMessage("carlosmdaw2020@gmail.com", subject, message);
		//Mensaje de agradecimiento de la plataforma al Rider				
		email.sendSimpleMessage(riderAddress, "RE: "+subject, AutomaticResponse);		
		//Mensjae Final al cliente
		email.sendSimpleMessage(clientAddress, "RE: "+subject, AutomaticResponseClient);
		
		
		return  "redirect:/riders/history";
	}
	
	
}

