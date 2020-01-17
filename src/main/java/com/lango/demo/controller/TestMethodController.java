package com.lango.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lango.demo.pojo.TestMethod;
import com.lango.demo.service.ITestMethodService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("TMController")
public class TestMethodController {

	@Autowired
	private ITestMethodService testMethodService;

	@PostMapping("query")
	public List<TestMethod> queryTestMethods(@RequestBody @NotNull TestMethod TestMethod) {
		return testMethodService.selectTestMethods(TestMethod);
	}

	@GetMapping
	public List<TestMethod> selectTestMethods() {
		TestMethod TestMethod = new TestMethod();
		return testMethodService.selectTestMethods(TestMethod);
	}

	@PostMapping("queryByIds")
	public List<TestMethod> queryTestMethodByIds(@RequestBody @NotNull String ids) {

		JSONObject jsonObject = JSONObject.parseObject(ids);
		String idString = (String) jsonObject.get("ids");
		return testMethodService.selectTestingMethodsByIds(idString);
	}

	@PostMapping("insert")
	public boolean insertTestMethod(@RequestBody @NotNull TestMethod TestMethod) {
		return testMethodService.insertTestMethod(TestMethod);
	}

	@PutMapping
	public boolean updateTestMethod(@RequestBody @NotNull TestMethod TestMethod) {
		return testMethodService.updateTestMethod(TestMethod);
	}

	@DeleteMapping(value = "/{tId}")
	public boolean deleteTestMethod(@PathVariable("tId") Long tId) {
		return testMethodService.deleteTestMethod(tId);
	}
}
