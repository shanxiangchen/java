package com.app.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

 

 

public class SocketThread extends Thread{
	private static Logger log = Logger.getLogger(SocketThread.class);

	private ServerSocket serverSocket = null;  
	//定义线程池
	private static ExecutorService threadPool = null;
    public SocketThread(ServerSocket serverScoket){  
        try {  
            if(null == serverSocket){  
            	Properties props = new Properties();
            	ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        		@SuppressWarnings("rawtypes")
				Enumeration enums = bundle.getKeys();
        		Object key = null;
        		Object value = null;
        		for (; enums.hasMoreElements(); props.put(key, value)) {
        			key = enums.nextElement();
        			value = bundle.getString(key.toString());
        		}

        		bundle = null;
            	int port = Integer.parseInt((String)props.get("listen_port1"));
            	//初始连接池数量
            	int int_count = Integer.parseInt((String)props.get("socket_count"));
            	//队列连接数
            	int queue_count = Integer.parseInt((String)props.get("queue_num"));
            	//监听的连接数
            	int max_count = Integer.parseInt((String)props.get("listen_num"));
            	//空闲等待时间
            	int keep_time = Integer.parseInt((String)props.get("keep_time"));
            	//初始化线程池
            	threadPool = new ThreadPoolExecutor(int_count, max_count, keep_time,
            			TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queue_count));
                this.serverSocket = new ServerSocket();  
                serverSocket.setReuseAddress(true);//先设置serverSocket的选项,该选项必须在bind之前才有效
                serverSocket.bind(new InetSocketAddress(port));
            }   
            
        } catch (Exception e) {  
        	log.error(e +" "+"serverSocket create failed");
        	e.printStackTrace();
        }  
  
    }  
    public SocketThread(){
    	
    }
       
    public void run(){  
    	
        while(!this.isInterrupted()){  
            try {  
                Socket socket = serverSocket.accept();  
                
                if(null != socket && !socket.isClosed()){     
                    //处理接受的数据  
                	SocketOperate operte = new SocketOperate(socket);  
                	//MBServlet.threadPool.execute(operte);
                	threadPool.execute(operte);
                	//operte.runClient();
                	 
                	log.error("the active thread count:"+((ThreadPoolExecutor)threadPool).getActiveCount());
                }   
                  
            }catch (Exception e) {  
                e.printStackTrace();  
                Thread.currentThread().interrupt();
                
            }
        }  
        
    }  
      
      
    public void closeSocketServer(){  
       try {  
            if(null!=serverSocket && !serverSocket.isClosed())  
            {  
             serverSocket.close(); 
            }  
       } catch (IOException e) {  
        
        e.printStackTrace();  
       }  
      } 
    
}
