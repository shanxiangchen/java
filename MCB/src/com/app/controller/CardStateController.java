package com.app.controller;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankUploadFtp;
import com.app.service.CardStateService;

/**
 * 卡片申请状态Controller方法
 * create date 2016/3/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
@Controller
@RequestMapping(value="/cardState")
public class CardStateController {
	@Autowired
	private CardStateService  cardStateService;
	@RequestMapping
	public ModelAndView listCardState(HttpServletRequest request,BankUploadFtp bankUploadFtp,ModelMap modelMap){
		ModelAndView mv=new ModelAndView();
		List<BankUploadFtp> list=cardStateService.selectFtpPageList(bankUploadFtp);  
		//List<CardState> list = cardStateService.listCardState(cardState);
		//request.setAttribute("cardState", cardState);
		modelMap.put("list", list);
		request.setAttribute("bankUploadFtp", bankUploadFtp);
		mv.setViewName("cardState/cardState");
		return mv;	
	}
	
	/**
	 * 导出客户申请信息
	 * @author zhao.lei
	 * @date 2016-4-14 下午2:54:30
	 */
	@RequestMapping(value="/exportData")
	public void exportData(@RequestParam String insertSysDate,HttpServletRequest request,HttpServletResponse response){
		//TXT文档字段：客户姓名、登陆已持卡卡号、手机号码、是否开通密码（开通1，不开通0）、是否分期（送0）、分期起始金额（送空）、分期期数（送空）、加办卡片名称、卡产品编码、主卡版面代码、1098、申请条形码、EAMIL地址 
		//TXT文档模板：古全|6229028154651106|13713379958|1|0|||星夜星座信用卡|1177|A|1098|1509250590007999800|1571888202@qq.com 
	 
		String uploadTxt="";
		String txtName="";
		String name="";
		SimpleDateFormat sdf =new SimpleDateFormat("yyMMdd");
		SimpleDateFormat sf =new SimpleDateFormat("yyyyMMdd");
		if("".equals(insertSysDate)){
			Date date=new Date();
			date.setTime(System.currentTimeMillis()-1000*24*60*60);
			txtName=sdf.format(date);
			name=sf.format(date);
			SimpleDateFormat dateformat =new SimpleDateFormat("yyyy-MM-dd");
			insertSysDate=dateformat.format(date);
		}else{
			txtName=insertSysDate.replaceAll("-", "");
			name=txtName;
			txtName=txtName.substring(2); 
		}
		List<BankUploadFtp> list=cardStateService.selectBankUploadFtpList(insertSysDate);
		if(list.size()==0){
			try {
				PrintWriter out=response.getWriter();
				out.print("1");
				out.close();
				return;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		uploadTxt = request.getSession().getServletContext()
				.getRealPath("uploadTxt");
		File txtFile=mkdirFile(txtName,uploadTxt,insertSysDate,list); //生成TXT文件
		downLoadFile(uploadTxt,txtFile,name,request,response);//下载TXT文件
		
	}
	
	//生成TXT文件
	public File mkdirFile(String txtName,String uploadTxt,String insertSysDate,List<BankUploadFtp> list){
		FileWriter fw=null;
		BufferedWriter bw=null;
		File txtFile=null;
		try {
			 
			List<String> bufferList=new ArrayList<String>();
			//String txtDemo="客户姓名|登陆已持卡卡号|手机号码|是否开通密码（开通1，不开通0）|是否分期（送0）|分期起始金额（送空）|分期期数（送空）|加办卡片名称|卡产品编码|主卡版面代码|1098|申请条形码|EAMIL地址";
			//bufferList.add(txtDemo);
			//List<BankUploadFtp> list=cardStateService.selectBankUploadFtpList(insertSysDate);
			 
			int lowFour=1;//流水低位四位
			int hinTwo=20;//流水高位两位
			String barCodeNumber="";
			for(BankUploadFtp bankFtp:list){
				if(lowFour==9999){
					hinTwo++;
					lowFour=0;
				}else{
					String str=String.format("%04d",lowFour); 
					barCodeNumber=txtName+"0590"+hinTwo+"7"+str+"00";//条形码 yyMMdd + 0590 + 流水高位两位 + 7 + 流水低位四位 + 00
				}
				String custName=bankFtp.getCustName();//客户姓名
				String cardNum=bankFtp.getCardNum();//登录卡号
				String custPhone=bankFtp.getCustPhone();//手机号码
				String cardConsumerPwd=bankFtp.getCardConsumerPwd();//境内是否开通密码
				String autoStages=bankFtp.getAutoStages();//是否自动分期
				//String autoStagesMoney=bankFtp.getAutoStagesMoney();//分期起始金额
				//String autoStagesNum=bankFtp.getAutoStagesNum();//分期期数
				String cardName=bankFtp.getCardName();//加办卡卡片名称
				String cardId=bankFtp.getCardId();//卡种编号
				String mainCardPage=bankFtp.getMainCardPage();//卡种版面代码
				//String barCodeNumber=bankFtp.getBarCodeNumber();//申请条形编码
				String businessSource=bankFtp.getBusinessSource();//业务来源 ：邮箱地址
				//Date insertSysDate=bankFtp.getInsertSysDate();//申请日期
				//String insertSysDates=sdf.format(insertSysDate);
				StringBuffer strs=new StringBuffer("");
				strs.append(custName).append("|").append(cardNum).append("|").append(custPhone).append("|").append(cardConsumerPwd)
				.append("|").append(autoStages).append("|||").append(cardName).append("|").append(cardId).append("|").append(mainCardPage).append("|")
				.append("1098").append("|").append(barCodeNumber).append("|").append(businessSource);
				bufferList.add(strs.toString());
				lowFour++; 
			}
		  
			File file=new File(uploadTxt);
			if(!file.exists()){
				file.mkdir();
			}
			//定义文件名格式并创建
			txtFile=File.createTempFile("YICHIKA", ".txt",file); 
//			fw=new FileWriter(txtFile);
		    bw=new BufferedWriter(new OutputStreamWriter(new FileOutputStream(txtFile),"gbk"));
			for(int i=0;i<bufferList.size();i++){
			    bw.write(bufferList.get(i));
			    bw.write("\r\n");
			}
		 
			 
		} catch (Exception e) {
			e.printStackTrace();
			 
		}finally{
			try {
				if(bw!=null){
					bw.flush();
					bw.close();
				}
				if(fw!=null){
					fw.close();
				}
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
			 
		}
		
		return txtFile;
	}
	//下载TXT文件
	public void downLoadFile(String uploadTxt,File txtFile,String txtName,HttpServletRequest request,HttpServletResponse response){
		BufferedReader bis = null;  
		BufferedOutputStream bos = null;  
	    //获取下载文件路径
	    String downLoadPath = uploadTxt+File.separator+txtFile.getName();  
	    //获取文件的长度
	    long fileLength = new File(downLoadPath).length();  

	    //设置文件输出类型
	    response.setContentType("application/octet-stream");  
	    try {
	    	request.setCharacterEncoding("utf-8");
	    	String name="YICHIKA_"+txtName+".txt";
			response.setHeader("Content-disposition", "attachment; filename="  
			    + new String(name.getBytes("utf-8"), "ISO8859-1"));
			 //设置输出长度
		    response.setHeader("Content-Length", String.valueOf(fileLength));  
		    //获取输入流
		    bis = new BufferedReader(new InputStreamReader(new FileInputStream(downLoadPath),"gbk"));  
		    //输出流
		    bos = new BufferedOutputStream(response.getOutputStream());  
//		    byte[] buff = new byte[2048];  
		    String bytesRead = null;  
		    while ((bytesRead = bis.readLine())!= null) {  
		      bos.write(bytesRead.getBytes("gbk"));  
		      bos.write("\r\n".getBytes("gbk"));
		    }   
		     System.out.println(bytesRead);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			 
			e.printStackTrace();
		}finally{
			//关闭流
	    	try {
	    		  if(bis!=null){
				   bis.close();
	    		  }
	    		  if(bos!=null){
	  		    	bos.close(); 
	  		      }
	    		  txtFile.delete();
			} catch (IOException e) {
				e.printStackTrace();
			}  
		    
		   
		} 
	   
	}
	
	 
}
