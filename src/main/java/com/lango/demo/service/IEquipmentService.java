package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.Equipment;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface IEquipmentService {

	public List<Equipment> selectEquipments(Equipment equipment);

	public boolean updateEquipment(Equipment equipment);

	public boolean insertEquipment(Equipment equipment);

	public boolean deleteEquipment(Long eId);
}
