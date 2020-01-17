package com.lango.demo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.lango.demo.mapper.CheckRecordMapper;
import com.lango.demo.mapper.FaultRecordMapper;
import com.lango.demo.mapper.RepairMethodMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.mapper.TestMethodMapper;
import com.lango.demo.pojo.CheckRecord;
import com.lango.demo.pojo.FaultRecord;
import com.lango.demo.pojo.RepairMethod;
import com.lango.demo.pojo.Schedule;
import com.lango.demo.pojo.Server;
import com.lango.demo.pojo.TestMethod;
import com.lango.demo.service.IServerService;
import com.lango.demo.util.SendMessageUtils;

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
		logger.info("查询服务,参数为:" + server);
		return serverMapper.selectServers(server);
	}

	@Override
	public boolean updateServer(Server server) {
		// TODO Auto-generated method stub
		logger.info("更新服务,参数为:" + server);
		int judge = serverMapper.updateServer(server);
		if (judge > 0){
			//如果自动检测设为否,需要将原先保存到服务器的数据至空
			if (server.getAutoNotice() == 0) {
				RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
				HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
				request.getSession().setAttribute(String.valueOf(server.getsId()), null);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean insertServer(Server server) {
		// TODO Auto-generated method stub
		logger.info("添加服务,参数为:" + server);
		int judge = serverMapper.insertServer(server);
		if (judge > 0) {
			if (server.getAutoNotice() == 1) {
				RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
				HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();

				Schedule schedule = new Schedule();
				schedule.setInterval(30); // 默认定时任务为30分钟
				schedule.setLastDateTiem(new Date());
				schedule.setsId(server.getsId());
				request.getSession().setAttribute(String.valueOf(server.getsId()), schedule);
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean deleteServer(Long sId) {
		// TODO Auto-generated method stub
		logger.info("删除服务,服务id为:" + sId);
		int judge = serverMapper.deleteServer(sId);
		if (judge > 0)
			return true;
		return false;
	}

	// 根据选择的修复方法进行修复
	@Override
	public List<String> repairServer(Server server) {
		logger.info("正在自动修复服务:" + server.getsName());
		// 将传过来的修复方法id可能是字符串替换成数字,如["1","2"] => [1,2]
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

		// 获取配置信息
		String configurations = server.getsMessage();
		System.out.println(configurations);
		if (configurations == null) {
			logger.info("服务名为:" + server.getsName() + " 的服务的配置信息为空");
			List<String> lStrings = new ArrayList<>();
			lStrings.add("服务名为:" + server.getsName() + " 的服务的配置信息为空");
			return lStrings;
		}
		System.out.println("服务名为:" + server.getsId() + "的配置信息如下:");
		// 将configurations 转成数组
		List<Object> list = JSONObject.parseArray(configurations);
		for (Object object : list) {
			JSONObject jsonObject = (JSONObject) JSONObject.parse(object.toString());
			System.out.println(jsonObject.get("key") + ":" + jsonObject.get("value"));

		}

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
		SendMessageUtils sendMessageUtils = SendMessageUtils.getInstance();
		sendMessageUtils.sendMessageToChargePerson(server.getcId(), String.valueOf(repairResults));

		logger.info("将服务id为" + server.getsId() + "的总的修复结果通知给负责人:" + server.getChargePerson());
		CheckRecord checkRecord = new CheckRecord();
		checkRecord.setsId(server.getsId());
		checkRecord.seteId(server.geteId());
		checkRecord.setRecordMessage(String.valueOf(repairResults));
		// 将修复记录保存到数据库
		checkRecordMapper.insertCheckRecord(checkRecord);

		return repairResults;
	}

	// 这个主要是返回测试结果
	@Override
	public List<String> testServer(Server server) {
		logger.info("服务名为:" + server.getsName() + " 的服务开始检测");
		String ids = server.getTestMethodIds();
		if (ids == null) {
			logger.info("服务名为:" + server.getsName() + " 的服务的检测方法为空");
			List<String> lStrings = new ArrayList<>();
			lStrings.add("服务名为:" + server.getsName() + " 的服务的检测方法为空");
			return lStrings;
		}
		// 将传过来的测试方法id可能是字符串替换成数字,如["1","2"] => [1,2]
		ids = ids.replace("\"", "");
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
		System.out.println(configurations);
		if (configurations == null) {
			logger.info("服务名为:" + server.getsName() + " 的服务的配置信息为空");
			List<String> lStrings = new ArrayList<>();
			lStrings.add("服务名为:" + server.getsName() + " 的服务的配置信息为空");
			return lStrings;
		}
		System.out.println("服务名为:" + server.getsId() + "的配置信息如下:");
		// 将configurations 转成数组
		List<Object> list = JSONObject.parseArray(configurations);
		for (Object object : list) {
			JSONObject jsonObject = (JSONObject) JSONObject.parse(object.toString());
			System.out.println(jsonObject.get("key") + ":" + jsonObject.get("value"));

		}

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
		SendMessageUtils sendMessageUtils = SendMessageUtils.getInstance();
		sendMessageUtils.sendMessageToChargePerson(server.getcId(), String.valueOf(testResults));

		// 将这一次总的测试结果保存的检测记录表
		CheckRecord checkRecord = new CheckRecord();
		checkRecord.setsId(server.getsId());
		checkRecord.seteId(server.geteId());
		checkRecord.setRecordMessage(String.valueOf(testResults));
		checkRecordMapper.insertCheckRecord(checkRecord);
		return testResults;
	}

}
