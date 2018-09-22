package com.app.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.ClientWhiteImport;
import com.app.entity.ClientWhiteList;
import com.app.entity.User;
import com.app.listener.Const;
import com.app.service.ClientWhiteListService;
import com.app.util.ConfigManager;
import com.app.util.DESUtil;
 
/**
 * 白名单Controller
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/clientWhiteList")
public class ClientWhiteListController{
	private final Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ClientWhiteListService clientWhitListService;
 	
	
	/**
	 * 客户白名单显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	@RequestMapping
	public String clientWhiteList(Model model,ClientWhiteImport whiteImport, ModelMap modelMap,HttpServletRequest request){
		 
		List<ClientWhiteImport> list = clientWhitListService.selectWhiteImportListPage(whiteImport);
		modelMap.put("list", list);
		model.addAttribute("whiteImport", whiteImport);
		return "clientWhiteList/clientWhiteList";
		
	}
	
	@RequestMapping(value = "/importout")
	public String importOtu() {
		return "clientWhiteList/importClienWhiteList";
	}
	 
	/**
	 * 导入白名单
	 * @param files
	 * @param request
	 * @param response
	 * @param out
	 */
	@SuppressWarnings("static-access")
	@RequestMapping(value="/addClientWhiteList",method=RequestMethod.POST)
	public void saveClientWhiteList(@RequestParam("files") MultipartFile files,HttpServletRequest request,HttpServletResponse response,PrintWriter out){
		 
		User user=(User)request.getSession().getAttribute("user");
		Date date=new Date();
		SimpleDateFormat sim=new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss");
	    String time=sim.format(date);
	   String seqId="";
		try {
		    //导入文件
		    if (files != null) {
		    	String uploadTxt = request.getSession().getServletContext()
						.getRealPath("uploadTxt");
		    	String uploadUrl=uploadTxt+File.separator;
		    	//文件名是否为空
		    	if (StringUtils.hasText(files.getOriginalFilename())) {
			    		  String name = UUID.randomUUID().toString()+".txt";
			    		  File targetFile = new File(uploadUrl, name);
			    		  String charSet=getCharset(files.getInputStream());
						  if(!"UTF-8".equals(charSet)){
								out.print("1");
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
							  if(i!=1){
								  String[] testList=readLine.split("\\|\\|\\|");
								  if(readLine.split("\\|\\|\\|").length!=7){
									  out.print("3");
									  return;
								  }/*else if(!(readLine.split("\\|\\|\\|")[readLine.split("\\|\\|\\|").length-1].equals("3")||readLine.split("\\|\\|\\|")[readLine.split("\\|\\|\\|").length-1].equals("2")||readLine.split("\\|\\|\\|")[readLine.split("\\|\\|\\|").length-1].equals("1"))){
									  out.print("3");
									  return;
								  }*/else if(!((testList[0].indexOf(" ")==-1)||(testList[1].indexOf(" ")==-1)
										  ||(testList[2].indexOf(" ")==-1)||(testList[3].indexOf(" ")==-1)
										  ||(testList[4].indexOf(" ")==-1)||(testList[5].indexOf(" ")==-1))){
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
					      //判断文件是否存在 存在返回true
					      if(!targetFile.exists()){ 
					    	  //创建目录
					          targetFile.mkdirs(); 
					      }  
				          try { 
				                files.transferTo(targetFile);
				        	    log.info("<file weblogic Path>"+targetFile);
				        	    String size=FormetfileSizeize(files.getSize());
				        	    log.info("<file targetFile>"+targetFile);
				        	    ClientWhiteImport cli=new ClientWhiteImport(files.getOriginalFilename(),name,uploadUrl,size,time,"1",user.getLoginname());
				        	    int radom1 = (int) (Math.random()*10000000);
				        	    SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
				        	    String time1=fmt.format(date);
				        	    String cliId = radom1+time1;
				        	    cli.setWhiteImportId(cliId);
				        	    clientWhitListService.insertClientWhiteImport(cli);
				        	    seqId = cli.getWhiteImportId();
				        	    out.print("2");
				        	    ThreadReadData readData=new ThreadReadData();
				        		Thread thReadData=new Thread(readData);
				        		readData.setClientWhitListService(clientWhitListService);
				        	    readData.setFilePath(uploadUrl);
				        	    readData.setFileName(name);
				        	    readData.setSeqId(seqId);
				        	    thReadData.sleep(1000);
				        	    thReadData.setDaemon(true);
				        	    thReadData.start();
				        	    
				          }catch (Exception e) {
				        	  e.printStackTrace();
						  }finally{
							  if(out!=null){
								  out.close();
							  }
							   
						  }
		    	}
		    }	
		}catch (Exception e) {
			e.printStackTrace();
		}    
	}
	
	
	/**
	 * 执行文件
	 * @param filePath		 
	 * @param fileName		
	 * @param tableName		
	 */
	private void executivereadline(String filePath, String fileName, String tableName,String DB_USERNAME,String DB_PASSWORD,String DB_URL,String host,String port,String database,String filedName,String ctlPath,Connection conn,String seqId,ClientWhiteListService clientWhitListService){
		long startTime = System.currentTimeMillis();
		try {
 			InputStreamReader readtxt = new InputStreamReader(new FileInputStream(new File(filePath+""+fileName)),"utf-8");
			BufferedReader br = new BufferedReader(readtxt);
			String linehead = "insert into "+tableName+" "+filedName+" values";
			String lineText = linehead;
			String lineStr = "";
			int count = 0;
			int badCount = 0;
			int goodCount=0;
			int repetitionCount = 0;
			int readNum = 100;
			String baddata = "";
			String repetitiondata = "";
		    while ((lineStr=br.readLine()) != null) {
		    	//如果数据没问题，count++,如果数据有问题badCount++;
		        count++;
		        String linecontrolstr = "";
		        if(count != 1){
		        	if(lineStr.length()<=0){
		        		baddata+=count+",";
		        		badCount++;
		        		continue;
		        	}
		        	String[] str = lineStr.split("\\|\\|\\|");
		        	int strLength = str.length;
		        	boolean bbl=(str[0].indexOf(" ")==-1)&&(str[1].indexOf(" ")==-1)
							  &&(str[2].indexOf(" ")==-1)&&(str[3].indexOf(" ")==-1)
							  &&(str[4].indexOf(" ")==-1)&&(str[5].indexOf(" ")==-1)&&(str[6].indexOf(" ")==-1);
		        	//长度必须是7 条目内不能有空格
		        	if(strLength == 7&&bbl==true){
		        		ClientWhiteList clientWhite = new ClientWhiteList();
		        		clientWhite.setWhiteListphone(str[5]);
		        		clientWhite.setActivityNo(str[4]);
		        		clientWhite.setWhiteListType(str[6]);
		        		//检查是否拥有此号码
		        		int whiteClientNo = clientWhitListService.selectWhiteClientNo(clientWhite);
		        		if(whiteClientNo>0){
		        			repetitiondata+=count+",";
		        			//有重复数据
		        			repetitionCount++;
		        		}else{
		        			//无重复数据,组装sql语句
		        			for (int i= 0;i<str.length;i++) {
				        		if(i!=str.length-1){
				        			linecontrolstr = linecontrolstr+"'"+str[i]+"',";
				        		}else{
				        			linecontrolstr = linecontrolstr+"'"+str[i]+"'";
				        		}
							}
		        			String strline = "'"+str[4]+"','"+str[5]+"'";
		        			if(lineText.indexOf(strline)==-1){
		        				goodCount++;
		        				lineText = lineText+"("+linecontrolstr+",'"+seqId+"'),";
		        			}else{
		        				repetitiondata+=count+",";
		        				repetitionCount++;
		        			}
				        	if(count%readNum == 0&&goodCount>0){
				        		StringBuilder sb = new StringBuilder(lineText);
						    	sb.replace(lineText.length()-1, lineText.length(), ";");
						    	lineText = sb.toString();
							    log.info("<mysql>"+lineText);
							   /* Map<String, String> mapStr = new HashMap<String, String>();
								mapStr.put("lineText", lineText);
								String flag = "0";
								mapStr.put("flag", flag);
								clientWhitListService.sqlexecute(mapStr);*/
							    Statement stmt = conn.createStatement(); 
			                    stmt.addBatch(lineText); 
			                    stmt.executeBatch();//执行批处理 
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
				/*Map<String, String> mapStr = new HashMap<String, String>();
				mapStr.put("lineText", lineText);
				String flag = "0";
				mapStr.put("flag", flag);
				clientWhitListService.sqlexecute(mapStr);*/
                Statement stmt = conn.createStatement(); 
                stmt.addBatch(lineText); 
                stmt.executeBatch();    //执行批处理 
                stmt.close(); 
		    }
		     br.close();
		     readtxt.close();
		     long endTime = System.currentTimeMillis();
		     log.info(endTime-startTime);
		     String loginfo = "";
		     if(badCount==0&&repetitionCount==0){
		    	 loginfo = "插入总数据: "+(count-1)+"条，坏数据： "+badCount+"条，重复数据： "+repetitionCount+"条，成功插入"+goodCount+"条数据。";
		     }else if(badCount==0&&repetitionCount>0){
		    	 loginfo ="插入总数据: "+(count-1)+"条，坏数据： "+badCount+"条，重复数据： "+repetitionCount+"条，重数据为第"+repetitiondata+"条数据，成功插入"+goodCount+"条数据。";
		     }else if(badCount>0&&repetitionCount==0){
		    	 loginfo ="插入总数据: "+(count-1)+"条，坏数据： "+badCount+"条，坏数据为第"+baddata+"条数据，重复数据： "+repetitionCount+"条，成功插入"+goodCount+"条数据。";
		     }else{
		    	 loginfo ="插入总数据: "+(count-1)+"条，坏数据： "+badCount+"条，坏数据为第"+baddata+"条数据，重复数据： "+repetitionCount+"条，重数据为第"+repetitiondata+"条数据，成功插入"+goodCount+"条数据。";
		     }
		     ClientWhiteImport clientWhiteImport = new ClientWhiteImport();
		     clientWhiteImport.setWhiteImportId(seqId);
		     clientWhiteImport.setLoginfo(loginfo);
		     clientWhitListService.updateClientWhiteImport(clientWhiteImport);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
     
	public  void importData(ClientWhiteListService clientWhitListService,String filePath,String fileName,String seqId) {
     	String DB_USERNAME="";
     	String DB_PASSWORD="";
    	String DB_URL="";
    	String flag="";
    	String activityNo="";
    	String  tableName="",fieldName="";
    	String host = "";
    	String port = "";
    	String database = "";
		InputStreamReader read =null;
 		BufferedReader bufferedReader =null;
 		Connection conn=null;
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
     		  
			 read = new InputStreamReader(
			 new FileInputStream(filePath+fileName),"utf-8");
			 bufferedReader = new BufferedReader(read);
             String lineTxt = null;
             int i=1;
             while((lineTxt = bufferedReader.readLine()) != null){
            	if(i==1){
            		i++;
            		continue;
            	}
            	String[] str=lineTxt.split("\\|\\|\\|");
            	flag=str[6];
            	activityNo=str[4];
            	if(i==2){
            		break;
            	}
             }
            
             
            if(flag.equals("1")){
            	int count= clientWhitListService.selectApplyWhiteListOfActNo(activityNo);
            	if(count>0){
            		clientWhitListService.deleteAllApplyWhiteList(activityNo);
            	}
  				tableName = "bank_apply_white_list";//"BANK_APPLY_WHITE_LIST";
 			    fieldName = "(WHITE_LIST_NAME,PAPERS_TYPE,PAPERS_NUM,ACTIVITY_TYPE,ACTIVITY_NO,WHITE_LIST_PHONE,WHITE_LIST_TYPE,WHITE_IMPORT_ID)";//,WHITE_LIST_ID \"seq_bk_apply_white_list.nextval\"
 		
            }
  	
		  	if(flag.equals("2")){
		  		int count=clientWhitListService.selectClientWhiteListOfActNo(activityNo);
		  		if(count>0){
		  			clientWhitListService.deleteAllClientWhiteList(activityNo);
		  		}
  				tableName = "bank_client_white_list";
 			    fieldName = "(WHITE_LIST_NAME,PAPERS_TYPE,PAPERS_NUM,ACTIVITY_TYPE,ACTIVITY_NO,WHITE_LIST_PHONE,WHITE_LIST_TYPE,WHITE_IMPORT_ID)";//,WHITE_LIST_ID \"seq_bk_client_white_listt.nextval\"
		 		
		  	}
		
		  	if(flag.equals("3")){
		  		int count=clientWhitListService.selectStandardWhiteListOfActNo(activityNo);
		  		if(count>0){
		  			clientWhitListService.deleteAllStandardWhiteList(activityNo);
		  		}
  		   		tableName = "bank_standard_white_list";
 			    fieldName = "(WHITE_LIST_NAME,PAPERS_TYPE,PAPERS_NUM,ACTIVITY_TYPE,ACTIVITY_NO,WHITE_LIST_PHONE,WHITE_LIST_TYPE,WHITE_IMPORT_ID)";//,WHITE_LIST_ID \"seq_bk_standard_white_list.nextval\"
 		
		  	}
		  	filePath = filePath.replace("\\", "/");
     		String ctlPath = filePath+"ctl/";
     		File file = new File(ctlPath);
     		if (!file.exists()) {
     		   file.mkdir();
     		}
     		executivereadline(filePath,fileName,tableName,DB_USERNAME,DB_PASSWORD,DB_URL,host,port,database,fieldName,ctlPath,conn,seqId,clientWhitListService);
     		ClientWhiteImport clientWhiteImport = new ClientWhiteImport();//更新处理状态
     		clientWhiteImport.setWhiteImportId(seqId);;
     		clientWhiteImport.setWhiteSaveNo(flag);
     		clientWhitListService.updateClientWhiteImport(clientWhiteImport);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (SQLException e1) {
			e1.printStackTrace();
		}finally{
			try {
				if (bufferedReader != null) {
					bufferedReader.close();
				}
				
				if (read != null) {
					read.close();
				}
				
				if(conn!=null){
					conn.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			 
		}
	}
    
    /**
	 * 转换文件大小
	 * @param fileSize
	 * @return
	 */
	public String FormetfileSizeize(long fileSize) {
	       DecimalFormat df = new DecimalFormat("#.00");
	       String fileSizeizeString = "";
	       if (fileSize < 1024) {
	           fileSizeizeString = df.format((double) fileSize) + "B";
	       } else if (fileSize < 1048576) {
	           fileSizeizeString = df.format((double) fileSize / 1024) + "K";
	       } else if (fileSize < 1073741824) {
	           fileSizeizeString = df.format((double) fileSize / 1048576) + "M";
	       } else {
	           fileSizeizeString = df.format((double) fileSize / 1073741824) +"G";
	       }
	       return fileSizeizeString;
	}
	
	@RequestMapping(value = "/deleteWhiteImport" )
	public void  deleteWhiteImport(@RequestParam String whiteImportId, PrintWriter out,HttpServletRequest request){
		 try {
			   String strs[]=whiteImportId.split(",");
			   for (int i = 0,b=strs.length; i < b; i++) {
				   if(strs[i] != null && strs[i]!=""){
					   ClientWhiteImport clientWhiteImport = clientWhitListService.selectClientWhiteImportById(strs[i]);
					   if(clientWhiteImport!=null){
						   ClientWhiteList clientWhiteList = new ClientWhiteList();
						   clientWhiteList.setWhiteListType(clientWhiteImport.getWhiteSaveNo());
						   clientWhiteList.setWhiteImportId(clientWhiteImport.getWhiteImportId());
						   int whiteImportNum = clientWhitListService.selectClientWhiteListByWhiteImport(clientWhiteList);
						   if(whiteImportNum>0){
							   clientWhitListService.deleteClientWhiteList(clientWhiteImport);
						   }
						   clientWhitListService.deleteWhiteImportById(strs[i]);
						   //clientWhitListService.deleteWhiteImport(strs);
						   String uploadTxt = request.getSession().getServletContext()
									.getRealPath("uploadTxt");
						   String ctlPath = uploadTxt +"\\ctl";
						   for(int j=0;j<strs.length;j++){
							   File fileTxt=new File(uploadTxt+File.separator+strs[j]+".txt");
							   File fileCtl=new File(ctlPath+File.separator+strs[j]+".txt");
							   File fileLog=new File(uploadTxt+File.separator+strs[j]+".log");
							   File fileBad=new File(uploadTxt+File.separator+strs[j]+".bad");
							   if(fileTxt.exists()){
								   fileTxt.delete();
							   }
							   if(fileCtl.exists()){
								   fileCtl.delete();
							   }
							   
							   if(fileLog.exists()){
								   fileLog.delete();
							   }
							   
							   if(fileBad.exists()){
								   fileBad.delete();
							   }
	
						   }
						   out.print("success");
						   out.close();
					   }
				   }
			   }
		 } catch (Exception e) {
			 e.printStackTrace();
			 out.print("error");
			 out.close();
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
}


 /**
  * 读取文件校验数据
  * @author admin
  *
  */
 class ThreadReadData implements Runnable {
	private ClientWhiteListService clientWhitListService;
	private String filePath;
	private String fileName;
	private String seqId;
	
	public ClientWhiteListService getClientWhitListService() {
		return clientWhitListService;
	}
	public void setClientWhitListService(ClientWhiteListService clientWhitListService) {
		this.clientWhitListService = clientWhitListService;
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
	public String getSeqId() {
		return seqId;
	}
	public void setSeqId(String seqId) {
		this.seqId = seqId;
	}
	@Override
	public void run() {
		new ClientWhiteListController().importData(clientWhitListService,filePath,fileName,seqId);
	}
	
}

 