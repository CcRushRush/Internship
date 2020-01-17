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
import com.lango.demo.pojo.RepairMethod;
import com.lango.demo.service.IRepairMethodService;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@RestController
@RequestMapping("RMController")
public class RepairMethodController {

	@Autowired
	private IRepairMethodService repairMethodService;
	
	@PostMapping("query")
	public List<RepairMethod> selectRepairMethods(@RequestBody @NotNull RepairMethod repairMethod){
		return repairMethodService.selectRepairMethods(repairMethod);
	}
	
	@PostMapping("queryByIds")
	public List<RepairMethod> selectRepairMethodByIds(@RequestBody @NotNull String ids){
		JSONObject jsonObject = JSONObject.parseObject(ids);
		String idString = (String) jsonObject.get("ids");
		return repairMethodService.selectMethodsByIds(idString);
	}
	
	@GetMapping
	public List<RepairMethod> selectRepairMethods(){
		RepairMethod repairMethod = new RepairMethod();
		return repairMethodService.selectRepairMethods(repairMethod);
	}
	
	@PostMapping("insert")
	public boolean insertRepairMethod(@RequestBody @NotNull RepairMethod repairMethod){
		return repairMethodService.insertRepairMethod(repairMethod);
	}
	
	@PutMapping
	public boolean updateRepairMethod(@RequestBody @NotNull RepairMethod repairMethod){
		return repairMethodService.updateRepairMethod(repairMethod);
	}
	
	@DeleteMapping(value = "/{rId}")
	public boolean deleteRepairMethod(@PathVariable("rId") Long tId ){
		return repairMethodService.deleteRepairMethod(tId);
	}
}
