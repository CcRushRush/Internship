package com.lango.demo.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class TestMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//测试方法id
	private int tId;
	
	//测试方法名称
	private String tName;

	//测试方法详细信息
	private String tMethod;

	public TestMethod() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TestMethod(int tId, String tName, String tMethod) {
		super();
		this.tId = tId;
		this.tName = tName;
		this.tMethod = tMethod;
	}

	public int gettId() {
		return tId;
	}

	public void settId(int tId) {
		this.tId = tId;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}

	public String gettMethod() {
		return tMethod;
	}

	public void settMethod(String tMethod) {
		this.tMethod = tMethod;
	}

	@Override
	public String toString() {
		return "TestMethod [tId=" + tId + ", tName=" + tName + ", tMethod="
				+ tMethod + "]";
	}

}
