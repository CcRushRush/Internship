package com.lango.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.EquipmentMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.pojo.Equipment;
import com.lango.demo.service.IEquipmentService;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Service
public class EquipmentService implements IEquipmentService{

	private Logger logger = LoggerFactory.getLogger(EquipmentService.class);
	
	@Autowired
	private EquipmentMapper equipmentMapper;
	
	@Autowired
	private ServerMapper serverMapper;
	
	@Override
	public List<Equipment> selectEquipments(Equipment equipment) {
		logger.info("查询设备,参数为:"+equipment);
		return equipmentMapper.selectEquipments(equipment);
	}

	@Override
	public boolean updateEquipment(Equipment equipment) {
		logger.info("更新设备,参数为:"+equipment);
		int judge = equipmentMapper.updateEquipment(equipment);
		if(judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertEquipment(Equipment equipment) {
		logger.info("添加设备,参数为:"+equipment);
		int judge = equipmentMapper.insertEquipment(equipment);
		if(judge > 0)
			return true;
		return false;
	}

	/**
	 * 删除一个设备,先把设备上的服务都删除
	 */
	@Override
	public boolean deleteEquipment(Long eId) {
		logger.info("删除设备,id为:"+eId);
		serverMapper.deleteServerByEId(eId);
		int judge = equipmentMapper.deleteEquipment(eId);
		if(judge > 0)
			return true;
		return false;
	}

}
