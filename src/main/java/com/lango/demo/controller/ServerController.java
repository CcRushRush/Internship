package com.lango.demo.controller;

import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lango.demo.pojo.Server;
import com.lango.demo.service.IServerService;

/**
 * @author: cc
 * @version: 2020年1月16日 下午3:07:49
 */
@RestController
@RequestMapping("serverController")
public class ServerController {
	@Autowired
	private IServerService serverService;

	@PostMapping("query")
	public List<Server> queryServers(@RequestBody @NotNull Server server) {
		System.out.print(server);
		return serverService.selectServers(server);
	}

	@GetMapping
	public List<Server> selectServers() {
		Server server = new Server();
		return serverService.selectServers(server);
	}

	@PostMapping(value = "insertServer")
	public boolean insertServer(@RequestBody @NotNull Server server) {
		return serverService.insertServer(server);
	}

	@PostMapping(value = "repairServer")
	public boolean repairServer(@RequestBody @NotNull Server server) {
		return serverService.repairServer(server);
	}

	@PostMapping(value = "testServer")
	public List<String> testServer(@RequestBody @NotNull Server server) {
		return serverService.testServer(server);
	}

	@PutMapping
	public boolean updateServer(@RequestBody @NotNull Server server) {
		return serverService.updateServer(server);
	}

	@DeleteMapping(value = "/{sId}")
	public boolean deleteServer(@PathVariable("sId") Long sId) {
		return serverService.deleteServer(sId);
	}
}
