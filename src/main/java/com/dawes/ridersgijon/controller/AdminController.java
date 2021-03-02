package com.dawes.ridersgijon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dawes.ridersgijon.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserService user;	
	
	@GetMapping ("")
	public String admin(){
		return "/admin/adminOrdersOrderList";
	}
	
	@GetMapping ("/adminList")
	public String adminList(){
		return "/admin/adminAdminsAdminList";
	}
	
	@GetMapping ("/adminDetail")
	public String adminDetail(){
		return "/admin/adminAdminsAdminDetail";
	}
	
	@GetMapping ("/clientList")
	public String clientList(){
		return "/admin/adminClientsClientList";
	}
	
	@GetMapping ("/clientDetail")
	public String clientDetail(){
		return "/admin/adminClientsClientDetail";
	}
	
	@GetMapping ("/riderList")
	public String riderList(){
		return "/admin/adminRidersRiderList";
	}
	
	@GetMapping ("/riderDetail")
	public String riderDetail(){
		return "/admin/adminRidersRiderDetail";
	}
	
	@GetMapping ("/orderList")
	public String orderList(){
		return "/admin/adminOrdersOrderList";
	}
	
	@GetMapping ("/orderDetail")
	public String orderDetail(){
		return "/admin/adminOrdersOrderDetail";
	}
	
}
