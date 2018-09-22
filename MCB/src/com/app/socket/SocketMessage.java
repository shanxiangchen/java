package com.app.socket;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.app.util.ConfigManager;
public class SocketMessage {
	private static TypeReference<Map<String, Object>> typeRef = new TypeReference<Map<String, Object>>() {
	};
	
	public String login(){
		// 用户每次登录更新消息中心推送平台存储的用户信息
		Map<String, Object> netionResMap = new HashMap<String, Object>();
		
		netionResMap.put("act", "LOGIN");
//		netionResMap.put("act", "QUERY");

		netionResMap.put("userPhone", "18616296395");
		netionResMap.put("deviceType", "I");
		netionResMap.put("deviceToken", "");
		netionResMap.put("lastDate", "2016-11-03 18:44");
		netionResMap.put("deviceId", "DEVICEID|$|14|$|99000469735397");
		netionResMap.put("isOpen", "0");
		return null;
		
	}
	
	/**
	 * 更新消息模板
	 * @return
	 */
	public static Map<String, Object> upTemp(String typeNo,String smallTypeNo,String writienContent,String messageTitle) {
		Map<String, Object> netionResMap = new HashMap<String, Object>();

		netionResMap.put("act", "UPTEMP");
		netionResMap.put("pushType",typeNo);//typeno
		netionResMap.put("messageType",smallTypeNo);//smalltypeno
		netionResMap.put("content",writienContent);//writiencontent
		netionResMap.put("title",messageTitle);//messagetitle
		return netionResMap;
	}
	
	public static Map<String, Object> resend(String messageDate,String messageCode) {
		Map<String, Object> netionResMap = new HashMap<String, Object>();

		netionResMap.put("act", "RELOAD");//
		netionResMap.put("time", messageDate);//日期
		netionResMap.put("code", messageCode);//code
		return netionResMap;
		
	}
	
	
	public static Map<String, Object> resend(String card) {
		Map<String, Object> netionResMap = new HashMap<String, Object>();
		netionResMap.put("act", "CCCARD");//
		netionResMap.put("card", card);//日期
		return netionResMap;
		
	}
	
	/**
	 * 直连易迅服务器
	 * 建议ipCode使用随机数，每次只访问一台
	 * @param ipCode
	 * @param req
	 * @return
	 */
	public static Map<String, Object> callNetionService(Map<String, Object> req){

		//获取配置的ip地址
		String listen_ip = ConfigManager.getProperties("listen_ip");
		String listen_port = ConfigManager.getProperties("listen_port");
		
		String reqStr = JSON.toJSONString(req);
		String resStr= null;
		Socket socket = null;
		PrintWriter os = null;
		BufferedReader is  = null;
		
		try {
			socket = new Socket(listen_ip,Integer.parseInt(listen_port));
			//socket.setSoTimeout(30000);
			is = new BufferedReader(new InputStreamReader(
					socket.getInputStream(),"utf-8"));

			os=new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"utf-8"));

			//发送数据
			os.println(reqStr);
			os.println("bye");
			os.flush();
			
			//读取返回数据
			while(true){
				String res = is.readLine();
				if(res==null || res.equals("bye")){
					break;
				}
				resStr += res;
			}
			if(resStr.indexOf("null")==0){
				resStr = resStr.substring(4);
			}
				//System.out.println("server respones : "+resStr);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				if(os!=null)
					os.close();
				if(is!=null)
					is.close();
				if(socket!=null)
					socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return JSON.parseObject(resStr, typeRef);
	}
}
