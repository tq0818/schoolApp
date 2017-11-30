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
	}
}
