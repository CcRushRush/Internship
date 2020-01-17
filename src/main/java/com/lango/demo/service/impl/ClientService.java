package com.lango.demo.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.lango.demo.service.IClientService;

/** 
 * @author: cc 
 * @version: 2020年1月17日 下午3:30:35 
 */

@Service
public class ClientService implements IClientService{

	/* (non-Javadoc)
	 * @see com.lango.demo.service.IClientService#httpPing(java.lang.String)
	 */
	private static void setHeader(Object object, Map<String, String> headMap) {
		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				((HttpMessage) object).addHeader(key, headMap.get(key));
			}
		}
	}
	
	@Override
	public boolean httpPing(String url) {
		// TODO Auto-generated method stub
		boolean isPass = false;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(url);
			response = httpclient.execute(httpGet);
			isPass = true;
		} catch (Exception e) {
			//ping 不通
		} finally {
			try {
				if(response != null){
					response.close();
				}
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isPass;
	}

	/* (non-Javadoc)
	 * @see com.lango.demo.service.IClientService#httpGet(java.lang.String, java.util.Map)
	 */
	@Override
	public String httpGet(String url, Map<String, String> headMap) {
		// TODO Auto-generated method stub
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			setHeader(httpGet, headMap);
			try {
				//System.out.println(response1.getStatusLine());
				HttpEntity entity = response1.getEntity();
				InputStream is = entity.getContent();
				StringBuffer strBuf = new StringBuffer();
				byte[] buffer = new byte[4096];
				int r = 0;
				while ((r = is.read(buffer)) > 0) {
					strBuf.append(new String(buffer, 0, r, "UTF-8"));
				}
				responseContent = strBuf.toString();
				//System.out.println("debug:" + responseContent);
				EntityUtils.consume(entity);
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return responseContent;
	}

	/* (non-Javadoc)
	 * @see com.lango.demo.service.IClientService#httpPost(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public String httpPost(String url, String params, Map<String, String> headMap) {
		// TODO Auto-generated method stub
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			
			setHeader(httpPost,headMap);
	        
	        StringEntity stringEntity = new StringEntity(params);
	        httpPost.setEntity(stringEntity);
	        		
			CloseableHttpResponse response = httpclient.execute(httpPost);
			try {
				//System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				StringBuffer strBuf = new StringBuffer();
				byte[] buffer = new byte[4096];
				int r = 0;
				while ((r = is.read(buffer)) > 0) {
					strBuf.append(new String(buffer, 0, r, "UTF-8"));
				}
				responseContent = strBuf.toString();
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			//出于信息安全原因，不打印
			//e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("responseContent = " + responseContent);
		return responseContent;
	}

	/* (non-Javadoc)
	 * @see com.lango.demo.service.IClientService#httpPut(java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public String httpPut(String url, String params, Map<String, String> headMap) {
		// TODO Auto-generated method stub
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPut httpPut = new HttpPut(url);
			setHeader(httpPut,headMap);
	        StringEntity stringEntity = new StringEntity(params);
	        httpPut.setEntity(stringEntity);
			CloseableHttpResponse response = httpclient.execute(httpPut);
			try {
				//System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				StringBuffer strBuf = new StringBuffer();
				byte[] buffer = new byte[4096];
				int r = 0;
				while ((r = is.read(buffer)) > 0) {
					strBuf.append(new String(buffer, 0, r, "UTF-8"));
				}
				responseContent = strBuf.toString();
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			//出于信息安全原因，不打印
			//e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("responseContent = " + responseContent);
		return responseContent;
	}

	/* (non-Javadoc)
	 * @see com.lango.demo.service.IClientService#httpDelete(java.lang.String, java.util.Map)
	 */
	@Override
	public String httpDelete(String url, Map<String, String> headMap) {
		// TODO Auto-generated method stub
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpDelete httpDelete = new HttpDelete(url);
			setHeader(httpDelete,headMap);
			CloseableHttpResponse response = httpclient.execute(httpDelete);
			try {
				//System.out.println(response.getStatusLine());
				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				StringBuffer strBuf = new StringBuffer();
				byte[] buffer = new byte[4096];
				int r = 0;
				while ((r = is.read(buffer)) > 0) {
					strBuf.append(new String(buffer, 0, r, "UTF-8"));
				}
				responseContent = strBuf.toString();
				EntityUtils.consume(entity);
			} finally {
				response.close();
			}
		} catch (Exception e) {
			//出于信息安全原因，不打印
			//e.printStackTrace();
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//System.out.println("responseContent = " + responseContent);
		return responseContent;
	}

}
