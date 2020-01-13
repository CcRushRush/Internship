package com.lango.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lango.demo.service.impl.UserService;

@Controller
public class ToHTML {

	@Autowired
	private UserService userService;
	
	@RequestMapping("index")
	public String index(){
		return "index";
	}
	@RequestMapping("home")
	public String home(){
		userService.login();
		return "home";
	}

}
