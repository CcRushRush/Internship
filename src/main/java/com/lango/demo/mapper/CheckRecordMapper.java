package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.CheckRecord;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface CheckRecordMapper {

	public List<CheckRecord> selectCheckRecords(CheckRecord checkRecord);
	
	public int insertCheckRecord(CheckRecord checkRecord);
	
	public int deleteCheckRecord(Long checkId);
}
