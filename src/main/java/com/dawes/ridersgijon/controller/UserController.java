package com.dawes.ridersgijon.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.EmailService;
import com.dawes.ridersgijon.service.RolService;
import com.dawes.ridersgijon.service.UserRolService;
import com.dawes.ridersgijon.service.UserService;

/*Se pueden pasar argumentos...Tenerlo en cuenta
 * @RequestMapping(value = "/ex/foos/{fooid}/bar/{barid}", method = GET)
@ResponseBody
public String getFoosBySimplePathWithPathVariables
  (@PathVariable long fooid, @PathVariable long barid) {
    return "Get a specific Bar with id=" + barid + 
      " from a Foo with id=" + fooid;
 * 
 * */

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService email;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	UserRolService userRolService;
	
	@Autowired
	RolService rolService;	

	//Codificador de la contraseña
	public String encode(String password) {
		return encoder.encode(password);
	}
	//**********************************PRUEBAS redireccionamiento usuarios y autenticacion**************************************************
	//Verificación de autenticación
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    System.out.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities());	    
	    return authentication.isAuthenticated();
	}
	
	//Recuperar el rol del usuario autenticado
		private String giveMeTheRole() {
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
		        return null;
		    }
//		    String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();	    
//		    return roles;
		    String roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();	    
		    return roles;
		}
//**********************************PRUEBAS redireccionamiento usuarios y autenticacion******
	
	
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
	
	//**********************************PRUEBAS redireccionamiento usuarios y autenticacion
	//Si estás autenticado no
	@GetMapping("/loginUser")
	public String getUserLoginPage() {
	    if (isAuthenticated()) {
	    	if (giveMeTheRole().contains("ADMIN")) {
		        return "redirect:admin";	    		
	    	}
	    	if (giveMeTheRole().contains("CLIENT")) {
		        return "redirect:clientes";	    		
	    	}
	    	if (giveMeTheRole().contains("RIDER")) {
		        return "redirect:riders";	    		
	    	}
	    }
	    return "login";
	}
	
	//**********************************PRUEBAS redireccionamiento usuarios y autenticacion

	
//  Obsoleto
//	@GetMapping("/loginSuccess")
//	public String loginSuccess() {
//		return "loginSuccess";		
//		}
	
	
	


	@GetMapping("/contact")
	public String contact() {
		return "contact";
	}

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
		
		
		
		//*******************************
		user.setActive(true);
		user.setPassword(encode(user.getPassword()));				
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

	//Envio de Emails desde contactos...PRUEBAS*****************************************
	@PostMapping("/enviar")
	public String contactoEnviado() {
		email.sendSimpleMessage("carlosmdaw2020@gmail.com", "AsuntoMensaje", "CuerpoMensaje");
		return "enviado";
	}	
	//Envio de Emails...PRUEBAS*****************************************
}
