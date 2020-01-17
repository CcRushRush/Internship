package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.FaultRecord;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface IFaultRecordService {

	public List<FaultRecord> selectFaultRecords(FaultRecord faultRecord);

	public boolean insertFaultRecord(FaultRecord faultRecord);
	
	public boolean deleteFaultRecord(Long fId);
}
