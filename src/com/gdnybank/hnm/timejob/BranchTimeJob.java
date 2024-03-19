package com.gdnybank.hnm.timejob;

import com.gdnybank.hnm.pub.service.HnmCommService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;


@Service
public class BranchTimeJob  extends BaseTimeJob{
	private Logger logger = Logger.getLogger(this.getClass());
	@Resource
	private HnmCommService hnmCommService;

	@Override
	public Object doService(Map<String, Object> env, Map<String, Object> p){
		synchronized(this){
			logger.info("定时任务“BranchTimeJob”开始执行");
			hnmCommService.synBranchInfo();
			logger.info("定时任务“BranchTimeJob”执行结束");

			return null;
		}
	}
}
