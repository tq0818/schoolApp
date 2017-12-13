package com.yuxin.wx.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.http.Consts;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.yuxin.wx.utils.HttpClient4Utils;
import com.yuxin.wx.utils.PropertiesUtil;
import com.yuxin.wx.utils.SignatureUtils;

/**
 * 调用易盾反垃圾云服务文本在线检测接口API示例，该示例依赖以下jar包：
 * 1. httpclient，用于发送http请求
 * 2. commons-codec，使用md5算法生成签名信息，详细见SignatureUtils.java
 * 3. gson，用于做json解析
 * 
 * @author hzgaomin
 * @version 2016年2月3日
 */
public class TextCheck {
    /** 产品密钥ID，产品标识 */
    private final static String SECRETID = "4ebfc3e6f3ea895a756e4f42b107928d";
    /** 产品私有密钥，服务端生成签名信息使用，请严格保管，避免泄露 */
    private final static String SECRETKEY = "4c0da0c13a52e054ebf95ae43b2e0d10";
    /** 业务ID，易盾根据产品业务特点分配 */
//    private final static String BUSINESSID = "af69b70c7f08ef78b035923417c7fb1c";
    /** 易盾反垃圾云服务文本在线检测接口地址 */
    private final static String API_URL = "https://api.aq.163.com/v3/text/check";
    /** 实例化HttpClient，发送http请求使用，可根据需要自行调参 */
    private static HttpClient httpClient = HttpClient4Utils.createHttpClient(100, 20, 2000, 2000, 2000);
//    private static PropertiesUtil propertiesUtil;
    /**
     * 
     * @param args
     * @throws Exception
     */
    public static boolean  TextCheck(String str,PropertiesUtil propertiesUtil) throws Exception {
    	boolean flag=false;
        Map<String, String> params = new HashMap<String, String>();
        // 1.设置公共参数
        params.put("secretId", SECRETID);
        params.put("businessId",propertiesUtil.getTextBusinessId());
        params.put("version", "v3");
        params.put("timestamp", String.valueOf(System.currentTimeMillis()));
        params.put("nonce", String.valueOf(new Random().nextInt()));

        // 2.设置私有参数
        UUID id = UUID.randomUUID();
        //String qId = "提问或回答的id";
        params.put("dataId", id.toString());//问题的id
        params.put("content", str);//问题内容
        /*params.put("dataType", "1");
        params.put("ip", "123.115.77.137");
        params.put("account", "java@163.com");
        params.put("deviceType", "4");
        params.put("deviceId", "92B1E5AA-4C3D-4565-A8C2-86E297055088");
        params.put("callback", "ebfcad1c-dba1-490c-b4de-e784c2691768");
        params.put("publishTime", String.valueOf(System.currentTimeMillis()));*/

        // 3.生成签名信息
        String signature = SignatureUtils.genSignature(SECRETKEY, params);
        params.put("signature", signature);

        // 4.发送HTTP请求，这里使用的是HttpClient工具包，产品可自行选择自己熟悉的工具包发送请求
        String response = HttpClient4Utils.sendPost(httpClient, API_URL, params, Consts.UTF_8);

        // 5.解析接口返回值
        JsonObject jObject = new JsonParser().parse(response).getAsJsonObject();
        int code = jObject.get("code").getAsInt();
        //String msg = jObject.get("msg").getAsString();
        if (code == 200) {
            JsonObject resultObject = jObject.getAsJsonObject("result");
            int action = resultObject.get("action").getAsInt();
           // String taskId = resultObject.get("taskId").getAsString();
           // JsonArray labelArray = resultObject.getAsJsonArray("labels");
            /*for (JsonElement labelElement : labelArray) {
                JsonObject lObject = labelElement.getAsJsonObject();
                int label = lObject.get("label").getAsInt();
                int level = lObject.get("level").getAsInt();
                JsonObject detailsObject=lObject.getAsJsonObject("details");
                JsonArray hintArray=detailsObject.getAsJsonArray("hint");
            }*/
            if (action == 0) {
            	flag=true;
            } else if (action == 1) {
            	flag=false;
            } else if (action == 2) {
            	flag=false;
            }
        } else {
        	flag=false;
        }
        return flag;
    }
}
