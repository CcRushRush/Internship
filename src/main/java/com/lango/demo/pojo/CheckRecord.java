package com.lango.demo.pojo;

import java.io.Serializable;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class CheckRecord implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//检测记录id
	private int checkId;
	
	//设备id
	private int eId;
	
	//服务id
	private int sId;
	
	//设备名字
	private String equipmentName;
	
	//服务名字
	private String serverName;
	
	//检测结果记录信息
	private String recordMessage;
	
	public CheckRecord() {
		// TODO Auto-generated constructor stub
	}

	public CheckRecord(int checkId, int eId, int sId, String equipmentName,
			String serverName, String recordMessage) {
		super();
		this.checkId = checkId;
		this.eId = eId;
		this.sId = sId;
		this.equipmentName = equipmentName;
		this.serverName = serverName;
		this.recordMessage = recordMessage;
	}

	public int getCheckId() {
		return checkId;
	}

	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getServerName() {
		return serverName;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public String getRecordMessage() {
		return recordMessage;
	}

	public void setRecordMessage(String recordMessage) {
		this.recordMessage = recordMessage;
	}

	@Override
	public String toString() {
		return "CheckRecord [checkId=" + checkId + ", eId=" + eId + ", sId="
				+ sId + ", equipmentName=" + equipmentName + ", serverName="
				+ serverName + ", recordMessage=" + recordMessage + "]";
	}
	
}
