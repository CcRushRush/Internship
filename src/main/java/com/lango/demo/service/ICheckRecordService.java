package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.CheckRecord;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface ICheckRecordService {

	public List<CheckRecord> selectCheckRecords(CheckRecord checkRecord);

	public boolean insertCheckRecord(CheckRecord checkRecord);
	
	public boolean deleteCheckRecord(Long checkId);
}
