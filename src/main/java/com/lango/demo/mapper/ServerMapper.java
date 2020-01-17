package com.lango.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lango.demo.pojo.Server;
/** 
 * @author: cc 
 * @version: 2020年1月16日 下午3:07:49 
 */
@Mapper
public interface ServerMapper {

	public List<Server> selectServers(Server server);

	public int updateServer(Server server);

	public int insertServer(Server server);

	public int deleteServer(Long sId);
	
	public int deleteServerByEId(Long eId);
}
