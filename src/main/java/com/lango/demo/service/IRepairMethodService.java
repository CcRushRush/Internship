package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.RepairMethod;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface IRepairMethodService {

	public List<RepairMethod> selectRepairMethods(RepairMethod repairMethod);

	public boolean updateRepairMethod(RepairMethod repairMethod);

	public boolean insertRepairMethod(RepairMethod repairMethod);

	public boolean deleteRepairMethod(Long rId);
	
	public List<RepairMethod> selectMethodsByIds(String ids);
}
