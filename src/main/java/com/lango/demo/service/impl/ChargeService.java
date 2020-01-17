package com.lango.demo.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.ChargePersonMapper;
import com.lango.demo.mapper.EquipmentMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.pojo.ChargePerson;
import com.lango.demo.pojo.Equipment;
import com.lango.demo.pojo.Server;
import com.lango.demo.service.IChargeService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
public class ChargeService implements IChargeService {

	private Logger logger = LoggerFactory.getLogger(ChargeService.class);

	@Autowired
	private ChargePersonMapper chargePersonMapper;

	@Autowired
	private ServerMapper serverMapper;

	@Autowired
	private EquipmentMapper equipmentMapper;

	@Override
	public List<ChargePerson> selectChargePersons(ChargePerson chargePerson) {
		// TODO Auto-generated method stub
		logger.info("查询负责人,参数为:" + chargePerson);
		List<ChargePerson> cList = chargePersonMapper.selectChargePersons(chargePerson);
		return cList;
	}

	@Override
	public boolean updateChargePerson(ChargePerson chargePerson) {
		// TODO Auto-generated method stub
		logger.info("更新负责人,参数为:" + chargePerson);
		int judge = chargePersonMapper.updateChargePerson(chargePerson);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertChargePerson(ChargePerson chargePerson) {
		// TODO Auto-generated method stub
		logger.info("添加负责人,参数为:" + chargePerson);
		int judge = chargePersonMapper.insertChargePerson(chargePerson);
		if (judge > 0)
			return true;
		return false;
	}

	/**
	 * 删除负责人前需要判断该负责人是否有负责设备和服务,如果有,就不能删除
	 */
	@Override
	public boolean deleteChargePerson(Long cId) {
		// TODO Auto-generated method stub
		Server server = new Server();
		server.setcId(cId.intValue());
		System.out.println(server);
		List<Server> servers = serverMapper.selectServers(server);
		if (servers.size() > 0) {
			logger.error("负责人id为" + cId + "的负责人删除失败,该负责人负责了以下服务:" + servers.toString());
			return false;
		}

		Equipment equipment = new Equipment();
		equipment.setcId(cId.intValue());
		if (equipmentMapper.selectEquipments(equipment).size() > 0) {
			logger.error("负责人id为" + cId + "的负责人删除失败,该负责人负责了以下设备:" + servers.toString());
			return false;
		}

		int judge = chargePersonMapper.deleteChargePerson(cId);

		if (judge > 0) {
			logger.info("负责人id为:" + cId + "的负责人删除成功");
			return true;
		}
		return false;
	}

}
