package com.lango.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lango.demo.service.impl.ScheduService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("scheduController")
public class ScheduController {

	@Autowired
	private ScheduService scheduService;

	@PutMapping
	private boolean changeSchedu(@RequestBody String object) {
		JSONObject jsonObject = JSONObject.parseObject(object);
		int minutes = Integer.parseInt((String) jsonObject.get("minutes"));
		scheduService.setCron(minutes);
		return true;
	}
}
