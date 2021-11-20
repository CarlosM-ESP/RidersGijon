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

import com.dawes.ridersgijon.model.EmailVO;
import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.EmailService;
import com.dawes.ridersgijon.service.PedidoService;
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
	
	@Autowired
	PedidoService pedidoService;
	
	/**
	 * Página home de accesoa la aplicación
	 * @return
	 */
	@GetMapping("/")
	public String inicio() {
		return "index";
	}
	/**
	 * Página de inicio
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}	

	/**
	 * Página vista de LOGIN USUARIO	
	 * @param model
	 * @return
	 */
	@GetMapping("/login")
	public String login(Model model) {
		model.addAttribute("user", new UserVO());
		return "login";
	}
	
	/**
	 * Mapeo definido en securityConfig tras logueo exitoso
	 * @param model
	 * @param session
	 * @return
	 */
	@GetMapping("/loginUser")	
	//Pasamos un atributos HttpSession para poder personalizar a voluntad las vistas en función del usuario
	public String getUserLoginPage(Model model, HttpSession session){			
	    if (userService.isAuthenticated()) {
	    	//Le pasamos el nombre de usuario
	    	//Decidir cual de los dos nos quedamos
	    	model.addAttribute("nick", userService.findUserLogged().getNick());
	    	session.setAttribute("nick", userService.findUserLogged().getNick());
	    	
	    	//Le pasamos a la sesión el tipo de usuario para personalizar los navbar en función del tipo 
	        session.setAttribute("tipoUsuario", userService.findUserLogged().getUser_type());
	    	//Comprobamos qué tipo de usuario es para redireccionar
	    	if (userService.findUserLogged().getUser_type().equals("ADMIN")){
		        return "redirect:admin/orderList";	    		
	    	}
	        if (userService.findUserLogged().getUser_type().equals("CLIENT")){	
		        return "redirect:clientes/history";
	    	}
	    	if (userService.findUserLogged().getUser_type().equals("RIDER")){
		        return "redirect:riders/history";	    		
	    	}
	    }
	    return "login";
	}	
	/**
	 * Página de Contacto
	 * @param model
	 * @return
	 */
	@GetMapping("/contact")
	public String contact(Model model) {
		//Le pasamos un objeto Email para recoger los datso del form de contacto y poder enviarlos por correo
		//todo Como hacer esto sin crear una entidad?????		
		model.addAttribute("user", new EmailVO());
		return "contact";
	}
	
	//Envio de Emails desde contactos...PRUEBAS*****************************************	
	
	/**
	 * Proceso de Envío de Emails según datos de contacto
	 * @param emailData
	 * @param model
	 * @return
	 */
	@PostMapping("/enviar")
		public String contactoEnviado(@ModelAttribute EmailVO emailData, Model model) {
			//recuperamos datos del form de contacto guardados en un objeto EmailVO
			String address = emailData.getEmail();
			String subject = emailData.getSubject();
			String message = emailData.getMessage();
			//Enviamos
			String AutomaticResponse = "thank you for contact us, "+emailData.getName()+". We will contact you as soon as possible.";
			message = "From: "+emailData.getName()+". \n"+message;
			email.sendSimpleMessage("carlosmdaw2020@gmail.com", subject, message);
			email.sendSimpleMessage(address, "RE: "+subject, AutomaticResponse);
			 
			return "enviado";
		}	
		//Envio de Emails...PRUEBAS*****************************************
	
	/**
	 * Error 403. No permitido
	 */
	@GetMapping("/403")
	public String error403() {
		return "403";
	}

	/**
	 * Log out segñun spring securitu requiere
	 * @return
	 */
	@GetMapping("/logout")
	public String logout() {
		return "logout";
	}
	
	/**
	 * Registro de nuevo usuario	 * 
	 * @param model
	 * @return
	 */
		@GetMapping("/register")
		public String register(Model model) {
			//Se añade un modelo vacío donde se guardaran los datos del formulario
			model.addAttribute("user", new UserVO());			
			return "register";
			}
	
	/**
	 * Guardar nuevo Usuario 
	 */
	@PostMapping("/register")
	public String registerSuccess(@ModelAttribute UserVO user, Model model){		
		//Falta Validación Datos Formulario
		//Activamos usuario y codificamos contraseña antes de guardarla
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
	
	/**
	 * Cambio de contraseña Todos Usuarios
	 * @param id_user
	 * @param model
	 * @return
	 */
	@GetMapping("/passwordChange")
	public String PasswordChange(@RequestParam(name= "id_user") int id_user, Model model) {
		//no se valida realmente la antigua contraseña
		String oldPassword = "";
		String newPassword1 = "";
		String newPassword2 = "";
		
		model.addAttribute("logged_userId", userService.findUserLogged().getId_user());
		model.addAttribute("oldpassword", oldPassword);
		model.addAttribute("newPassword1", newPassword1);
		model.addAttribute("newPassword2", newPassword2);
		model.addAttribute("id_user", id_user);
		//Redirección según tipo de Cliente
		//TODO Feo.Intentar buscar otra forma
		String tipoUsuario = userService.findUserLogged().getUser_type();
		if(tipoUsuario.equals("CLIENT")) {
			return "passwordChange_cliente";
		}else if(tipoUsuario.equals("RIDER")) {
			return "passwordChange_rider";
		}else if(tipoUsuario.equals("ADMIN")) {
			return "passwordChange";
		}
		return "";
	}
	/**
	 * Actualización Contraseña usuario
	 * @param id_user
	 * @param oldPassword
	 * @param newPassword1
	 * @param newPassword2
	 * @param model
	 * @return
	 */
	@PostMapping("/passwordChange")
	public String PasswordChangeProccessing(@ModelAttribute("id_user") int id_user
			, @ModelAttribute("oldPassword") String oldPassword
			, @ModelAttribute("newPassword1") String newPassword1
			, @ModelAttribute("newPassword2") String newPassword2
			, Model model)
	{				
		model.addAttribute(id_user);
		//No cambiar contraseña de otro usuario, rider o Admin
		if(userService.findUserLogged().getId_user() != id_user) {				
			return "/errorCambioContrasena";			
		//no cambiar si no coinciden las dos contraseñas
		//discriminar por tipo de usuario
		//TODO Feo, intentar hacerlo de otra manera
		}else if(!newPassword1.equals(newPassword2)){
			String tipoUsuario = userService.findUserLogged().getUser_type();
			if(tipoUsuario.equals("CLIENT")) {
				return "/errorCambioContrasena2_cliente";
			}else if(tipoUsuario.equals("RIDER")) {
				return "/errorCambioContrasena2_rider";
			}else if(tipoUsuario.equals("ADMIN")) {
				return "/errorCambioContrasena2";
			}
							
		}else{				
			UserVO user = userService.findById(id_user).get();
			user.setPassword(userService.encode(newPassword1));
			userService.save(user);
			//discriminar por tipo de usuario
			//TODO Feo, intentar hacerlo de otra manera
			String tipoUsuario2 = userService.findUserLogged().getUser_type();
			if(tipoUsuario2.equals("CLIENT")) {
				return "cambioContrasenaOK_cliente";
			}else if(tipoUsuario2.equals("RIDER")) {
				return "cambioContrasenaOK_rider";
			}else if(tipoUsuario2.equals("ADMIN")) {
				return "cambioContrasenaOK";
			}
		}
		return "";
	}		
}