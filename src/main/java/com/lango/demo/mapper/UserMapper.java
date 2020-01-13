package com.lango.demo.mapper;

import org.springframework.stereotype.Repository;

import com.lango.demo.pojo.User;

@Repository
public interface UserMapper {

	public User login();
}
