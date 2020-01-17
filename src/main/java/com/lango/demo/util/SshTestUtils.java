package com.lango.demo.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

/**
 * @author: cc
 * @version: 2020年1月17日 下午1:29:16
 */
public class SshTestUtils {
	
	
	/**
	 * 发送ssh命令
	 * @param host 服务器ip
	 * @param port 端口
	 * @param userName 用户名
	 * @param password 密码
	 * @param command  需要发送的ssh命令
	 * @return ssh连接发送命令放回的结果
	 * @throws JSchException
	 * @throws IOException
	 */
	public static String sshTest(String host, int port, String userName, String password, String command) throws JSchException, IOException {

		JSch jsch = new JSch();
        Session session = jsch.getSession(userName, host, port);
        System.out.println(session);
        session.setConfig("StrictHostKeyChecking","no");
        session.setPassword(password);
        session.setTimeout(6000);
        session.connect();
        //建立连接结束
        //发送指令
        System.out.println(command);
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        InputStream in = channelExec.getInputStream();
        channelExec.setCommand(command);
        channelExec.connect();
        String s = IOUtils.toString(in, "UTF-8");
        System.out.println("结果："+s);
        in.close();
        channelExec.disconnect();  
        session.disconnect(); 
		return s;
	}
}
