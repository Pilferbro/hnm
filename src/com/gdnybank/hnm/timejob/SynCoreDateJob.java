package com.gdnybank.hnm.timejob;

import com.gdnybank.hnm.pub.service.HnmCommService;
import com.nantian.mfp.framework.utils.BaseUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class SynCoreDateJob extends BaseTimeJob {
	private final Logger looger = Logger.getLogger(this.getClass());
	
	@Resource
	private HnmCommService hnmCommService;
	
	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p){
		looger.info("定时任务“SynCoreDateJob”开始执行");
		hnmCommService.synCoreDate(BaseUtils.map());
		looger.info("定时任务“SynCoreDateJob”执行结束");
		
		return null;
	}

}
