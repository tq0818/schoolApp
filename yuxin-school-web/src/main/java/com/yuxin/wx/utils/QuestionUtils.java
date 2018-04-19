package com.yuxin.wx.utils;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class QuestionUtils {
	   /**
     * 处理问答中换行问题
     * @param questionDesc
     */
    public static String dealQuestionDesc(String questionDesc){
	   	//处理换行问题
	   	if (StringUtils.isNotBlank(questionDesc)) {
	   		JSONArray jsonArray = JSONArray.fromObject(questionDesc);
	   		for (int i = 0; i < jsonArray.size(); i++) {
	   			JSONObject jsonObject = jsonArray.getJSONObject(i);
	   			if("0".equals(jsonObject.getString("type"))){
	   				//处理后台提问的换行
	   				String s = jsonObject.getString("content").replace("<br>", "\n");
	   				//处理IOS的换行
	   				s = s.replace("\\ua", "\n");
	   				jsonObject.put("content", s);
	   			}
	   		}
	   		return jsonArray.toString();
	   	}
	   	return questionDesc;
    }
}
