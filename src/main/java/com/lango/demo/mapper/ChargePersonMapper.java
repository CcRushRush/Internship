package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.ChargePerson;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface ChargePersonMapper {
	
	public List<ChargePerson> selectChargePersons(ChargePerson chargePerson);
	
	public int updateChargePerson(ChargePerson chargePerson);
	
	public int insertChargePerson(ChargePerson chargePerson);
	
	public int deleteChargePerson(Long cId);
}
