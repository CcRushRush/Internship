package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.RepairMethod;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface RepairMethodMapper {
	public List<RepairMethod> selectRepairMethods(RepairMethod repairMethod);

	public int updateRepairMethod(RepairMethod repairMethod);

	public int insertRepairMethod(RepairMethod repairMethod);

	public int deleteRepairMethod(Long rId);
	
	public List<RepairMethod> selectMethodsByIds(List<Integer> list);
	
}
