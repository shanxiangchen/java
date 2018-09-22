package com.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component("demoTask")
public class DemoTask {
	
	private static Logger logger = Logger.getLogger(DemoTask.class);
	
	public void cronJob1(){
		logger.debug("��ʱ���� : ����������������");
	}
	
	@Scheduled(cron="0 2/4 * * * ?") //cron���ʽ
	public void cronJob2(){
		logger.debug("��ʱ���� : �м�ҵ������������");
	}

}
