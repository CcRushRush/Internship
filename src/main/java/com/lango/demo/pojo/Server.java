package com.lango.demo.pojo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class Server implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//服务id
	private int sId;
	
	//服务名称
	private String sName;
	
	//服务类型
	private String sType;
	
	//服务配置信息
	private String sMessage;
	
	//是否自动检测:1->是; 0->否
	private int autoNotice;
	
	//是否自动修复:1->是; 0->否
	private int autoRepair;
	
	//负责人id
	private int cId;
	
	//设备Id
	private int eId;
	
	//设备负责人
	private String chargePerson;
	
	//测试方法选中的ids
	private String testMethodIds;
	
	//修复方法选中的ids
	private String repairMethodIds;
	
	//创建服务时间
	private Date serverTime;

	public Server() {
		// TODO Auto-generated constructor stub
	}

	public Server(int sId, String sName, String sType, String sMessage,
			int autoNotice, int autoRepair, int cId, int eId,
			String chargePerson, String testMethodIds, String repairMethodIds,
			Date serverTime) {
		super();
		this.sId = sId;
		this.sName = sName;
		this.sType = sType;
		this.sMessage = sMessage;
		this.autoNotice = autoNotice;
		this.autoRepair = autoRepair;
		this.cId = cId;
		this.eId = eId;
		this.chargePerson = chargePerson;
		this.testMethodIds = testMethodIds;
		this.repairMethodIds = repairMethodIds;
		this.serverTime = serverTime;
	}

	public int getsId() {
		return sId;
	}

	public void setsId(int sId) {
		this.sId = sId;
	}

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String getsType() {
		return sType;
	}

	public void setsType(String sType) {
		this.sType = sType;
	}

	public String getsMessage() {
		return sMessage;
	}

	public void setsMessage(String sMessage) {
		this.sMessage = sMessage;
	}

	public int getAutoNotice() {
		return autoNotice;
	}

	public void setAutoNotice(int autoNotice) {
		this.autoNotice = autoNotice;
	}

	public int getAutoRepair() {
		return autoRepair;
	}

	public void setAutoRepair(int autoRepair) {
		this.autoRepair = autoRepair;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public int geteId() {
		return eId;
	}

	public void seteId(int eId) {
		this.eId = eId;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getTestMethodIds() {
		return testMethodIds;
	}

	public void setTestMethodIds(String testMethodIds) {
		this.testMethodIds = testMethodIds;
	}

	public String getRepairMethodIds() {
		return repairMethodIds;
	}

	public void setRepairMethodIds(String repairMethodIds) {
		this.repairMethodIds = repairMethodIds;
	}

	public Date getServerTime() {
		return serverTime;
	}

	public void setServerTime(Date serverTime) {
		this.serverTime = serverTime;
	}

	@Override
	public String toString() {
		return "Server [sId=" + sId + ", sName=" + sName + ", sType=" + sType
				+ ", sMessage=" + sMessage + ", autoNotice=" + autoNotice
				+ ", autoRepair=" + autoRepair + ", cId=" + cId + ", eId="
				+ eId + ", chargePerson=" + chargePerson + ", testMethodIds="
				+ testMethodIds + ", repairMethodIds=" + repairMethodIds
				+ ", serverTime=" + serverTime + "]";
	}


}
