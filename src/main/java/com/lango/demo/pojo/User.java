package com.lango.demo.pojo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class User implements Serializable{
	private int userId;
	private String userName;
	private String password;
	private Date createTime;
	public User(int userId, String userName, String password, Date createTime) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.createTime = createTime;
	}
	public User() {
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName
				+ ", password=" + password + ", createTime=" + createTime + "]";
	}
	
}
