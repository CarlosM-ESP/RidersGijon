package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.EmailService;
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
	UserService user;
	
	@Autowired
	EmailService email;
	
	@GetMapping ("/")
	public String inicio(){
		return "index";
	}
	
	@GetMapping ("/index")
	public String index(){
		return "index";
	}
		
	@GetMapping ("/register")
	public String register(){
		return "register";
	}
	
	@GetMapping ("/login")
	public String login(){
		return "login";
	}
	
	@GetMapping ("/contact")
	public String contact(){
		return "contact";
	}
	
	
	@PostMapping ("/enviar")
	public String contactoEnviado(){
		email.sendSimpleMessage("carlosmdaw2020@gmail.com", "AsuntoMensaje","CuerpoMensaje");		
		return "enviado";
	}
	
	@GetMapping ("/403")
	public String error403(){
		return "403";
	}
	@GetMapping("/logout")
    public String logout() {
        return "logout";
    }
	@GetMapping("/modal")
    public String modal() {
        return "../modal";
    }

}
