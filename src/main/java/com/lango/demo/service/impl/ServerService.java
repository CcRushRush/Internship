package com.lango.demo.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.lango.demo.mapper.CheckRecordMapper;
import com.lango.demo.mapper.FaultRecordMapper;
import com.lango.demo.mapper.RepairMethodMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.mapper.TestMethodMapper;
import com.lango.demo.pojo.CheckRecord;
import com.lango.demo.pojo.FaultRecord;
import com.lango.demo.pojo.RepairMethod;
import com.lango.demo.pojo.Server;
import com.lango.demo.pojo.TestMethod;
import com.lango.demo.service.IServerService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
public class ServerService implements IServerService {

	private Logger logger = LoggerFactory.getLogger(ServerService.class);

	@Autowired
	private ServerMapper serverMapper;

	@Autowired
	private RepairMethodMapper repairMethodMapper;

	@Autowired
	private CheckRecordMapper checkRecordMapper;

	@Autowired
	private TestMethodMapper testMethodMapper;

	@Autowired
	private FaultRecordMapper faultRecordMapper;

	@Override
	public List<Server> selectServers(Server server) {
		// TODO Auto-generated method stub
		return serverMapper.selectServers(server);
	}

	@Override
	public boolean updateServer(Server server) {
		// TODO Auto-generated method stub
		int judge = serverMapper.updateServer(server);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean insertServer(Server server) {
		// TODO Auto-generated method stub
		int judge = serverMapper.insertServer(server);
		if (judge > 0)
			return true;
		return false;
	}

	@Override
	public boolean deleteServer(Long sId) {
		// TODO Auto-generated method stub
		int judge = serverMapper.deleteServer(sId);
		if (judge > 0)
			return true;
		return false;
	}

	// 根据选择的修复方法进行修复
	@Override
	public boolean repairServer(Server server) {
		logger.info("正在自动修复服务:" + server.getsName());
		//将传过来的修复方法id可能是字符串替换成数字,如["1","2"] => [1,2]
		String ids = server.getRepairMethodIds();
		ids = ids.replace("\"", "");
		ids = ids.substring(1, ids.length() - 1);
		String[] words2 = ids.split("\\,");
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < words2.length; i++) {
			lists.add(Integer.parseInt(words2[i]));
		}
		// 根据方法id获取修复方法
		List<RepairMethod> repairMethods = repairMethodMapper.selectMethodsByIds(lists);
		// 保存每次修复结果
		List<String> repairResults = new ArrayList<>();
		for (Iterator<RepairMethod> iterator = repairMethods.iterator(); iterator.hasNext();) {
			RepairMethod repairMethod = iterator.next();
			logger.info("正在执行修复方法:" + repairMethod.getrMethod());
			// 修复方法
			String result = "success";

			logger.info("修复方法:" + repairMethod.getrMethod() + ";的修复结果为" + result);

			/**
			 * 这里可以针对每次修复后,重新进行检测,检测后再做判断
			 */

			// 将修复结果保存
			repairResults.add(result);
		}
		// 将结果通知负责人
		logger.info("将服务id为" + server.getsId() + "的总的修复结果通知给负责人:" + server.getChargePerson());
		CheckRecord checkRecord = new CheckRecord();
		checkRecord.setsId(server.getsId());
		checkRecord.seteId(server.geteId());
		checkRecord.setRecordMessage(String.valueOf(repairResults));
		// 将修复记录保存到数据库
		checkRecordMapper.insertCheckRecord(checkRecord);

		return true;
	}

	// 这个主要是返回测试结果
	@Override
	public List<String> testServer(Server server) {
		logger.info("服务名为:" + server.getsName() + " 的服务开始检测");
		String ids = server.getTestMethodIds();
		if (ids == null) {
			return null;
		}
		//将传过来的测试方法id可能是字符串替换成数字,如["1","2"] => [1,2]
		ids = ids.replace("\"", "");
		System.out.println(ids);
		ids = ids.substring(1, ids.length() - 1);
		String[] words2 = ids.split("\\,");
		List<Integer> lists = new ArrayList<>();
		for (int i = 0; i < words2.length; i++) {
			lists.add(Integer.parseInt(words2[i]));
		}
		
		// 根据方法id获取检测方法
		List<TestMethod> testMethods = testMethodMapper.selectTestingMethodsByIds(lists);

		// 获取配置信息
		String configurations = server.getsMessage();
		if (configurations == null) {
			return null;
		}
		// 将configurations 转成json对象
		
//		JSONObject object = JSONObject.parseObject(configurations);
//		
//		// 开始遍历所有key-value
//		// fastjson解析方法
//		System.out.println("服务名为:" + server.getsId() + "的配置信息如下:");
//		for (Map.Entry<String, Object> entry : object.entrySet()) {
//			// 这里主要对配置信息取出,然后放入到相应的方法中进行检测
//			System.out.println(entry.getKey() + ":" + entry.getValue());
//		}
		// 保存每次测试结果
		List<String> testResults = new ArrayList<>();
		for (Iterator<TestMethod> iterator = testMethods.iterator(); iterator.hasNext();) {
			TestMethod testMethod = iterator.next();
			logger.info("服务名为:" + server.getsName() + " 正在执行的检测方法为:" + testMethod.gettMethod());

			/**
			 * 如果检测出现问题可以执行以下两种方式 1.不做修复,等到所有检测方法结束后,直接将结果通知负责人
			 * 2.针对每一个检测出现故障均做修复,可以是针对性的,主要在创建修复方法时定义好
			 */

			String result1 = "error";

			logger.info("服务名为:" + server.getsName() + ",执行的检测方法为:" + testMethod.gettMethod() + "的结果为:" + result1);

			// 假设出现故障,将故障保存到故障记录表
			FaultRecord faultRecord = new FaultRecord();
			faultRecord.setChargePerson(server.getChargePerson());
			faultRecord.setcId(server.getcId());
			faultRecord.seteId(server.geteId());
			faultRecord.setfType("important");
			faultRecord.setMethodIds(server.getRepairMethodIds());
			faultRecord.setResult("result");
			faultRecord.setsId(server.getsId());
			faultRecordMapper.insertFaultRecord(faultRecord);

			// 保存每一次测试结果到检测记录
			testResults.add("success");
		}

		logger.info("正在将服务为:" + server.getsName() + "的检测结果通知负责人:" + server.getChargePerson());

		// 将这一次总的测试结果保存的检测记录表
		CheckRecord checkRecord = new CheckRecord();
		checkRecord.setsId(server.getsId());
		checkRecord.seteId(server.geteId());
		checkRecord.setRecordMessage(String.valueOf(testResults));
		checkRecordMapper.insertCheckRecord(checkRecord);
		return testResults;
	}

}
