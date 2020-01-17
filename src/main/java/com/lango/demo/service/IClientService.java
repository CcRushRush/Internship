package com.lango.demo.service;

import java.util.Map;

/**
 * @author: cc
 * @version: 2020年1月17日 下午3:20:12
 */
public interface IClientService {

	//ping url 是否连得通
	public boolean httpPing(String url);

	/**
	 * get请求
	 * @param url 请求路径
	 * @param headMap 请求头
	 * @return
	 */
	public String httpGet(String url, Map<String, String> headMap);

	/**
	 * Post请求
	 * @param url 请求api接口
	 * @param params  请求参数,参数为字符串
	 * @param headMap  请求头
	 * @return
	 */
	public String httpPost(String url, String params, Map<String, String> headMap);

	/**
	 * Put请求
	 * @param url 请求路径
	 * @param params 请求参数,参数为字符串
	 * @param headMap 请求头
	 * @return
	 */
	public String httpPut(String url, String params, Map<String, String> headMap);

	/**
	 * delete 请求
	 * @param url 请求路径
	 * @param headMap 请求头
	 * @return
	 */
	public String httpDelete(String url, Map<String, String> headMap);
}
