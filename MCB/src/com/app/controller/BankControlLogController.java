package com.app.controller;
 
 
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankControlLog;
import com.app.entity.BankControlShow;
import com.app.service.BankControlLogService;
 
@Controller
@RequestMapping(value="/controlLog")
public class BankControlLogController{
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BankControlLogService bankControlLogService;
	 
	  
	@RequestMapping
	public ModelAndView selectBankControlLogList(Model model,BankControlShow bankControlShow, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankControlShow> list=bankControlLogService.selectBankControlShowList(bankControlShow);
		modelMap.put("list", list);
		
		request.setAttribute("bankControlShow", bankControlShow); 
		mv.setViewName("bankControlLog/controlLogList");
		return mv;
		 
	}
	
	/*
	 * 详情页面
	 */
	@RequestMapping(value="/controlLogInfo")
	public ModelAndView controlLogInfo(@RequestParam String controlId, HttpServletRequest request,HttpServletResponse response ){
		ModelAndView mv = new ModelAndView();
		  
		BankControlLog bankControlLog=bankControlLogService.selectControlLogById(controlId);
		 
		mv.addObject("bankControlLog",bankControlLog);
		 
		mv.setViewName("bankControlLog/controlLogInfo");
		 
		return mv;
	}
	
	/*
	 * 阀值配置
	 */
	@RequestMapping(value="/addControlRate")
	public ModelAndView addControlRate(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<Map<String,String>> list=bankControlLogService.selectControlConfigAll();
		request.setAttribute("list",list);
		mv.setViewName("bankControlLog/updateControlLog");
		return mv;
	}
	
	
	
	@RequestMapping(value = "/controlConfig", method = RequestMethod.POST)
	public void updateControlConfig(@RequestParam String configRate, HttpServletRequest request,ModelMap modelMap,PrintWriter out){
		 
		String strs[]=configRate.split(",");
		for(int i=0;i<strs.length;i++){
			 Map<String,String> map=new HashMap<String,String>();
			 map.put("configType",String.valueOf(i+1));
			 map.put("configRate",strs[i]);
			 bankControlLogService.updateControlConfig(map);
		}
		out.print("success");
		out.close();
		 
	}
	
	/*
	 * 删除
	 */
	@RequestMapping(value = "/delControlShow" )
	public void  delControlShow(@RequestParam String showIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=showIds.split(",");
		   int i=bankControlLogService.delControlShow(strs); 
		   if(i>0){
			   out.print("success");
			   out.close();
		   }else{
			   out.print("error");
			   out.close();
		   }
		  
	}
	
	 
	
	
	/*
	 * APP监控管理定时任务
	 */
	public void controlWork(){
		log.info("==> 开始执行APP监控管理定时任务");
		SimpleDateFormat dm =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		Date date=new Date();
		String nowDay=df.format(date);
		String nowTime=dm.format(date);
		long time=(date.getTime()/1000)-60*10;//往前推10分钟
		date.setTime(time*1000);
		Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -7);
		String bfDate=df.format(cal.getTime()); //7天前日期
		String bfTime=dm.format(date);//前推10分钟时间
		Map<String,String> map= new HashMap<String,String>();
		map.put("nowTime", nowTime);
		map.put("bfTime", bfTime);
		List<String> list=bankControlLogService.selectControCauseNo(map); 
		
		if(list==null||list.size()==0){
			String configRate=bankControlLogService.selectControlConfig("5");//根据监控编号查询监控阀值查询
			BankControlShow bankControlShow=new BankControlShow();
			bankControlShow.setShowAlarmInfo("登录模拟交易失败率超过阀值");
			bankControlShow.setShowSuccessNum("0");
			bankControlShow.setShowErrorNum("5");
			bankControlShow.setShowErrorRate("100%");
			bankControlShow.setShowInsertDate(nowDay);
			bankControlShow.setShowInsertTime(nowTime);
			bankControlShow.setShowIsOk("1");
			bankControlShow.setShowRemarks("标准阀值"+configRate+"%，现阀值100%");
			bankControlShow.setShowType("5");
			bankControlLogService.insertControlShow(bankControlShow);
			log.info("==>APP监控管理定时任务执行结束");
			return;
		}else if(list!=null&&!list.contains("5")){
			String configRate=bankControlLogService.selectControlConfig("5");//根据监控编号查询监控阀值查询
			BankControlShow bankControlShow=new BankControlShow();
			bankControlShow.setShowAlarmInfo("登录模拟交易失败率超过阀值");
			bankControlShow.setShowSuccessNum("0");
			bankControlShow.setShowErrorNum("5");
			bankControlShow.setShowErrorRate("100%");
			bankControlShow.setShowInsertDate(nowDay);
			bankControlShow.setShowInsertTime(nowTime);
			bankControlShow.setShowIsOk("1");
			bankControlShow.setShowRemarks("标准阀值"+configRate+"%，现阀值100%");
			bankControlShow.setShowType("5");
			bankControlLogService.insertControlShow(bankControlShow);
		}
		String showAlarmInfo="";//告警内容（中文） 
		String remarks="";//备注说明
		String configRate="";//标准阀值
		int noNum=0;int okNum=0;
		for(int i=0;i<list.size();i++){
			String  controlCauseNo=list.get(i);
			map.put("controlCauseNo", controlCauseNo);
			map.put("controlIsOk", "Y");
			configRate=bankControlLogService.selectControlConfig(controlCauseNo);//根据监控编号查询监控阀值查询
			if(configRate==null||"".equals(configRate)){
				continue;
			}
			double cfRate=Double.valueOf(configRate);
			okNum=bankControlLogService.selectControlCount(map); 
			if("5".equals(controlCauseNo)){
				 if(okNum<5){
					 noNum=5-okNum;
				 }else{
					continue;
				 }
			}else{
				map.put("controlIsOk", "N");
				noNum=bankControlLogService.selectControlCount(map); 
			}
			double rate=Math.round((double)noNum/(okNum+noNum)*100);
			String erRate=String.valueOf(rate)+"%";
			 
			if(rate>cfRate){
				if("1".equals(controlCauseNo)){
					showAlarmInfo="CCGW交易失败率超过阀值";
				}else if("2".equals(controlCauseNo)){
					showAlarmInfo="制卡系统加密机失败率超过阀值";
				}else if("3".equals(controlCauseNo)){
					showAlarmInfo="中间业务平台加密机失败率超过阀值";
				}else if("4".equals(controlCauseNo)){
					showAlarmInfo="彩信平台交易失败率超过阀值";
				}else if("5".equals(controlCauseNo)){
					showAlarmInfo="登录模拟交易失败率超过阀值";
				}else if("6".equals(controlCauseNo)){
					showAlarmInfo="Apache服务器连接失败率超过阀值";
				}
				remarks="标准阀值"+cfRate+"%，现阀值"+erRate;
				BankControlShow bankControlShow=new BankControlShow();
				bankControlShow.setShowAlarmInfo(showAlarmInfo);
				bankControlShow.setShowSuccessNum(String.valueOf(okNum));
				bankControlShow.setShowErrorNum(String.valueOf(noNum));
				bankControlShow.setShowErrorRate(erRate);
				bankControlShow.setShowInsertDate(nowDay);
				bankControlShow.setShowInsertTime(nowTime);
				bankControlShow.setShowIsOk("1");
				bankControlShow.setShowRemarks(remarks);
				bankControlShow.setShowType(controlCauseNo);
				bankControlLogService.insertControlShow(bankControlShow);
			}else{
				continue;
			}
		}
		
		//删除7天前的历史数据
		bankControlLogService.delControlLog(bfDate);
		log.info("==>APP监控管理定时任务执行结束");
		
	}
	
	 
	
	 
	 
}
