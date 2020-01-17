package com.lango.demo.pojo;

import java.io.Serializable;
import java.sql.Date;

import org.springframework.stereotype.Component;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Component
public class FaultRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// 故障id
	private int fId;
	
	// 设备id
	private int eId;
	
	// 服务id
	private int sId;
	
	// 故障类型
	private String fType;
	
	// 负责人id
	private int cId;
	
	// 负责人名字
	private String chargePerson;
	
	// 所使用的修复方法id
	private String methodIds;
	
	// 修复结果
	private String result;
	
	// 修复时间
	private Date operateTime;

	public FaultRecord() {
		// TODO Auto-generated constructor stub
	}

	public FaultRecord(int fId, int eId, int sId, String fType, int cId,
			String chargePerson, String methodIds, String result,
			Date operateTime) {
		super();
		this.fId = fId;
		this.eId = eId;
		this.sId = sId;
		this.fType = fType;
		this.cId = cId;
		this.chargePerson = chargePerson;
		this.methodIds = methodIds;
		this.result = result;
		this.operateTime = operateTime;
	}

	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
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

	public String getfType() {
		return fType;
	}

	public void setfType(String fType) {
		this.fType = fType;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getChargePerson() {
		return chargePerson;
	}

	public void setChargePerson(String chargePerson) {
		this.chargePerson = chargePerson;
	}

	public String getMethodIds() {
		return methodIds;
	}

	public void setMethodIds(String methodIds) {
		this.methodIds = methodIds;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(Date operateTime) {
		this.operateTime = operateTime;
	}

	@Override
	public String toString() {
		return "FaultRecord [fId=" + fId + ", eId=" + eId + ", sId=" + sId
				+ ", fType=" + fType + ", cId=" + cId + ", chargePerson="
				+ chargePerson + ", methodIds=" + methodIds + ", result="
				+ result + ", operateTime=" + operateTime + "]";
	}

}
