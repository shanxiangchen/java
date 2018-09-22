package com.app.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.StagingList;
import com.app.service.StagingListService;

@Controller
@RequestMapping("/stagingList")
public class StagingListController {
	@Autowired
	private StagingListService stagingListService;
	
	 @RequestMapping
	 public ModelAndView listStagingList(StagingList stagingList, HttpServletRequest request, ModelMap modelMap ){
		ModelAndView mv= new ModelAndView();
			List<StagingList> stagingLists = stagingListService.stagingListPageList(stagingList);
		modelMap.put("stagingLists", stagingLists);
		request.setAttribute("stagingList", stagingList);
		mv.setViewName("StagingList/stagingList");   
		   
	    return mv;
	   }
	
	 @RequestMapping("/importout")
	 public String importStaging(){
		return "StagingList/importStagingList";
	 }
	 
	@RequestMapping(value = "/importData")
	public void bank_book(@RequestParam("files") MultipartFile files,
			HttpServletRequest request, HttpServletResponse response,
			PrintWriter out, ModelMap model) {
		String flag="success";
		if (files != null) {

			try {
				String uploadTxt = request.getSession().getServletContext()
						.getRealPath("uploadTxt");
				String uploadUrl = uploadTxt + File.separator;
				String name = UUID.randomUUID().toString()+".txt";
				
				File file=new File(uploadUrl);
		     	File[] filelist=file.listFiles();//获取文件列表
		     	for(int j=0;j<filelist.length;j++){
		     	if(!filelist[j].isFile())
		     		continue;//如果不是文件就跳过（排除文件夹等）
		     	String fileName=filelist[j].getName();
		     	if(fileName.endsWith(".txt"))
		     		filelist[j].delete();//后缀名为txt就删除
		     	}
				
				if (StringUtils.hasText(files.getOriginalFilename())) {
					File targetFile = new File(uploadUrl, name);
					String charSet=getCharset(files.getInputStream());
					if(!"UTF-8".equals(charSet)){
						flag="1"; 
						return;
					}
					// 判断文件是否存在 存在返回true
					if (!targetFile.exists()) {
						targetFile.mkdirs(); // 创建目录
					}
					//先执行文件复制
					files.transferTo(targetFile);
					//判断文件是否为空
					  InputStream inputStream = new FileInputStream(targetFile);
					  InputStreamReader isReader = new InputStreamReader(inputStream,"utf-8");
					  BufferedReader bfReader = new BufferedReader(isReader);
					  String readLine = null;
					  int i = 0;
					  StringBuffer resBuf =null;
					  while ((readLine =bfReader.readLine())!=null) {
						  i++;
						  if(i==1){
							  continue;
						  }
						  if(i>=2){
							  if(readLine.split(",").length!=6){
								  if(resBuf == null){
									  resBuf= new StringBuffer();
								  }
								  resBuf.append(i+",");
							  }
						  }
					  }
					  if(resBuf != null){
						  flag = resBuf.toString();
						  return;
					  }
					  if(i==1){
						  out.print("6");
						  return;
					  }
					//执行插入操作之前，清空表中现有数据
			     	stagingListService.deleteAllStaging();
			     	stagingListService.batchImportData(name, uploadUrl, i);
				}

			} catch (IllegalStateException e) {
				e.printStackTrace();
				flag="error"; 
			} catch (IOException e) {
				e.printStackTrace();
				flag="error"; 
			} catch (Exception e) {
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
	
	/*class ThreadReadData implements Runnable{
        private StagingListService stagingListService;
        private ClientWhiteListService clientWhitListService;
        private String filePath;
    	private String fileName;
		
		public StagingListService getStagingListService() {
			return stagingListService;
		}
		public void setStagingListService(StagingListService stagingListService) {
			this.stagingListService = stagingListService;
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
		 
		public ClientWhiteListService getClientWhitListService() {
			return clientWhitListService;
		}
		public void setClientWhitListService(
				ClientWhiteListService clientWhitListService) {
			this.clientWhitListService = clientWhitListService;
		}
		public void run() {
			String DB_USERNAME="";
	     	String DB_PASSWORD="";
	    	String DB_URL="";
	    	String host = "";
	    	String port = "";
	    	String database = "";
	    	String  tableName="bank_speedy_list";
	    	String filedName="(NAME,PHONE,NO_MORTGAGE_CAR_STAGING,NO_MORTGAGE_DIRECT_STAGING,MORTGAGE_CAR_STAGING,MORTGAGE_DIRECT_STAGING)";//,SPEEDY_LIST \"SEQ_BK_SPEEDY_LIST.nextval\"
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
	     		executivereadline(filePath,fileName,tableName,DB_USERNAME,DB_PASSWORD,DB_URL,host,port,database,filedName,ctlPath,conn,stagingListService);
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
		
	}*/
	
	
	/**
	 * 执行文件
	 * @param filePath		 
	 * @param fileName		
	 * @param tableName		
	 */
/*	private void executivereadline(String filePath, String fileName, String tableName,String DB_USERNAME,String DB_PASSWORD,String DB_URL,String host,String port,String database,String filedName,String ctlPath,Connection conn,StagingListService listService){
		long startTime = System.currentTimeMillis();
		InputStreamReader readtxt = null;
		BufferedReader br = null;
		try {
			
			readtxt = new InputStreamReader(new FileInputStream(new File(filePath+""+fileName)),"utf-8");
			br = new BufferedReader(readtxt);
			String linehead = "insert IGNORE into "+tableName+" "+filedName+" values";
			String lineText = linehead;
			String lineStr = "";
			int count = 0;
			int readNum = 100;
			int badCount = 0;
			int goodCount =0;
			int repetitionCount = 0;
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
		        	String[] str = lineStr.split(",");
		        	int strLength = str.length;
		        	if(strLength == 6){ 
		        		StagingList stagingList = new StagingList();
		        		stagingList.setPhone(str[1]);
//		        		int listNum = stagingListService.getStangeListNum(stagingList);
//		        		if(false){
//		        			repetitiondata+=count+",";
//		        			//有重复数据
//		        			repetitionCount++;
//		        			continue;
//		        		}else{
		        			//无重复数据,组装sql语句
				        	for (int i= 0;i<str.length;i++) {
				        		if(i!=str.length-1){
				        			linecontrolstr = linecontrolstr+"'"+str[i]+"',";
				        		}else{
				        			linecontrolstr = linecontrolstr+"'"+str[i]+"'";
				        		}
							}
				        	
				        	lineText = lineText+"("+linecontrolstr+"),";
		        			
				        	
				        	if(count%readNum == 0){
				        		StringBuilder sb = new StringBuilder(lineText);
						    	sb.replace(lineText.length()-1, lineText.length(), ";");
						    	lineText = sb.toString();
//							    log.info("<mysql>"+lineText);	
							    Map<String, String> mapStr = new HashMap<String, String>();
								mapStr.put("lineText", lineText);
								String flag = "0";
								mapStr.put("flag", flag);
								stagingListService.sqlstagingList(mapStr);
			                    Statement stmt = conn.createStatement(); 
			                    stmt.addBatch(lineText); 
			                    stmt.executeBatch();    //执行批处理 
			                    stmt.close(); 
			                    lineText = linehead;
					        } 
//		        		}
			        }else{
			        	baddata+=count+",";
		        		badCount++;
		        		continue;
			        }
		        }
		     }
		    if(count%readNum != 0){
		    	StringBuilder sb = new StringBuilder(lineText);
		    	sb.replace(lineText.length()-1, lineText.length(), ";");
		    	lineText = sb.toString();
//				log.info("<mysql>"+lineText);
				Map<String, String> mapStr = new HashMap<String, String>();
				mapStr.put("lineText", lineText);
				String flag = "0";
				mapStr.put("flag", flag);
				stagingListService.sqlstagingList(mapStr);
                Statement stmt = conn.createStatement(); 
                stmt.addBatch(lineText); 
                stmt.executeBatch();    //执行批处理 
                stmt.close(); 
		    }
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
			log.error("汽车分期快捷名单导入错误："+e);
		}finally{
			try {
				if(br != null){
					br.close();
				}
				if(readtxt != null){
					readtxt.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		     
		}
	}*/
	
	
	
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
    //删除快捷名单
    @RequestMapping(value = "/delStag" )
    public void delStag(@RequestParam String speedyLists, PrintWriter out,HttpServletRequest request){
    	
    	 String strs[]=speedyLists.split(",");
    	 int i=stagingListService.deleteStagingList(strs);
    	 if(i>0){
    		 out.print("success");
  		     out.close();
    	 }else{
    		 out.print("error");
  		     out.close();
    	 }
    }

	
	 
}
