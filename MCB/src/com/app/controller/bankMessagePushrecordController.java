package com.app.controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.app.socket.SocketMessage;

@Controller
@RequestMapping(value="/messagepushrecord")
public class bankMessagePushrecordController {
	
	
	/**
	 * 查询消息记录
	 * @author 黄成
	 * @param model
	 * @param bankMessageAllocation
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping
	public ModelAndView resendAllocation(HttpServletRequest request,HttpServletResponse response){
		String card=request.getParameter("card");
		ModelAndView mv=new ModelAndView();
		String	errorInfo="";
		if(card=="" ||card==null){
			mv.setViewName("bankMessagePushrecord/bankMessagePushrecord");
		}else{
		Map<String, Object> resendList = SocketMessage.resend(card);
		Map<String, Object> callNetionResult = SocketMessage.callNetionService(resendList);
		String value1 = (String)callNetionResult.get("retCode");
		if("0000000".equals(value1)){
			errorInfo = "success";
		}else{
			errorInfo = "fail";
		}
		//返回前端数据
				Map<String,Object> data = new HashMap<String, Object>();
				data.put("errorInfo", errorInfo);
				PrintWriter pt = null;
				JSONObject json = JSONObject.fromObject(data);
				String jsonStr = json.toString();
				try {
					response.setContentType("text/html;charset=utf-8");
					request.setCharacterEncoding("utf-8");
					pt = response.getWriter();
					pt.write(jsonStr);
					pt.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					if(pt != null){
						pt.close();
					}
				}
	}
	return mv;
	}
}
