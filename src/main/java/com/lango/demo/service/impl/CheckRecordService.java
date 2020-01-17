package com.lango.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.CheckRecordMapper;
import com.lango.demo.pojo.CheckRecord;
import com.lango.demo.service.ICheckRecordService;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Service
public class CheckRecordService implements ICheckRecordService{

	private Logger logger = LoggerFactory.getLogger(CheckRecordService.class);
	
	@Autowired
	private CheckRecordMapper checkRecordMapper;
	
	@Override
	public List<CheckRecord> selectCheckRecords(CheckRecord checkRecord) {
		// TODO Auto-generated method stub
		logger.info("查询记录,参数为:"+checkRecord);
		return checkRecordMapper.selectCheckRecords(checkRecord);
	}

	@Override
	public boolean deleteCheckRecord(Long checkId) {
		// TODO Auto-generated method stub
		logger.info("删除记录,id为:"+checkId);
		int judge = checkRecordMapper.deleteCheckRecord(checkId);
		if(judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertCheckRecord(CheckRecord checkRecord) {
		// TODO Auto-generated method stub
		logger.info("添加检查记录,参数为:"+checkRecord);
		int judge = checkRecordMapper.insertCheckRecord(checkRecord);
		if(judge > 0)
			return true;
		return false;
	}

}
