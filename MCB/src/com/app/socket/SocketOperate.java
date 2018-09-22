package com.app.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.app.listener.Const;
import com.app.util.DbUtils;
 
 

public class SocketOperate extends Thread{
    
	private static Logger log = Logger.getLogger(SocketOperate.class);
	private Socket socket;

	//private static TypeReference<Map<String, String>> typeReference = new TypeReference<Map<String, String>>() {};
	private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {};
 
	public SocketOperate(Socket socket) {
		this.socket = socket;
	}

	@SuppressWarnings({ "unchecked" })
	public void run() {
		BufferedReader is = null;
		PrintWriter out = null;
		try {

			socket.setSoTimeout(30000);  
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			out = new PrintWriter(socket.getOutputStream());

			String req = "";
			// 读取客户端发送的信息
			while(true){
				String reqStr = is.readLine();
				
				if("".equals(reqStr)||reqStr.equals("bye")){
					break;
				}
				req = req + reqStr;
			}
			/*log.info("get client message:*****" + req);
			String length = req.substring(0,1);
			String act = req.substring(1,1+Integer.parseInt(length));
			log.info(act+ " SocketOperate run() begin --------> start"); 
			req = req.substring(1+Integer.parseInt(length));*/
			
			 
			//交易处理
			Map<String, Object>  resMap = new HashMap<String,Object>();
			DbUtils db =null;
			Connection conn=null;
			Map<String,Object> map=JSON.parseObject(req, typeRef);
			List<Object> paramList=(List<Object>) map.get("LIST");
			String sql=(String) map.get("SQL");
			String act=(String)map.get("ACT");
			DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
			if(act.equals("SELECT")){
				try {
					db=new DbUtils();
					conn=datasource.getConnection();
					conn.setAutoCommit(false);
					List<Map<String ,Object>> resultList=db.getQueryList(sql, paramList, conn);
					resMap.put("list", resultList);
					conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally{
					db.closeConn(conn);
				}
				  
			}else if(act.equals("SAVEORUPDATE")){
				try {
					conn=datasource.getConnection();
					conn.setAutoCommit(false);
					db = new DbUtils();
					int count=db.execute(sql, paramList, conn);
					resMap.put("count", count);
					//conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally{
					db.closeConn(conn);
				}
				 
			}else if(act.equals("TASK")){//批量执行
				try {
					conn=datasource.getConnection();
					conn.setAutoCommit(false);
					db = new DbUtils();
					int[] count=db.executeBatch(sql, paramList, conn);
					resMap.put("count", count);
					conn.commit();
				} catch (SQLException e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} catch (Exception e) {
					e.printStackTrace();
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally{
					db.closeConn(conn);
				}
			}
	 
			//返回给前置的信息
			String resStr = JSON.toJSONString(resMap);
			out.println(resStr);
			out.println("bye");

			out.flush();
			/*log.info("response client: " + resStr.toString());
			 
			log.info(act+ " SocketOperate run() success --------> stop");*/

		} catch (IOException ex) {
			log.error("server failed");
			out.println("bye");
			out.flush();
			
		} finally {

			try {
				if(is!=null)
					is.close();
				if(out!=null)
					out.close();
				if(socket!=null)
					this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	 

}
