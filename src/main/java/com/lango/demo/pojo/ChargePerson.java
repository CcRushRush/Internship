package com.lango.demo.pojo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class ChargePerson implements Serializable {

	private static final long serialVersionUID = 1L;

	// 负责人id
	private int cId;

	// 负责人账号
	private String account;

	// 负责人密码
	private String password;

	// 负责人名字
	private String cName;

	// 负责人email
	private String email;

	// 负责人手机号码
	private String phone;

	// 创建时间
	private Date createTime;

	public ChargePerson(int cId, String account, String password, String cName,
			String email, String phone, Date createTime) {
		this.cId = cId;
		this.account = account;
		this.password = password;
		this.cName = cName;
		this.email = email;
		this.phone = phone;
		this.createTime = createTime;
	}

	public ChargePerson() {
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "ChargePerson [cId=" + cId + ", account=" + account
				+ ", password=" + password + ", cName=" + cName + ", email="
				+ email + ", phone=" + phone + ", createTime=" + createTime
				+ "]";
	}

}
