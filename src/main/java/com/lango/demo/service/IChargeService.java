package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.ChargePerson;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface IChargeService {
	
	public List<ChargePerson> selectChargePersons(ChargePerson chargePerson);
	
	public boolean updateChargePerson(ChargePerson chargePerson);
	
	public boolean insertChargePerson(ChargePerson chargePerson);
	
	public boolean deleteChargePerson(Long cId);
}
