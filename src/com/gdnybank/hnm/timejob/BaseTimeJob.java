package com.gdnybank.hnm.timejob;

import java.util.UUID;

import com.nantian.mfp.framework.context.MfpContextHolder;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.nantian.mfp.framework.err.BusinessException;
import com.nantian.mfp.framework.utils.BaseUtils;
import com.nantian.mfp.pub.service.TXBaseFormService;

@Service
public class BaseTimeJob extends TXBaseFormService {
	private Logger logger = Logger.getLogger(this.getClass());

	private int runTime = 0;

	//获取默认重跑次数,通过system.properties设置
	public int getMaxAgainTime() {
		return Integer.valueOf(MfpContextHolder.getProps("system.timejob.MaxAgainTime", "2"));
	}

	//获取默认重跑等待,通过system.properties设置
	public int getWaitTime() {
		return Integer.valueOf(MfpContextHolder.getProps("system.timejob.WaitTIme", "60"));
	}


	public void exeJob(){
		//设置日志的OID
		String reqFlowNo=UUID.randomUUID().toString();
		MfpContextHolder.setReqFlowNo(reqFlowNo);
		try{
			// 增加定时任务的名称作为定时任务的交易码
			String classStr = this.getClass().toString();
			String jobId = classStr.substring(classStr.lastIndexOf(".")+1,classStr.length());
			MfpContextHolder.setTxcode(jobId);
			runTime++;
			logger.info(this.getClass()+" 失败最大重跑次数为： "+this.getMaxAgainTime()+" ; 当前执行次数为： "+runTime);
			//执行具体的任务操作
			doService(BaseUtils.map(),BaseUtils.map());
			runTime=0;
		}catch (Exception ex){
			ex.printStackTrace();
			logger.error(this.getClass()+"任务执行出错！！！");
			//判断运行失败次数是否已超过最大重跑次数
			if(runTime <= this.getMaxAgainTime()){
				//运行失败次数小于最大重跑次数，休眠等待设置的时间后重跑
				Thread th = new Thread();
				int second = this.getWaitTime();
				try {
					th.sleep(second*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
					String msg = this.getClass()+" 等待重跑过程中出错";
					logger.error(msg);
					throw new BusinessException("BaseTimeJob001",msg);
				}
				int minute = 0;
				int hour = 0;
				String waitTimeMsg = "";
				if(60 <= second){
					minute =second / 60;
					second = second % 60;
					if(60 <= minute){
						hour = minute / 60;
						minute = minute % 60;
					}
					if(0 != hour){
						waitTimeMsg += hour+" 时 ";
					}
					if(0 != minute){
						waitTimeMsg += minute+" 分 ";
					}
					if(0 != second){
						waitTimeMsg += second+" 秒 ";
					}
				}
				logger.info(this.getClass()+"等待"+waitTimeMsg+"后重新执行");
				exeJob();
			}else{
				//运行失败次数大于最大重跑次数，抛错返回
				String msg = this.getClass()+"当前执行次数为："+runTime+" ，已经超过最大重跑次数："+this.getMaxAgainTime()+"。任务不再重跑，需人工处理";
				logger.error(msg);
				runTime=0;
				throw new BusinessException("BaseTimeJob002",msg);
			}
		}
	}
}
