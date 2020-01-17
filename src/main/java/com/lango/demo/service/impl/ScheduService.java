package com.lango.demo.service.impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lango.demo.mapper.ChargePersonMapper;
import com.lango.demo.mapper.ServerMapper;
import com.lango.demo.pojo.Schedule;
import com.lango.demo.pojo.Server;
import com.lango.demo.service.IServerService;
import com.lango.demo.util.TimeDiff;

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

	// 针对每一个服务都可以设置对应的自动检测时间
	public boolean changeSchedule(int sId, int time) {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		Schedule schedule = new Schedule();
		schedule.setsId(sId);
		schedule.setLastDateTiem(new Date());
		schedule.setInterval(time);
		request.getSession().setAttribute(String.valueOf(sId), schedule);
		return true;
	}

	//开启当个任务自动检测
	@Scheduled(cron = "0 */1 * * * ?")
	public void startScheduTask() {
		RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
		Server server = new Server();
		server.setAutoNotice(1);
		logger.info("获取需要进行自动检测的服务");
		// 获取需要自动检测的服务
		List<Server> servers = serverMapper.selectServers(server);
		for (Server server2 : servers) {
			
			Schedule schedule = (Schedule) request.getSession().getAttribute(String.valueOf(server2.getsId()));
			//判断该服务是否有定时任务
			if (schedule == null) {
				continue;
			}
			long diff = TimeDiff.minuteDiff(schedule.getLastDateTiem());// 这样得到的差值是分钟
			//判断是否可以开启定时任务,如果时间差超过设定的时间差,就可以执行相应的操作
			if (schedule.getInterval() <= diff) {
				logger.info("正在执行服务:"+server.getsName()+"自动检测");
				// 开始自动检测
				List<String> lists = serverService.testServer(server2);
				// 根据结果判断是否有问题,如果有问题执行以下操作进行修复,修复方法是上次保存在数据的
				logger.info("服务:"+server.getsName()+"自动检测结束");
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
				schedule.setLastDateTiem(new Date());
				request.getSession().setAttribute(String.valueOf(server2.getsId()), schedule);
			}
			
		}
	}
}
