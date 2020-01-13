package com.lango.demo.mapper;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.lango.demo.pojo.User;

@Repository
public interface UserMapper {

	public List<User> selectUsers();
}
