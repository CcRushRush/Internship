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

import com.lango.demo.pojo.Equipment;
import com.lango.demo.service.IEquipmentService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("equipmentController")
public class EquipmentController {
	@Autowired
	private IEquipmentService equipmentService;

	@PostMapping("query")
	public List<Equipment> queryEquipments(@RequestBody @NotNull Equipment equipment) {
		return equipmentService.selectEquipments(equipment);
	}

	@GetMapping
	public List<Equipment> selectEquipments() {
		Equipment equipment = new Equipment();
		return equipmentService.selectEquipments(equipment);
	}

	@PostMapping("insert")
	public boolean insertEquipment(@RequestBody @NotNull Equipment equipment) {
		return equipmentService.insertEquipment(equipment);
	}

	@PutMapping
	public boolean updateEquipment(@RequestBody @NotNull Equipment equipment) {
		return equipmentService.updateEquipment(equipment);
	}

	@DeleteMapping(value = "/{eId}")
	public boolean deleteEquipment(@PathVariable("eId") Long eId) {
		return equipmentService.deleteEquipment(eId);
	}
}
