package com.lango.demo.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;

import com.lango.demo.mapper.ChargePersonMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.pojo.Server;
import com.lango.demo.service.IServerService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@Service
// @EnableScheduling
public class ScheduService implements SchedulingConfigurer {

	private Logger logger = LoggerFactory.getLogger(ScheduService.class);

	@Autowired
	private ChargePersonMapper chargePersonMapper;

	@Autowired
	private IServerService serverService;

	@Autowired
	private ServerMapper serverMapper;

	private static final String DEFAULT_CRON = "0 */1 * * * ?";
	private String cron = DEFAULT_CRON;

	@Override
	public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
		taskRegistrar.addTriggerTask(() -> {
			// 定时任务的业务逻辑
				logger.info("=================开启定时任务======================");
				try {
					testInterface();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				logger.info("=================结束定时任务======================");
			}, (triggerContext) -> {
				// 定时任务触发，可修改定时任务的执行周期
				CronTrigger trigger = new CronTrigger(cron);
				Date nextExecDate = trigger.nextExecutionTime(triggerContext);
				return nextExecDate;
			});
	}

	public void setCron(int minutes) {
		logger.info("定时任务改变为:" + minutes + "分钟调用一次");
		this.cron = "0 */" + minutes + " * * * ?";
	}

	public String testInterface() throws IOException {

		Server server = new Server();
		server.setAutoNotice(1);
		logger.info("获取需要进行自动检测的服务");
		// 获取需要自动检测的服务
		List<Server> servers = serverMapper.selectServers(server);
		for (Server server2 : servers) {
			// 逐个自动检测
			List<String> lists = serverService.testServer(server2);
			// 根据结果判断是否有问题,如果有问题执行以下操作进行修复,修复方法是上次保存在数据的

			if (lists.size() > 0) {
				logger.info(server2.getsName() + "出现故障");
				if (server2.getAutoRepair() == 1) {
					serverService.repairServer(server2);
				} else {
					logger.info(server2.getsName() + "没有设置自动修复");
				}
			} else {
				logger.info(server2.getsName() + "没有出现故障");
			}
		}
		return "success";
	}
}
