package com.lango.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lango.demo.pojo.CheckRecord;
import com.lango.demo.service.ICheckRecordService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("CRController")
public class CheckRecordController {

	@Autowired
	private ICheckRecordService checkRecordService;

	@PostMapping
	public List<CheckRecord> queryCheckRecords(@RequestBody @NotNull CheckRecord checkRecord) {
		return checkRecordService.selectCheckRecords(checkRecord);
	}

	@GetMapping
	public List<CheckRecord> selectCheckRecords() {
		CheckRecord checkRecord = new CheckRecord();
		return checkRecordService.selectCheckRecords(checkRecord);
	}

	// @PostMapping("insert")
	// public boolean insertCheckRecord(@RequestBody @NotNull CheckRecord
	// checkRecord){
	// return checkRecordService.insertCheckRecord(checkRecord);
	// }

	@DeleteMapping(value = "/{checkId}")
	public boolean deleteCheckRecord(@PathVariable("checkId") Long checkId) {
		return checkRecordService.deleteCheckRecord(checkId);
	}
}
