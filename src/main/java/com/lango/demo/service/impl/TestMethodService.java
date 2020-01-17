package com.lango.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.TestMethodMapper;
import com.lango.demo.pojo.TestMethod;
import com.lango.demo.service.ITestMethodService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
public class TestMethodService implements ITestMethodService {

	private Logger logger = LoggerFactory.getLogger(CheckRecordService.class);

	@Autowired
	private TestMethodMapper testMethodMapper;

	@Override
	public List<TestMethod> selectTestMethods(TestMethod testMethod) {
		// TODO Auto-generated method stub
		logger.info("查询测试方法,参数为:" + testMethod);
		return testMethodMapper.selectTestMethods(testMethod);
	}

	@Override
	public boolean updateTestMethod(TestMethod testMethod) {
		// TODO Auto-generated method stub
		logger.info("更新测试方法,参数为:" + testMethod);
		int judge = testMethodMapper.updateTestMethod(testMethod);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertTestMethod(TestMethod testMethod) {
		// TODO Auto-generated method stub
		logger.info("添加测试方法,参数为:" + testMethod);
		int judge = testMethodMapper.insertTestMethod(testMethod);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteTestMethod(Long tId) {
		// TODO Auto-generated method stub
		logger.info("删除测试方法,id为:" + tId);
		
		
		int judge = testMethodMapper.deleteTestMethod(tId);
		if (judge > 0)
			return true;
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.lango.demo.service.ITestMethodService#selectTestingMethodsByIds(java
	 * .util.List)
	 */
	@Override
	public List<TestMethod> selectTestingMethodsByIds(String ids) {
		// TODO Auto-generated method stub
		//将传过来的测试方法id可能是字符串替换成数字,如["1","2"] => [1,2]
		ids = ids.replace("\"", "");
		ids = ids.substring(1, ids.length() - 1);
		String[] words2 = ids.split("\\,");
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < words2.length; i++) {
			lists.add(Integer.parseInt(words2[i]));
		}
		List<TestMethod> list = testMethodMapper.selectTestingMethodsByIds(lists);
		return list;
	}

}
