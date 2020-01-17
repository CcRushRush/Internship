package com.lango.demo.service;

import java.util.List;

import com.lango.demo.pojo.Server;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
public interface IServerService {
	
	public List<Server> selectServers(Server server);

	public boolean updateServer(Server server);

	public boolean insertServer(Server server);

	public boolean deleteServer(Long sId);
	
	public List<String> repairServer(Server server);
	
	public List<String> testServer(Server server);
}
