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

import com.lango.demo.pojo.FaultRecord;
import com.lango.demo.service.IFaultRecordService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("FRController")
public class FaultRecordController {

	@Autowired
	private IFaultRecordService faultRecordService;

	@PostMapping
	public List<FaultRecord> queryFaultRecord(@RequestBody @NotNull FaultRecord faultRecord) {
		return faultRecordService.selectFaultRecords(faultRecord);
	}

	@GetMapping
	public List<FaultRecord> selectFaultRecord() {
		FaultRecord faultRecord = new FaultRecord();
		return faultRecordService.selectFaultRecords(faultRecord);
	}

	// @PostMapping("insert")
	// public boolean insertFaultRecord(@RequestBody @NotNull FaultRecord
	// faultRecord){
	// return faultRecordService.insertFaultRecord(faultRecord);
	// }

	@DeleteMapping(value = "/{fId}")
	public boolean deleteFaultRecord(@PathVariable("fId") Long fId) {
		return faultRecordService.deleteFaultRecord(fId);
	}
}
