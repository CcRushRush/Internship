package com.lango.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.lango.demo.pojo.ChargePerson;
import com.lango.demo.service.IClientService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:12:35
 */
@RestController
@RequestMapping("testController")
public class TestController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping
	public void test() {
		// 用于检测
		Map<String, String> map = new HashMap<>();
		ChargePerson chargePerson = new ChargePerson();
		chargePerson.setcId(1);
		map.put("ChargePerson", JSONObject.toJSONString(chargePerson));

		Map<String, String> headMap = new HashMap<>();
		headMap.put("Content-Type", "application/json;charset=UTF-8");
		headMap.put("Accept", "application/json");

//		HttpClientUtils clientUtils = HttpClientUtils.getInstance();
		String url = "http://192.168.1.155:8888/CPController/query";
		// post 请求
		String result = clientService.httpPost(url,
				JSONObject.toJSONString(chargePerson), headMap);
		System.out.println("post请求结果:" + result);

		// get 请求
		result = clientService.httpGet("http://192.168.1.155:8888/CPController",
				null);
		System.out.println("get请求结果:" + result);

		chargePerson.setAccount("aaaa");
		chargePerson.setcName("aaaaa");
		chargePerson.setEmail("8366@qq.com");
		chargePerson.setPassword("aaaa");
		chargePerson.setPhone("15932469741");
		// put 请求
		result = clientService.httpPut("http://192.168.1.155:8888/CPController", JSONObject.toJSONString(chargePerson), headMap);
		System.out.println("put请求结果:" + result);
		
		// delete请求
		result = clientService.httpDelete("http://192.168.1.155:8888/CPController/2", headMap);
		System.out.println("delete请求结果:" + result);
		
//		//ssh指令
//		try {
//			String string = SshTestUtils.sshTest("127.0.0.1", 3317, "root", "123456", "select * from charge_person");
//			System.out.println(string);
//		} catch (JSchException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}
}
