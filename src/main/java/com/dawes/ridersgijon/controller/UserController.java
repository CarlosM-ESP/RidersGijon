package com.dawes.ridersgijon.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.EmailService;
import com.dawes.ridersgijon.service.RolService;
import com.dawes.ridersgijon.service.UserRolService;
import com.dawes.ridersgijon.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService email;
	
	@Autowired
	UserRolService userRolService;
	
	@Autowired
	RolService rolService;
	
	
	@GetMapping("/")
	public String inicio() {
		return "index";
	}

	@GetMapping("/index")
	public String index() {
		return "index";
	}	

	//LOGIN USUARIO	
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserVO());
		return "login";
	}
	
	//Mapeo definido en securityConfig tras logueo exitoso
	@GetMapping("/loginUser")	
	//Pasamos un atributos HttpSession para poder personalizar a voluntas las vistas en función del usuario
	public String getUserLoginPage(Model model, HttpSession session){			
	    if (userService.isAuthenticated()) {
	    	//Le pasamos el nombre de usuario
	    	//Decidir cual de los dos nos quedamos
	    	model.addAttribute("nick", userService.findUserLogged().getNick());
	    	session.setAttribute("nick", userService.findUserLogged().getNick());
	    	
	    	//Le pasamos a la sesion el tipo de usuario para personalizar los navbar en función del tipo 
	        session.setAttribute("tipoUsuario", userService.findUserLogged().getUser_type());
	    	//Comprobamos que tipo de usuario es para redireccionar
	    	if (userService.findUserLogged().getUser_type().equals("ADMIN")){
		        return "redirect:admin/orderList";	    		
	    	}
	        if (userService.findUserLogged().getUser_type().equals("CLIENT")){	    	
		        return "clientes/clientHistory";	    		
	    	}
	    	if (userService.findUserLogged().getUser_type().equals("RIDER")){
		        return "riders/riderHistory";	    		
	    	}
	    }
	    return "login";
	}	

	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}
	
	//Envio de Emails desde contactos...PRUEBAS*****************************************
		@PostMapping("/enviar")
		public String contactoEnviado() {
			email.sendSimpleMessage("carlosmdaw2020@gmail.com", "AsuntoMensaje", "CuerpoMensaje");
			return "enviado";
		}	
		//Envio de Emails...PRUEBAS*****************************************
	

	@GetMapping("/403")
	public String error403() {
		return "403";
	}

	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}

	
	//Se añade un modelo vacío donde se guardaran los datos del formulario
		@GetMapping("/register")
		public String register(Model model) {
			model.addAttribute("user", new UserVO());			
			return "register";
			}
	
	// Guardar en BBDD los datos de nuevo usuario
	@PostMapping("/register")
	public String registerSuccess(@ModelAttribute UserVO user, Model model){		
		//Falta Validación Datos Formulario	
		user.setIsActive(true);
		user.setPassword(userService.encode(user.getPassword()));				
		userService.save(user);		
		
		//Añadir el Rol de usuario escogido y validar que no se intente añadir otro
		String tipoUsuario = user.getUser_type();		
		if (tipoUsuario.equals("CLIENT")) {			
			UserRolVO userRol = new UserRolVO(0, userService.findById(user.getId_user()).get(),rolService.findById(2).get());
			userRolService.save(userRol);
		}else if(tipoUsuario.equals("RIDER")) {			
			UserRolVO userRol = new UserRolVO(0, userService.findById(user.getId_user()).get(),rolService.findById(3).get());
			userRolService.save(userRol);
		}else{
			userService.delete(user);
			return "errorRoles";
		}
		return "signingSuccess";
}
	
	//Cambio de contraseña Todos Usuarios
			@GetMapping("/passwordChange")
			public String PasswordChange(@RequestParam(name= "id_user") int id_user, Model model) {
				String oldPassword = "";
				String newPassword1 = "";
				String newPassword2 = "";

				model.addAttribute("oldpassword", oldPassword);
				model.addAttribute("newPassword1", newPassword1);
				model.addAttribute("newPassword2", newPassword2);
				model.addAttribute("id_user", id_user);
				return "passwordChange";
			}
			
			@PostMapping("/passwordChange")
			public String PasswordChangeProccessing(@ModelAttribute("id_user") int id_user, @ModelAttribute("oldPassword") String oldPassword, @ModelAttribute("newPassword1") String newPassword1, @ModelAttribute("newPassword2") String newPassword2, Model model) {;
				model.addAttribute(id_user);				
				if(userService.findUserLogged().getId_user() != id_user) {				
					return "/errorCambioContrasena";				
				}else if(!newPassword1.equals(newPassword2)){
					return "/errorCambioContrasena2";				
				}else{				
					UserVO user = userService.findById(id_user).get();
					user.setPassword(userService.encode(newPassword1));
					userService.save(user);
					return "cambioContrasenaOK";
				}
			}	
}
