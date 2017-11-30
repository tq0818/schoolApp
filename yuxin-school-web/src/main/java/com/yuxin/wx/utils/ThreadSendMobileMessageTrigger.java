package com.yuxin.wx.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yuxin.wx.api.company.ICompanyMessageHistoryService;
import com.yuxin.wx.model.company.CompanyMessageHistoryLog;

public class ThreadSendMobileMessageTrigger implements Runnable {
	
	private Log log = LogFactory.getLog("log");
	private ICompanyMessageHistoryService companyMessageHistoryService;  
	
	public ThreadSendMobileMessageTrigger(ICompanyMessageHistoryService companyMessageHistoryService){
		this.companyMessageHistoryService = companyMessageHistoryService; 
	}
	
	@Override
	public void run() {

		try {
			while (true) {
				//log.info("短信通知触发站内通知--执行开始--");
				List<CompanyMessageHistoryLog> logList=companyMessageHistoryService.queryAllMessageHistoryLog();
				if(null!=logList&&logList.size()>0){
					for(CompanyMessageHistoryLog cmhlog:logList){
						Map<String, String> params=new HashMap<String, String>();
						params.put("message_id", String.valueOf(cmhlog.getMessageId()));
						String[] userIds={String.valueOf(cmhlog.getUserId())};
						String josnResult=JiGuangPushUtil.push(userIds, cmhlog.getContent(), "",params);
						if("推送成功".equals(josnResult)){
							companyMessageHistoryService.updateMessageHistoryLogByIds(cmhlog.getId());
						}
					}
				}
				//log.info("短信通知触发站内通知--执行结束--");
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			new Thread(new ThreadSendMobileMessageTrigger(companyMessageHistoryService)).start();
			return;
		}

		/*try {
			while (true) {
				//log.info("短信通知触发站内通知--执行开始--");
				List<CompanyMessageHistoryLog> logList=companyMessageHistoryService.queryAllMessageHistoryLog();
				if(null!=logList&&logList.size()>0){
					for(CompanyMessageHistoryLog cmhlog:logList){
						Map<String, String> params=new HashMap<String, String>();
						params.put("message_id", String.valueOf(cmhlog.getMessageId()));
						String[] userIds={String.valueOf(cmhlog.getUserId())};
						String josnResult=JiGuangPushUtil.push(userIds, cmhlog.getContent(), "",params);
						if("推送成功".equals(josnResult)){
							companyMessageHistoryService.updateMessageHistoryLogByIds(cmhlog.getId());
						}
					}
				}
				//log.info("短信通知触发站内通知--执行结束--");
				Thread.sleep(1000*60);
			}
		} catch (Exception e) {
			new Thread(new ThreadSendMobileMessageTrigger(companyMessageHistoryService)).start();
			return;
		}*/
	}

}
