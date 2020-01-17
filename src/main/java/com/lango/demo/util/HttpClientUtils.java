package com.lango.demo.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

/**
 * @web http://www.mobctrl.net
 * @author Zheng Haibo
 * @Description: 文件下载 POST GET
 */
public class HttpClientUtils {

	/**
	 * 最大线程池
	 */
	public static final int THREAD_POOL_SIZE = 5;

	public interface HttpClientDownLoadProgress {
		public void onProgress(int progress);
	}

	private static HttpClientUtils httpClientDownload;

	private ExecutorService downloadExcutorService;

	private HttpClientUtils() {
		downloadExcutorService = Executors.newFixedThreadPool(THREAD_POOL_SIZE);
	}

	public static HttpClientUtils getInstance() {
		if (httpClientDownload == null) {
			httpClientDownload = new HttpClientUtils();
		}
		return httpClientDownload;
	}

	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param filePath
	 */
	public void download(final String url, final String filePath) {
		downloadExcutorService.execute(new Runnable() {

			@Override
			public void run() {
				httpDownloadFile(url, filePath, null,null);
			}
		});
	}

	/**
	 * 下载文件
	 * @param url
	 * @param filePath
	 * @param progress
	 *            进度回调
	 */
	public void download(final String url, final String filePath,
			final HttpClientDownLoadProgress progress) {
		downloadExcutorService.execute(new Runnable() {

			@Override
			public void run() {
				httpDownloadFile(url, filePath, progress,null);
			}
		});
	}
	
	/**
	 * 上传文件(可带参数)
	 * @param localFile
	 * @param sendUrl 
	 * @param cusparams
	 * @return
	 */
	public static boolean upload(File localFile,String sendUrl,Map<String,String> cusparams){
        boolean flag = false;
        try {              
            // 设定要上传的普通Form Field及其对应的value  
            MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE,"----------ThIs_Is_tHe_bouNdaRY_$", Charset.defaultCharset());  
            multipartEntity.addPart("file",new FileBody(localFile));    
                
        	if(cusparams != null && cusparams.size() > 0){
        		Set<String> keys = cusparams.keySet();
        		for(String key:keys){
                    // 通过以下方法可以模拟页面参数提交
        			String value = cusparams.get(key);
        			if(value != null){
        				multipartEntity.addPart(key,new StringBody(value, Charset.forName("UTF-8")));    
        			}
        		}
        	}
            
            HttpPost request = new HttpPost(sendUrl);     
            request.setEntity(multipartEntity);  
            request.addHeader("Content-Type","multipart/form-data; boundary=----------ThIs_Is_tHe_bouNdaRY_$");  
              
            DefaultHttpClient httpClient = new DefaultHttpClient();  
            //请求超时
            httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 3000000);
            //读取超时
            httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,3000000);
            HttpResponse response =httpClient.execute(request);  
              
            InputStream is = response.getEntity().getContent();  
            BufferedReader in = new BufferedReader(new InputStreamReader(is));  
            StringBuffer buffer = new StringBuffer();  
            String line = "";  
            while ((line = in.readLine()) != null) {  
                buffer.append(line);  
            }  
            if(buffer.length() > 1){
            	flag = true;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            //filePost.releaseConnection();
        }
        return flag;
    }
	
	/**
	 * 下载文件
	 * 
	 * @param url
	 * @param filePath
	 */
	public static void httpDownloadFile(String url, String filePath,
			HttpClientDownLoadProgress progress, Map<String, String> headMap) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			setGetHead(httpGet, headMap);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				//System.out.println(response1.getStatusLine());
				HttpEntity httpEntity = response1.getEntity();
				long contentLength = httpEntity.getContentLength();
				InputStream is = httpEntity.getContent();
				// 根据InputStream 下载文件
				ByteArrayOutputStream output = new ByteArrayOutputStream();
				byte[] buffer = new byte[4096];
				int r = 0;
				long totalRead = 0;
				while ((r = is.read(buffer)) > 0) {
					output.write(buffer, 0, r);
					totalRead += r;
					if (progress != null) {// 回调进度
						progress.onProgress((int) (totalRead * 100 / contentLength));
					}
				}
				FileOutputStream fos = new FileOutputStream(filePath);
				output.writeTo(fos);
				output.flush();
				output.close();
				fos.close();
				EntityUtils.consume(httpEntity);
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
	}
	
//	/**
//	 * get请求
//	 * 
//	 * @param url
//	 * @return
//	 */
//	public String httpGet(String url) {
//		return httpGet(url, null);
//	}

	/**
	 * http Ping Url请求
	 * 
	 * @param url
	 * @return
	 */
	public boolean httpPing(String url) {
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
	
	/**
	 * http get请求
	 * 
	 * @param url 请求路径
	 * @param headMap 请求头
	 * @return
	 */
	public String httpGet(String url, Map<String, String> headMap) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpGet httpGet = new HttpGet(url);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			setGetHead(httpGet, headMap);
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
	/**
	 * 设置http get的HEAD
	 * 
	 * @param httpGet
	 * @param headMap
	 */
	private static void setGetHead(HttpGet httpGet, Map<String, String> headMap) {
		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				httpGet.addHeader(key, headMap.get(key));
			}
		}
	}
	
	

	/**
	 * http的post请求
	 * @param url   请求
	 * @param params 传递参数
	 * @param headMap 请求头
	 * @return 字符串
	 */
	public String httpPost(String url, String params,
			Map<String, String> headMap) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPost httpPost = new HttpPost(url);
			
			setPostHead(httpPost,headMap);
	        
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

	/**
	 * 设置http post的HEAD
	 * 
	 * @param httpPost
	 * @param headMap
	 */
	private void setPostHead(HttpPost httpPost, Map<String, String> headMap) {

		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				httpPost.addHeader(key, headMap.get(key));
			}
		}
	}
//	/**
//	 * 设置POST的参数
//	 * 
//	 * @param httpPost
//	 * @param paramsMap
//	 * @throws Exception
//	 */
//	private void setPostParams(HttpPost httpPost, Map<String, String> paramsMap)
//			throws Exception {
//		if (paramsMap != null && paramsMap.size() > 0) {
//			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
//			Set<String> keySet = paramsMap.keySet();
//			for (String key : keySet) {
//				nvps.add(new BasicNameValuePair(key, paramsMap.get(key)));
//			}
//			httpPost.setEntity(new UrlEncodedFormEntity(nvps));
//		}
//	}

	
	/**
	 * http的put请求
	 * @param url   请求路径
	 * @param params 传递参数
	 * @param headMap 请求头
	 * @return 字符串
	 */
	public String httpPut(String url, String params,
			Map<String, String> headMap) {
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpPut httpPut = new HttpPut(url);
			setPutHead(httpPut,headMap);
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
	
	/**
	 * 设置http Put的HEAD
	 * @param httpPut
	 * @param headMap
	 */
	private void setPutHead(HttpPut httpPut, Map<String, String> headMap) {
		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				httpPut.addHeader(key, headMap.get(key));
			}
		}
	}
	
	/**
	 * httpDelete请求
	 * @param url    请求路径
	 * @param headMap    请求头
	 * @return
	 */
	public String httpDelete(String url,
			Map<String, String> headMap){
		String responseContent = null;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			HttpDelete httpDelete = new HttpDelete(url);
			setDeleteHead(httpDelete,headMap);
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
	
	/**
	 * 设置http delete的HEAD
	 * @param httpDelete
	 * @param headMap
	 */
	private void setDeleteHead(HttpDelete httpDelete, Map<String, String> headMap) {
		if (headMap != null && headMap.size() > 0) {
			Set<String> keySet = headMap.keySet();
			for (String key : keySet) {
				httpDelete.addHeader(key, headMap.get(key));
			}
		}
	}
	
	/**
	 * 下载远程文件
	 * @param url
	 * @param name
	 * @param response
	 */
	public static boolean httpDownloadRemoteFile(String url,String name,HttpServletResponse response) {
		boolean isExist = true;
		CloseableHttpClient httpclient = HttpClients.createDefault();
		response.setContentType("application/octet-stream");
		try {
			response.setHeader("Content-disposition", "attachment; filename="  
					+ new String((name).getBytes("utf-8"), "ISO8859-1"));  
			HttpGet httpGet = new HttpGet(url);
			setGetHead(httpGet,null);
			CloseableHttpResponse response1 = httpclient.execute(httpGet);
			try {
				HttpEntity httpEntity = response1.getEntity();
				InputStream is = httpEntity.getContent();
				// 根据InputStream 下载文件
				BufferedInputStream bis = new BufferedInputStream(is);
				BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];  
	            int bytesRead; 
	            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {  
	                bos.write(buff, 0, bytesRead);  
	            } 
				EntityUtils.consume(httpEntity);
				bos.close();
			} finally {
				response1.close();
			}
		} catch (Exception e) {
			isExist = false;
		} finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return isExist;
	}
}