package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.Equipment;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface EquipmentMapper {

	public List<Equipment> selectEquipments(Equipment equipment);

	public int updateEquipment(Equipment equipment);

	public int insertEquipment(Equipment equipment);

	public int deleteEquipment(Long eId);
}
