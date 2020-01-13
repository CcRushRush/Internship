package com.lango.demo.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lango.demo.service.IManagerService;
import com.lango.demo.service.IUserService;

@RestController("login")
public class LoginController {

	@Autowired
	private IUserService userService;

	@Autowired
	private IManagerService managerService;
	
	@PostMapping
	public String userLogin(@NotNull String type) {
		if (type.equals("user")) {
			return userService.login();
		} else if(type.equals("manager")){
			return managerService.login();
		} else {
			return "error";
		}
	}
	

	
	@GetMapping
	public String logout() {
		return "logout";
	}
}
