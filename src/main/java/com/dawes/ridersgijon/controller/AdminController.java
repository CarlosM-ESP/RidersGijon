package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.RolService;
import com.dawes.ridersgijon.service.UserRolService;
import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	PedidoService pedidoService;
	
	@Autowired
	RolService rolService;
	
	@Autowired
	UserRolService userRolService;
	
	
	
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
    	//Le pasamos una colección con todos los usuarios Administradores de la BBDD
    	model.addAttribute("listaAdmins", userService.findByUser_type("ADMIN"));
		return "/admin/adminAdminsAdminList";
	}
	
	@GetMapping ("/adminDetail")		
	public String adminDetail(@RequestParam(name="id_user") int id_user,  Model model){		
		//Recuperamos el usuario
		UserVO user = userService.findById(id_user).get();
		//Nick del usuario autenticado
    	model.addAttribute("nick", userService.findUserLogged().getNick());    	
    	
    	//Le pasamos el UserVO para mostrarlo en formulario de la vista    	
    	model.addAttribute("detalleUser", user);    	
		return "/admin/adminAdminsAdminDetail";
	}
	
	//Actualizar Administrador
		@PostMapping("/adminList")
		public String adminUpdate(@ModelAttribute ("detalleUser") UserVO detalleUser, Model model){
			detalleUser.setActive(true);
			userService.save(detalleUser);		
			return  "redirect:/admin/adminList";
		}
		
	//Eliminar Administrador
		@PostMapping("/adminDelete")
		public String adminDelete(@ModelAttribute("detalleUser") UserVO user, Model model) {			
			if(userService.findByUser_type("ADMIN").size() == 1) {				
				return "/errorEliminarAdmin";				
			}else if(userService.findUserLogged().getId_user() == user.getId_user()){
				return "/errorEliminarAutenticado";	
			}else {				
				UserRolVO userRol = userRolService.findByUser(user).get();				
				userRolService.delete(userRol);				
				userService.delete(userService.findById(user.getId_user()).get());				
				return "redirect:/admin/adminList";				
			}
		}
		
		//Formulario Registro nuevo Administrador
		@GetMapping("/registerAdmin")
		public String registerAdmin(Model model) {
			model.addAttribute("user", new UserVO());			
			return "admin/registerAdmin";
			}
		
		// Guardar en BBDD los datos de nuevo administrador
				@PostMapping("/registerAdmin")
				public String registerSuccess(@ModelAttribute UserVO user, Model model){			
					//Falta Validación Datos Formulario	
					user.setActive(true);
					user.setUser_type("ADMIN");
					user.setPassword(userService.encode(user.getPassword()));				
					userService.save(user);
						UserRolVO userRol = new UserRolVO(0, userService.findById(user.getId_user()).get(),rolService.findById(2).get());
						userRolService.save(userRol);			
					return "admin/signingAdminSuccess";
			}
	
	
	@GetMapping ("/clientList")
	public String clientList(Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//Le pasamos una colección con todos los usuarios Administradores de la BBDD
    	model.addAttribute("listaClientes", userService.findByUser_type("CLIENT"));
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
    	//Le pasamos una colección con todos los usuarios Administradores de la BBDD
    	model.addAttribute("listaRiders", userService.findByUser_type("RIDER"));
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
	
	//Ver Detalles de un pedido y actualizar
	@GetMapping ("/orderDetail")
	public String orderDetail(@RequestParam int id_pedido,  Model model){
		//Le pasamos el nombre de usuario
    	model.addAttribute("nick", userService.findUserLogged().getNick());
    	//Le pasamos el id del pedido para mostrarlo en la cabecera del form
    	model.addAttribute("id", id_pedido );
    	//Le pasamos el PedidoVO para mostrarlo en formulario de la vista
    	model.addAttribute("detallePedido", pedidoService.findById(id_pedido));    	
    	//Lista de Clientes para usar un select en el formulario    	
    	model.addAttribute("listaClientes", userService.findByUser_type("CLIENT"));
    	//Lista de Riders para usar un select en el formulario 	
    	model.addAttribute("listaRiders", userService.findByUser_type("RIDER"));    	
    	
		return "/admin/adminOrdersOrderDetail";
	}
	//Actualizar Pedido
	@PostMapping("/orderList")
	public String orderUpdate(@ModelAttribute("detallePedido") PedidoVO detallePedido, Model model){		
		pedidoService.save(detallePedido);		
		return  "redirect:/admin/orderList";
	}
}
