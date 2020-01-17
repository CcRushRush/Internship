package com.lango.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.FaultRecordMapper;
import com.lango.demo.pojo.FaultRecord;
import com.lango.demo.service.IFaultRecordService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
public class FaultRecordService implements IFaultRecordService {

	private Logger logger = LoggerFactory.getLogger(CheckRecordService.class);

	@Autowired
	private FaultRecordMapper faultRecordMapper;

	@Override
	public List<FaultRecord> selectFaultRecords(FaultRecord faultRecord) {
		logger.info("查询故障记录,参数为:" + faultRecord);
		// TODO Auto-generated method stub
		return faultRecordMapper.selectFaultRecords(faultRecord);
	}

	@Override
	public boolean deleteFaultRecord(Long fId) {
		// TODO Auto-generated method stub
		logger.info("删除故障记录,id为:" + fId);
		int judge = faultRecordMapper.deleteFaultRecord(fId);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertFaultRecord(FaultRecord faultRecord) {
		// TODO Auto-generated method stub
		logger.info("添加故障记录,参数为:" + faultRecord);
		int judge = faultRecordMapper.insertFaultRecord(faultRecord);
		if (judge > 0)
			return true;
		return false;
	}

}
