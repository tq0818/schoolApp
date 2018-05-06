package com.yuxin.wx.utils;

import com.yuxin.wx.common.SMSHandler;

/**
 * Created by lym_gxm on 18/4/13.
 */
public class SendMsg {

    public static final String SMS_HURRY_TEMPLATE_ID="243576";

    public static void main(String [] arg){
        String phones= "";
        System.out.println(phones.replaceAll("\t","").replaceAll("\n",""));
        String[]phoneNums = phones.replaceAll("\t","").replaceAll("\n","").split("@");
        int num = 1;
        for(String str : phoneNums){
            System.out.println("第"+(num++)+"批--\n"+str);
//            sendHurryNotice(str,null,null);
        }
        System.out.println("发送完成.....");

//        sendHurryNotice("13678046242,18200389727,",null,null);
    }

    public static String sendHurryNotice(String phoneNum, String lessonName, String date){

        String result="";
        //记录发送历史
        try {
            SMSHandler.send(phoneNum, SMS_HURRY_TEMPLATE_ID, new String[]{lessonName,date});
            result = "发送成功";
        } catch (Exception e) {
            e.printStackTrace();
            result = "发送失败";
        }finally{
            return result;
        }

    }
}
