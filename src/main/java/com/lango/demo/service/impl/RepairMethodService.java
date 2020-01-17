package com.lango.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.RepairMethodMapper;
import com.lango.demo.pojo.RepairMethod;
import com.lango.demo.service.IRepairMethodService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
public class RepairMethodService implements IRepairMethodService {

	private Logger logger = LoggerFactory.getLogger(CheckRecordService.class);

	@Autowired
	private RepairMethodMapper repairMethodMapper;

	@Override
	public List<RepairMethod> selectRepairMethods(RepairMethod repairMethod) {
		// TODO Auto-generated method stub
		logger.info("查询修复方法,参数为:" + repairMethod);
		return repairMethodMapper.selectRepairMethods(repairMethod);
	}

	@Override
	public boolean updateRepairMethod(RepairMethod repairMethod) {
		// TODO Auto-generated method stub
		logger.info("更新修复方法,参数为:" + repairMethod);
		int judge = repairMethodMapper.updateRepairMethod(repairMethod);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertRepairMethod(RepairMethod repairMethod) {
		// TODO Auto-generated method stub
		logger.info("添加修复方法,参数为:" + repairMethod);
		int judge = repairMethodMapper.insertRepairMethod(repairMethod);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteRepairMethod(Long rId) {
		// TODO Auto-generated method stub
		logger.info("删除修复方法,id为:" + rId);
		int judge = repairMethodMapper.deleteRepairMethod(rId);
		if (judge > 0)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lango.demo.service.IRepairMethodService#selectMethodsByIds(java.lang
	 * .String)
	 */
	@Override
	public List<RepairMethod> selectMethodsByIds(String ids) {
		// TODO Auto-generated method stub
		//将传过来的修复方法id可能是字符串替换成数字,如["1","2"] => [1,2]
		logger.info("查询修复方法,id分别为:"+ids);
		ids = ids.replace("\"", "");
		ids = ids.substring(1, ids.length() - 1);
		String[] words2 = ids.split("\\,");
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < words2.length; i++) {
			lists.add(Integer.parseInt(words2[i]));
		}
		return repairMethodMapper.selectMethodsByIds(lists);
	}

}
