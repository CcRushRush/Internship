package com.lango.demo.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class RepairMethod implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 修复方法id
	private int rId;
	
	// 修复方法名称
	private String rName;
	
	// 修复方法具体内容
	private String rMethod;

	public RepairMethod() {
		// TODO Auto-generated constructor stub
	}

	public RepairMethod(int rId, String rName, String rMethod) {
		this.rId = rId;
		this.rName = rName;
		this.rMethod = rMethod;
	}

	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getrMethod() {
		return rMethod;
	}

	public void setrMethod(String rMethod) {
		this.rMethod = rMethod;
	}

	@Override
	public String toString() {
		return "RepairMethod [rId=" + rId + ", rName=" + rName + ", rMethod="
				+ rMethod + "]";
	}

}
