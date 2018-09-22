package com.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("demoTask")
public class DemoTask {
	
	private static Logger logger = Logger.getLogger(DemoTask.class);
	
	public void cronJob1(){
		logger.debug("定时任务 : 互联网批量开户！");
	}
	
	@Scheduled(cron="0 2/4 * * * ?") //cron表达式
	public void cronJob2(){
		logger.debug("定时任务 : 中间业务批量开户！");
	}

}
