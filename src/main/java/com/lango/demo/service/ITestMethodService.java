package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.TestMethod;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface ITestMethodService {
	public List<TestMethod> selectTestMethods(TestMethod testMethod);

	public boolean updateTestMethod(TestMethod testMethod);

	public boolean insertTestMethod(TestMethod testMethod);

	public boolean deleteTestMethod(Long tId);
	
	public List<TestMethod> selectTestingMethodsByIds(String ids);
}
