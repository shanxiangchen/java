package com.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("demoTask")
public class DemoTask {
	
	private static Logger logger = Logger.getLogger(DemoTask.class);
	
	@Scheduled(cron="0 1/2 * * * ?") //cron表达式
	public void cronJob1(){
		logger.error("定时任务 : 互联网批量开户！");
	}

}
