package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.FaultRecord;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface FaultRecordMapper {

	public List<FaultRecord> selectFaultRecords(FaultRecord faultRecord);

	public int insertFaultRecord(FaultRecord faultRecord);
	
	public int deleteFaultRecord(Long fId);
}
