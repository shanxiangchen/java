package com.app.socket;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class SocketServiceLoader implements ServletContextListener{

	private SocketThread socketThread;
	
	public void contextDestroyed(ServletContextEvent e) {

		if(null!=socketThread && !socketThread.isInterrupted())  
         {  
          socketThread.closeSocketServer();  
          socketThread.interrupt();  
         }  
	}

	//int i=0;
	public void contextInitialized(ServletContextEvent e) {
		
		 if(null==socketThread)  
	        {  
	         //新建线程类  
	         socketThread=new SocketThread(null);  
	         //设置成守护线程
	         socketThread.setDaemon(true);
	         //启动线程  
	         //MBServlet.threadPool.execute(socketThread);
	         socketThread.start();
	         
	      }  
	}

	
}
