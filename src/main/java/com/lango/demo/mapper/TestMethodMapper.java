package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.TestMethod;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface TestMethodMapper {
	public List<TestMethod> selectTestMethods(TestMethod testMethod);

	public int updateTestMethod(TestMethod testMethod);

	public int insertTestMethod(TestMethod testMethod);

	public int deleteTestMethod(Long sId);
	
	public List<TestMethod> selectTestingMethodsByIds(List<Integer> list);
	
}
