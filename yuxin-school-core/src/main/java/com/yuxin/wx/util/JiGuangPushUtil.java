package com.yuxin.wx.util;


import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class JiGuangPushUtil {
	
	    protected static final Logger LOG = LoggerFactory.getLogger(JiGuangPushUtil.class);
	    public static final String TITLE = "申通快递";
	    public static final String ALERT = "祝大家新春快乐";
	    public static final String MSG_CONTENT = "申通快递祝新老客户新春快乐";
	    public static final String REGISTRATION_ID = "0900e8d85ef";
	    public static final String TAG = "tag_api";


	    public static JPushClient jpushClient = null;

	    public static void main(String[] args) {
//	        正式
	        String appkey = "fb048c9ad2fc49009ab93ade";
	        String master = "6f59c3366f8fa161b7738a63";
	        String[] userIds = {"11","22","33"};
//	        测试
//	        String appkey = "cbb9ad81bca3d289b7a52c86";
//	        String master = "f8a1e26d4ba1af02f4220faf";
//	        push1(master,appkey,userIds);
	        push2(master, appkey, userIds);
//	        push3(master, appkey);
	    }
	    
	    /**
	     * 推送指定学员
	     */
	    public static String push(List<String> userIds,String content,String title,Map<String, String> params) {
	    	String result="";
	    	 try {
		    	Resource resource = new ClassPathResource("/config.properties");
				Properties props;
				props= PropertiesLoaderUtils.loadProperties(resource);
		        JPushClient jPushClient = new JPushClient(props.getProperty("jiguang.master"), props.getProperty("jiguang.appkey"));
		        params.put("content",content); 
		        PushPayload builder = PushPayload.newBuilder()
		                .setPlatform(Platform.android_ios())
//		                .setAudience(Audience.alias("558174"))//设置别名
		                .setAudience(Audience.newBuilder()
		                		.addAudienceTarget(AudienceTarget.alias(userIds))
		                		.build())
		                .setNotification(Notification.newBuilder()
		                        .setAlert(content)
		                        .addPlatformNotification(AndroidNotification.newBuilder()
		                                .setTitle(StringUtils.isNotEmpty(title)?title:"消息通知")
		                                .addExtras(params)
		                                .build())
		                        .addPlatformNotification(IosNotification.newBuilder()
		                                .addExtras(params).incrBadge(0)
		                                .build())
		                        .build())
		                .build();
		            PushResult results = jPushClient.sendPush(builder);
		            result="推送成功";
	        } catch (Exception e) {
//	            e.printStackTrace();
	            LOG.error("极光消息推送失败--"+new Date()+"----"+e.getMessage());
	            result = "推送失败";
	        }finally{
	        	return result;
	        }
	    }
	    
	    
	    
	    private static void push1(String master, String appkey, String[] userIds) {
	        String id = "";
	        for(int i = 0 ; i < userIds.length; i++){
	                id += "\""+userIds[i]+"\""+ ",";
	        }
	        String ids = id.substring(0,id.lastIndexOf(","));
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("content", "H5页面");
	        params.put("flag1", "aaaa");
	        params.put("flag1", "bbbb");
	        JPushClient jPushClient = new JPushClient(master, appkey);
	        PushPayload payload = PushPayload.newBuilder()
	                .setPlatform(Platform.all())//设置平台（ios/Android）all为所有平台（广播形式）
	                .setAudience(Audience.alias(ids))//设置推送给指定别名的用户
//	                .setAudience(Audience.all())//设置推送用户all未全部用户
	                .setNotification(Notification.alert("推送测试"))//消息标题
//	                .setNotification(Notification.android(ALERT, TITLE, null))//增加标题,发送给Android用户
//	                .setNotification(Notification.newBuilder()
//	                        .addPlatformNotification(IosNotification.newBuilder()
//	                                .setAlert(ALERT)
//	                                .setBadge(5)
//	                                .setSound("happy")
//	                                .addExtra("from", "JPush")
//	                                .build())
//	                        .build())//不管什么东西，iOS的总要复杂一些
	                .setMessage(Message.content("内容内容内容内容内容内容内容内容内容内容内容内容内容内容"))//自定义消息内容
	                //其他设置。。。
	                .build();

	        try {
	            PushResult result = jPushClient.sendPush(payload);
	            System.out.println(result.msg_id);
	            System.out.println(result.sendno);
	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        }
	    }


	    private static void push2(String master, String appkey,String[] userIds) {
	        JPushClient jPushClient = new JPushClient(master, appkey);
	        String id = "";
	        for(int i = 0 ; i < userIds.length; i++){
	            id += "\""+userIds[i]+"\""+ ",";
	        }
	        String ids = id.substring(0,id.lastIndexOf(","));
	        Map<String, String> params = new HashMap<String, String>();
	        params.put("content", "H5页面");
	        params.put("flag1", "aaaa");
	        params.put("flag1", "bbbb");
	        PushPayload builder = PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.alias("338802"))//设置别名
	                .setNotification(Notification.newBuilder()
	                        .setAlert(params)
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .setTitle("测试titel")
	                                .addExtras(params)
	                                .build())
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .addExtras(params)
	                                .build())
	                        .build())
	                .build();

	        try {
	            PushResult results = jPushClient.sendPush(builder);
	            System.out.println(results.msg_id);
	            System.out.println(results.sendno);
	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        }
	    }




	    private static void push3(String master, String appkey) {
	        Map<String, String> parm = new HashMap<String, String>();
	        parm.put("msg", "毒贩夫妇付付付付付付付付付付付付付付付付付");
	        JPushClient jpushClient = new JPushClient(master, appkey);
	        PushPayload payload = PushPayload.newBuilder()
	                .setPlatform(Platform.all())//ios平台的用户
	                .setAudience(Audience.all())//所有用户
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(parm.get("msg"))
	                                .setBadge(+1)
	                                .setSound("happy")
	                                .addExtras(parm)
	                                .build())
	                        .build())
	                .setOptions(Options.newBuilder().setApnsProduction(false).build())
	                .setMessage(Message.newBuilder().setMsgContent(parm.get("msg")).addExtras(parm).build())//自定义信息
	                .build();

	        try {
	            PushResult pu = jpushClient.sendPush(payload);
	            System.out.println(pu.msg_id);
	            System.out.println(pu.sendno);
	        } catch (APIConnectionException e) {
	            e.printStackTrace();
	        } catch (APIRequestException e) {
	            e.printStackTrace();
	        }

	    }



	    /**
	     * 将图片文件转为字符串
	     *
	     * @param imgFile
	     * @return
	     */
	    public static String getImageStr(String imgFile) {
	        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	        //String imgFile = "d:\\111.jpg";// 待处理的图片
	        InputStream in = null;
	        byte[] data = null;
	        // 读取图片字节数组
	        try {
	            in = new FileInputStream(imgFile);
	            data = new byte[in.available()];
	            in.read(data);
	            in.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        // 对字节数组Base64编码
	        BASE64Encoder encoder = new BASE64Encoder();
	        // 返回Base64编码过的字节数组字符串
	        return encoder.encode(data);
	    }



	    /*public static void testSendPush(String appKey, String masterSecret) {
	        jpushClient = new JPushClient(masterSecret, appKey);

	        //生成推送的内容，这里我们先测试全部推送
	        PushPayload payload = buildPushObject_all_alias_alert();
	        try {
	            System.out.println(payload.toString());
	            PushResult result = jpushClient.sendPush(payload);
	            System.out.println(result + "................................");

	            LOG.info("Got result - " + result);

	        } catch (APIConnectionException e) {
	            LOG.error("Connection error. Should retry later. ", e);

	        } catch (APIRequestException e) {
	            LOG.error("Error response from JPush server. Should review and fix it. ", e);
	            LOG.info("HTTP Status: " + e.getStatus());
	            LOG.info("Error Code: " + e.getErrorCode());
	            LOG.info("Error Message: " + e.getErrorMessage());
	            LOG.info("Msg ID: " + e.getMsgId());
	        }
	    }

	    public static PushPayload buildPushObject_all_all_alert() {
	        return PushPayload.alertAll(ALERT);
	    }

	    //生成推送的内容，测试全部推送
	    public static PushPayload buildPushObject_all_alias_alert() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.all())//设置接受的平台
	                .setAudience(Audience.all())//Audience设置为all，说明采用广播方式推送，所有用户都可以接收到
	                .setNotification(Notification.alert(ALERT))//消息内容
	                .build();
	    }


	    public static PushPayload buildPushObject_android_tag_alertWithTitle() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android())
	                .setAudience(Audience.all())
	                .setNotification(Notification.android(ALERT, TITLE, null))
	                .build();
	    }

	    public static PushPayload buildPushObject_android_and_ios() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.tag("tag1"))
	                .setNotification(Notification.newBuilder()
	                        .setAlert("alert content")
	                        .addPlatformNotification(AndroidNotification.newBuilder()
	                                .setTitle("Android Title").build())
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .incrBadge(1)
	                                .addExtra("extra_key", "extra_value").build())
	                        .build())
	                .build();
	    }

	    public static PushPayload buildPushObject_ios_tagAnd_alertWithExtrasAndMessage() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.ios())
	                .setAudience(Audience.tag_and("tag1", "tag_all"))
	                .setNotification(Notification.newBuilder()
	                        .addPlatformNotification(IosNotification.newBuilder()
	                                .setAlert(ALERT)
	                                .setBadge(5)
	                                .setSound("happy")
	                                .addExtra("from", "JPush")
	                                .build())
	                        .build())
	                .setMessage(Message.content(MSG_CONTENT))
	                .setOptions(Options.newBuilder()
	                        .setApnsProduction(true)
	                        .build())
	                .build();
	    }

	    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras() {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
	                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))//别名
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(MSG_CONTENT)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
	    }*/


}
