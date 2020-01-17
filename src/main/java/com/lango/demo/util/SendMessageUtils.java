package com.lango.demo.util;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
/**
 * @author: cc
 * @version: 2020年1月17日 下午4:38:43
 */
public class SendMessageUtils {

	private static SendMessageUtils sendMessageUtils;

	public static SendMessageUtils getInstance() {
		if (sendMessageUtils == null) {
			synchronized (SendMessageUtils.class) {
				if (sendMessageUtils == null) {
					sendMessageUtils = new SendMessageUtils();
				}
			}
		}
		return sendMessageUtils;
	}

	public void sendMessageToChargePerson(int cId, String message){
		System.out.println("负责人id:"+cId+",内容:"+message);
//		DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", "<accessKeyId>", "<accessSecret>");
//        IAcsClient client = new DefaultAcsClient(profile);
//
//        CommonRequest request = new CommonRequest();
//        request.setMethod(MethodType.POST);
//        request.setDomain("dysmsapi.aliyuncs.com");
//        request.setVersion("2017-05-25");
//        request.setAction("QuerySendDetails");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumber", "13433616245");
//        request.putQueryParameter("SendDate", "hello");
//        try {
//            CommonResponse response = client.getCommonResponse(request);
//            System.out.println(response.getData());
//        } catch (ServerException e) {
//            e.printStackTrace();
//        } catch (ClientException e) {
//            e.printStackTrace();
//        }
	}
}
