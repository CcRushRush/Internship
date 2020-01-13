package com.lango.demo.service.impl;

import org.springframework.stereotype.Service;

import com.lango.demo.service.IManagerService;

@Service
public class ManagerService implements IManagerService{

	@Override
	public String login() {
		// TODO Auto-generated method stub
		return "manager login success";
	}
	
}
