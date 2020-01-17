package com.lango.demo.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class Equipment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 设备id
	private int eId;
	
	// 设备名
	private String eName;
	
	// 外网ip
	private String oIP;
	
	// 内网ip
	private String iIP;
	
	// 设备开发端口
	private String port;
	
	// 设备自定义配置信息
	private String eMessage;
	
	// 域名
	private String domain;
	
	//负责人id
	private int cId;
	
	// 设备负责人
	private String chargePerson;

	public Equipment() {
	}

	public Equipment(int eId, String eName, String oIP, String iIP,
			String port, String eMessage, String domain, int cId,
			String chargePerson) {
		this.eId = eId;
		this.eName = eName;
		this.oIP = oIP;
		this.iIP = iIP;
		this.port = port;
		this.eMessage = eMessage;
		this.domain = domain;
		this.cId = cId;
		this.chargePerson = chargePerson;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String geteName() {
		return eName;
	}

	public void seteName(String eName) {
		this.eName = eName;
	}

	public String getoIP() {
		return oIP;
	}

	public void setoIP(String oIP) {
		this.oIP = oIP;
	}

	public String getiIP() {
		return iIP;
	}

	public void setiIP(String iIP) {
		this.iIP = iIP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String geteMessage() {
		return eMessage;
	}

	public void seteMessage(String eMessage) {
		this.eMessage = eMessage;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getchargePerson() {
		return chargePerson;
	}

	public void setchargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	@Override
	public String toString() {
		return "Equipment [eId=" + eId + ", eName=" + eName + ", oIP=" + oIP
				+ ", iIP=" + iIP + ", port=" + port + ", eMessage=" + eMessage
				+ ", domain=" + domain + ", cId=" + cId + ", chargePerson="
				+ chargePerson + "]";
	}

}
