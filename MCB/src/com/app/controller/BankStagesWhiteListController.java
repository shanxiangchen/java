package com.app.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankStagesWhiteList;
import com.app.listener.Const;
import com.app.service.BankStagesWhiteListService;
import com.app.util.ConfigManager;
import com.app.util.DESUtil;
 
 
@Controller
@RequestMapping(value="/stagesWhiteList")
public class BankStagesWhiteListController{
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private BankStagesWhiteListService bankStagesWhiteListService;
	  
	 @RequestMapping
	 public ModelAndView bankStagesWhitePageList(Model model,BankStagesWhiteList bankStagesWhiteList, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankStagesWhiteList> list = bankStagesWhiteListService.bankStagesWhitePageList(bankStagesWhiteList);
		modelMap.put("list", list);
		request.setAttribute("bankStagesWhiteList", bankStagesWhiteList);
		mv.setViewName("stagingWhite/stagingWhiteList");
		return mv;
		
	 }
	 /**
	  * 跳转到导入页面
	  * @return
	  */
	 @RequestMapping("/importWhite")
	 public String importStaging(){
		return "stagingWhite/importStagingWhiteList";
	 }
	 //跳转到增加页面
	 @RequestMapping("/addStagesWhite")
	 public String addStagesWhite(){
		 return "stagingWhite/addStagingWhite";
	 }
	 
	
	 @RequestMapping(value = "/saveStagesWhite")
	 public void  saveStagesWhite(BankStagesWhiteList bankStagesWhiteList,PrintWriter out) {
		int i=bankStagesWhiteListService.selectCountByPhone(bankStagesWhiteList.getListPhone());
		if(i>0){
			out.print("1");
			out.close();
		}else{
			bankStagesWhiteListService.insertStagesWhite(bankStagesWhiteList); 
			out.print("success");
			out.close();
		}
		
	 }
	 
	 @RequestMapping(value = "/importData")
	public void bank_book(@RequestParam("files") MultipartFile files,HttpServletRequest request, HttpServletResponse response,PrintWriter out, ModelMap model) {
		String flag="success";
		if (files != null) {

			try {
				String uploadTxt = request.getSession().getServletContext().getRealPath("uploadTxt");
				String uploadUrl = uploadTxt + File.separator;
				if (StringUtils.hasText(files.getOriginalFilename())) {
					String name = UUID.randomUUID().toString()+".txt";
					File targetFile = new File(uploadUrl, name);
					String charSet=getCharset(files.getInputStream());
					if(!"UTF-8".equals(charSet)){
						flag="1"; 
						return;
					}
					 //判断文件是否为空
					  InputStream inputStream = files.getInputStream();
					  InputStreamReader isReader = new InputStreamReader(inputStream);
					  BufferedReader bfReader = new BufferedReader(isReader);
					  String readLine = null;
					  int i = 0;
					  while ((readLine =bfReader.readLine())!=null) {
						  i++;
						  if(i==1){
							  continue;
						  }
						  if(i==2){
							  if(readLine.split(",").length!=1){
								  out.print("3");
								  return;
							  }else{
								  break;
							  }
						  }
					  }
					  if(i==1){
						  out.print("4");
						  return;
					  }
					// 判断文件是否存在 存在返回true
					if (!targetFile.exists()) {
						targetFile.mkdirs(); // 创建目录
					}
					files.transferTo(targetFile);
				 
					ThreadReadData readData=new ThreadReadData();
	        		Thread thReadData=new Thread(readData);
	        		readData.setBankStagesWhiteListService(bankStagesWhiteListService);
	        	    readData.setFilePath(uploadUrl);
	        	    readData.setFileName(name);
	        	    //thReadData.sleep(1000);
	        	    thReadData.setDaemon(true);
	        	    thReadData.start();
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
				flag="error"; 
			} catch (IOException e) {
				e.printStackTrace();
				flag="error"; 
			}finally{
				if(out!=null){
					out.write(flag);
					out.close();
				}
			}

		}
	}
	
	class ThreadReadData implements Runnable{
        private BankStagesWhiteListService bankStagesWhiteListService;
        private String filePath;
    	private String fileName;
		
		public BankStagesWhiteListService getBankStagesWhiteListService() {
			return bankStagesWhiteListService;
		}
		public void setBankStagesWhiteListService(
				BankStagesWhiteListService bankStagesWhiteListService) {
			this.bankStagesWhiteListService = bankStagesWhiteListService;
		}
		public String getFilePath() {
			return filePath;
		}
		public void setFilePath(String filePath) {
			this.filePath = filePath;
		}
		public String getFileName() {
			return fileName;
		}
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		 
		
		public void run() {
			String DB_USERNAME="";
	     	String DB_PASSWORD="";
	    	String DB_URL="";
	    	String  tableName="bank_stages_white_list";
	    	String fieldName="(LIST_PHONE)";
	    	String host = "";
	    	String port = "";
	    	String database = "";
	 		Connection conn=null;
	 		filePath = filePath.replace("\\", "/");
     		String ctlPath = filePath+"ctl/";
     		File file = new File(ctlPath);
     		if (!file.exists()) {
     		   file.mkdir();
     		}
     		try {
     			 DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
				 conn=datasource.getConnection();
				 DatabaseMetaData dmd=conn.getMetaData();
	     		 DB_USERNAME=dmd.getUserName();
	     		 String db_password= ConfigManager.getInstance().getString("db_password");
	     		 DB_PASSWORD=DESUtil.getDesString(db_password);
	     		 String url=dmd.getURL();
	     		 if(url!=null){
	     			String[] str=url.split("[?]")[0].split("//")[1].split(":");
	     			host= str[0];
	     			port = str[1].split("/")[0];
	     			database = str[1].split("/")[1];
	     		 }
	     		executivereadline(filePath,fileName,tableName,DB_USERNAME,DB_PASSWORD,DB_URL,host,port,database,fieldName,ctlPath,conn,bankStagesWhiteListService);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				if(conn!=null){
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
			
			 
		}
		
	}
	
	
	/**
	 * 执行文件
	 * @param filePath		 
	 * @param fileName		
	 * @param tableName		
	 */
	private void executivereadline(String filePath, String fileName, String tableName,String DB_USERNAME,String DB_PASSWORD,String DB_URL,String host,String port,String database,String filedName,String ctlPath,Connection conn,BankStagesWhiteListService bankStagesWhiteListService){
		long startTime = System.currentTimeMillis();
		try {
 			InputStreamReader readtxt = new InputStreamReader(new FileInputStream(new File(filePath+""+fileName)),"utf-8");
			BufferedReader br = new BufferedReader(readtxt);
			String linehead = "insert into "+tableName+" "+filedName+" values";
			String lineText = linehead;
			String lineStr = "";
			int count = 0;
			int readNum = 100;
			int badCount = 0;
			int goodCount = 0;
			int repetitionCount = 0;
			String baddata = "";
			String repetitiondata = "";
		    while ((lineStr=br.readLine()) != null) {
		        count++;
		        if(count != 1){
		        	if(lineStr.length()<=0){
		        		baddata+=count+",";
		        		badCount++;
		        		continue;
		        	}
		        	String[] str = lineStr.split(",");
		        	int strLength = str.length;
		        	if(strLength == 1){ 
		        		String phoneNum = str[0];
		        		int Num = bankStagesWhiteListService.selectCountByPhone(phoneNum);
		        		if(Num>0){
		        			repetitiondata+=count+",";
		        			//有重复数据
		        			repetitionCount++;
		        			continue;
		        		}else{
		        			//无重复数据,组装sql语句
				        	String phoneStr = "'"+str[0]+"'";
				        	if(lineText.indexOf(phoneStr)==-1){
				        		goodCount++;
				        		lineText = lineText+"("+phoneStr+"),";
		        			}else{
		        				repetitiondata+=count+",";
		        				repetitionCount++;
		        			}
		        		
				        	if(count%readNum == 0&&goodCount>0){
				        		StringBuilder sb = new StringBuilder(lineText);
						    	sb.replace(lineText.length()-1, lineText.length(), ";");
						    	lineText = sb.toString();
							    log.info("<mysql>"+lineText);	
			                    Statement stmt = conn.createStatement(); 
			                    stmt.addBatch(lineText); 
			                    stmt.executeBatch();    //执行批处理 
			                    stmt.close(); 
			                    lineText = linehead;
					        } 
		        		}
			        }else{
			        	baddata+=count+",";
		        		badCount++;
		        		continue;
			        }
		        }
		     }
		    if(count%readNum != 0&&goodCount>0){
		    	StringBuilder sb = new StringBuilder(lineText);
		    	sb.replace(lineText.length()-1, lineText.length(), ";");
		    	lineText = sb.toString();
				log.info("<mysql>"+lineText);	
                Statement stmt = conn.createStatement(); 
                stmt.addBatch(lineText); 
                stmt.executeBatch();    //执行批处理 
                stmt.close(); 
		    }
		     br.close();
		     readtxt.close();
		     long endTime = System.currentTimeMillis();
		     log.info(endTime-startTime);
		     if(badCount==0&&repetitionCount==0){
		    	 log.info("插入"+tableName+"表总数据: "+(count-1)+"条，坏数据： "+badCount+"条，重复数据： "+repetitionCount+"条，成功插入"+goodCount+"条数据。");
		     }else if(badCount==0&&repetitionCount>0){
		    	 log.info("插入"+tableName+"表总数据: "+(count-1)+"条，坏数据： "+badCount+"条，重复数据： "+repetitionCount+"条，重数据为第"+repetitiondata+"条数据，成功插入"+goodCount+"条数据。");
		     }else if(badCount>0&&repetitionCount==0){
		    	 log.info("插入"+tableName+"表总数据: "+(count-1)+"条，坏数据： "+badCount+"条，坏数据为第"+baddata+"条数据，重复数据： "+repetitionCount+"条，成功插入"+goodCount+"条数据。");
		     }else{
		    	 log.info("插入"+tableName+"表总数据: "+(count-1)+"条，坏数据： "+badCount+"条，坏数据为第"+baddata+"条数据，重复数据： "+repetitionCount+"条，重数据为第"+repetitiondata+"条数据，成功插入"+goodCount+"条数据。");
		     }
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
    //校验文件编码格式
	private String getCharset(InputStream inputStream) throws IOException{
        
        BufferedInputStream bin = new BufferedInputStream(inputStream);  
        int p = (bin.read() << 8) + bin.read();  
        
        String code = null;  
        
        switch (p) {  
            case 0xefbb:  
                code = "UTF-8";  
                break;  
            case 0xfffe:  
                code = "Unicode";  
                break;  
            case 0xfeff:  
                code = "Unicode big endian";  
                break;  
            default:  
                code = "ANSI";  
        }  
        return code;
    }
	
    @RequestMapping(value = "/delStag" )
    public void delStag(@RequestParam String speedyLists, PrintWriter out,HttpServletRequest request){
    	
    	 String strs[]=speedyLists.split(",");
    	 int i=bankStagesWhiteListService.deleteStagingList(strs);
    	 if(i>0){
    		 out.print("success");
  		     out.close();
    	 }else{
    		 out.print("error");
  		     out.close();
    	 }
    }

	 
	 
}
