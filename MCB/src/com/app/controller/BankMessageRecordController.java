package com.app.controller;
/**
 * 消息记录管理
 * @author 黄成
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankMessageAllocation;
import com.app.entity.BankMessageRecord;
import com.app.service.BankMessageAllocationService;
import com.app.service.BankMessageRecordService;
import com.app.socket.SocketMessage;

@Controller
@RequestMapping(value="/messageRecord")
public class BankMessageRecordController {
	
	@Autowired
	private BankMessageRecordService bankMessageRecordService;
	@Autowired
	private BankMessageAllocationService bankMessageAllocationService;
	
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
	public ModelAndView selectAllMessageAllocation(Model model,BankMessageRecord bankMessageRecord, ModelMap modelMap,HttpServletRequest request){
		List<BankMessageRecord> messageRecordList = bankMessageRecordService.selectBankMessageRecordPageList(bankMessageRecord);
		request.setAttribute("messageRecordList", messageRecordList);
		request.setAttribute("bankMessageRecord", bankMessageRecord);
		ModelAndView mv=new ModelAndView();
		mv.setViewName("bankMessageAllocation/bankMessageRecord");
		return mv;
	}
	
	
	/**
	 * 删除消息记录
	 * @author 黄成
	 * @param request
	 * @param out
	 */
	@RequestMapping(value="/delete_messageRecord")
	public void deleteAllocation(HttpServletRequest request,PrintWriter out){
		int bankMessageRecordId = Integer.parseInt((String)request.getParameter("recordId"));
		bankMessageRecordService.deleteMessageRecord(bankMessageRecordId);
		out.print("success");
		out.close();
	}
	
	/**
	 * 跳转到重发消息界面
	 * @author 黄成
	 * @param bankMessageRecord
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/resendMessageRecords")
	public ModelAndView resendMessageRecords(BankMessageRecord bankMessageRecord,HttpServletRequest request){//@RequestParam String date,@RequestParam String messageCode,
		ModelAndView mv = new ModelAndView();
		//SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		if("1".equals(bankMessageRecord.getMessageCode())){
			bankMessageRecord.setMessageCode("");
		}
		if(bankMessageRecord.getDate()==null){
			bankMessageRecord.setDate(new Date());
		}
		List<BankMessageAllocation> bankMessageAllocationList = new ArrayList<BankMessageAllocation>();
		List<BankMessageAllocation> bankMessageAllocationList1 = bankMessageAllocationService.selectMessageAllocationPageList(bankMessageRecord);
		if(bankMessageAllocationList1.get(0)!=null){
			bankMessageAllocationList = bankMessageAllocationList1;
		}
		request.setAttribute("bankMessageAllocationList",bankMessageAllocationList);
		request.setAttribute("bankMessageRecord",bankMessageRecord);
		mv.setViewName("bankMessageAllocation/resendMessageRecord");
		return mv;
	}
	
	/**
	 * 重发消息
	 * @author 黄成
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/resend_messageRecord",method=RequestMethod.POST)
	public void resendAllocation(HttpServletRequest request,BankMessageRecord bankMessageRecord,HttpServletResponse response){
		String errorInfo = "";
		String id = request.getParameter("recordId");
		int recordId = Integer.parseInt(id);
		bankMessageRecord.setRecordId(recordId);
		bankMessageRecordService.updateMessageRecordById(bankMessageRecord);
		String messageCode = request.getParameter("messageCode");
		String messageDate = request.getParameter("messageDate").substring(0,10);
		messageDate = messageDate.replace("-", "");
		Map<String, Object> resendList = SocketMessage.resend(messageDate,messageCode);
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
}
