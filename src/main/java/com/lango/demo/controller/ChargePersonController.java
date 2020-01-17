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

import com.lango.demo.pojo.ChargePerson;
import com.lango.demo.service.IChargeService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("CPController")
public class ChargePersonController {
	@Autowired
	private IChargeService chargeService;

	/**
	 * 主要是用于获取负责人
	 * 
	 * @param chargePerson
	 *            负责人bean
	 * @return
	 */
	@PostMapping("query")
	public List<ChargePerson> queryChargePersons(@RequestBody @NotNull ChargePerson chargePerson) {
		System.out.println(chargePerson);
		return chargeService.selectChargePersons(chargePerson);
	}

	@GetMapping
	public List<ChargePerson> selectChargePersons() {
		ChargePerson chargePerson = new ChargePerson();
		return chargeService.selectChargePersons(chargePerson);
	}

	@PostMapping("insert")
	public boolean insertChargePerson(@RequestBody @NotNull ChargePerson chargePerson) {
		return chargeService.insertChargePerson(chargePerson);
	}

	@PutMapping
	public boolean updateChargePerson(@RequestBody @NotNull ChargePerson chargePerson) {
		return chargeService.updateChargePerson(chargePerson);
	}

	@DeleteMapping(value = "/{cId}")
	public String deleteChargePerson(@PathVariable("cId") Long cId) {
		return chargeService.deleteChargePerson(cId);
	}
}
