package com.app.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpURL;
import org.apache.log4j.Logger;
import org.apache.webdav.lib.WebdavResource;
import org.springframework.web.multipart.MultipartFile;

import com.app.entity.WebDav;
import com.app.listener.Const;
import com.app.mapper.WebDavMapper;
/**
 * webDAV上传
 * @author zhaolei 
 * @date 2016-3-3 下午6:55:54
 */
public class PutFileToWebDAV {
	private static Logger log = Logger.getLogger(PutFileToWebDAV.class);
	private static String name="";
	public static String getName() {
		return name;
	}


	public static void setName(String name) {
		PutFileToWebDAV.name = name;
	}


	//webDav上传
	public static boolean putFile(String urlPath, String urlFileName, String fileName,String uname,
			String pwd, MultipartFile file,List<String> list) {

		boolean bool = false;
		WebdavResource wdr = null;
		String delPath=null;
		InputStream is=null;
		try {
			HttpURL hrl = new HttpURL(urlPath);
			hrl.setUserinfo(uname, pwd);//建立webDav服务器连接
			wdr = new WebdavResource(hrl);//创建webDav客户端
			is=file.getInputStream(); 
			String path = wdr.getPath();
			if (!path.endsWith("/"))
				path += "/";
			if(urlFileName!=null&&!"".equals(urlFileName)){
				if(!urlFileName.endsWith("/")){
					urlFileName+="/";
				}
				wdr.mkcolMethod(path+urlFileName);
				path=path+urlFileName; 
			} 
			//图片压缩上传
			SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
			String time = fmt.format(new Date());
			String url=RuntimeProperites.getCIBPROPMAP("url")+urlFileName;
			name = "advert_"+time+".jpg";
			String	names=url+name;
			Thumbnails.of(file.getInputStream()).scale(1f).outputQuality(0.5f).toFile(names); 
			if (!wdr.isCollection()) {
				log.error("webDavService Connect Failed!");
			} else {
				log.info("webDavService Connect Success!");
				// 尝试锁定
				if (wdr.isLocked()) {
					wdr.unlockMethod();
					bool = wdr.putMethod(names, is);
                    if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("Delete File Success:"+delPath);
    						}else{
    							log.info("Delete File Failed:"+delPath);
    						}
    						
                    	}
                    	
					}  
				} else {
					bool = wdr.putMethod(names, is);
					if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除图片路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("Delete File Success:"+delPath);
    						}else{
    							log.info("Delete File Failed:"+delPath);
    						}
                    	}
                    	
					}  
				}
				if(bool){
					log.info("Upload File Success,UploadFilePath is:"+urlPath+urlFileName+fileName);
				}else{
					log.info("Upload File Failed,UploadFilePath is:"+urlPath+urlFileName+fileName);
				}
				wdr.unlockMethod();

		    }

		} catch (MalformedURLException mue) {
			log.error("MalformedURLException:" + mue.getMessage());
		} catch (HttpException he) {
			log.error("HttpException: webDavService Connect Failed!"+urlPath);
		} catch (IOException ioe) {
			log.error("IOException:" + ioe.getMessage());
			log.error(urlPath+":webDavService Connect Failed!");
		} catch (Exception ex) {
			log.error("ThrowException:" + ex.getMessage());
		} finally {
			try {
				if(is!=null){
					is.close();
				}
				if (wdr != null) {
					wdr.close();
				}
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bool;

	}
	
	
	//批量上传修改
	public static boolean putFiles(String urlPath, String urlFileName,List<Map<String,Object>> putFiles, String uname,
			String pwd, List<String> delFileNames) {
        
		boolean bool = false;
		WebdavResource wdr = null;
		String putPath=null;//上传路径
		String delPath=null;//删除路径
		InputStream is=null;
		try {
			HttpURL hrl = new HttpURL(urlPath);
			hrl.setUserinfo(uname, pwd);//建立webDav服务器连接
			wdr = new WebdavResource(hrl);//创建webDav客户端
			 
			String path = wdr.getPath();
			if (!path.endsWith("/"))
				path += File.separator;
			
			if(urlFileName!=null&&!"".equals(urlFileName)){
				if(!urlFileName.endsWith("/")){
					urlFileName+="/";
				}
				wdr.mkcolMethod(path+urlFileName);
				path=path+urlFileName;
			}
			 
			if (!wdr.isCollection()) {
				log.error("webDavService Connect Failed");
			} else {
				log.info("webDavService Connect Success");
				// 尝试锁定
				if (wdr.isLocked()) {
					wdr.unlockMethod();
				    if(putFiles!=null&&putFiles.size()!=0){
				    	for(int k=0;k<putFiles.size();k++){
							Map<String,Object> map=putFiles.get(k);
							String putName=map.get("name").toString();
							MultipartFile file=(MultipartFile)map.get("file");
							is=file.getInputStream(); 
							putPath=path+name;//上传路径
							bool = wdr.putMethod(putPath, is);
							if(bool){
								log.info("Upload File Success,UploadFilePath is:"+urlPath+putName);
							}else{
								log.info("Upload File Failed,UploadFilePath is:"+urlPath+putName);
							}
							
							if(is!=null){
	        					is.close();
	        				}
	                     
						}
				    	
				    }
					 
                    if(delFileNames!=null&&delFileNames.size()!=0){
                    	for(int i=0;i<delFileNames.size();i++){
                    		String delName=delFileNames.get(i);
                    		delPath=path+delName;//删除路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("Delete File Success,DeleteFilePath is :"+urlPath+delName);
    						}else{
    							log.info("Delete File Failed,DeleteFilePath is:"+urlPath+delName);
    						}
    						
                    	}
                    	
					}  
				} else {
					if(putFiles!=null&&putFiles.size()!=0){
				    	for(int k=0;k<putFiles.size();k++){
							Map<String,Object> map=putFiles.get(k);
							String putName=map.get("name").toString();
							MultipartFile file=(MultipartFile)map.get("file");
							is=file.getInputStream(); 
							putPath=path+putName;//上传路径
							bool = wdr.putMethod(putPath, is);
							if(bool){
								log.info("Upload File Success,UploadFilePath is:"+urlPath+putName);
							}else{
								log.info("Upload File Failed,UploadFilePath is:"+urlPath+putName);
							}
							
							if(is!=null){
	        					is.close();
	        				}
	                     
						}
					    	
					}
					
                    if(delFileNames!=null&&delFileNames.size()!=0){
                    	for(int i=0;i<delFileNames.size();i++){
                    		String delName=delFileNames.get(i);
                    		delPath=path+delName;//删除路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("Delete File Success,DeleteFilePath is :"+urlPath+delName);
    						}else{
    							log.info("Delete File Failed,DeleteFilePath is:"+urlPath+delName);
    						}
    						
                    	}
                    	
					}  

				    wdr.unlockMethod();
				}
 		    }

		} catch (MalformedURLException mue) {
			log.error("MalformedURLException:" + mue.getMessage());
		} catch (HttpException he) {
			log.error("HttpException:" + he.getMessage());
		} catch (IOException ioe) {
			log.error("IOException:" + ioe.getMessage());
			log.error(urlPath+":webDavService Connect Failed!");
		} catch (Exception ex) {
			log.error("ThrowException:" + ex.getMessage());
		} finally {
			try {
				if(is!=null){
					is.close();
				}
				if (wdr != null) {
					wdr.close();
				}
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bool;

	}
	/**
	 * webDav上传 文件类型为File
	 * @author zhaolei
	 * @date 2016-3-18 下午4:31:18
	 */
	public static boolean putFileTwo(String urlPath, String urlFileName, String fileName,String uname,
			String pwd, File file,List<String> list) {

		boolean bool = false;
		WebdavResource wdr = null;
		String delPath=null;
		String putPath=null;
		FileInputStream is=null;
		try {
			HttpURL hrl = new HttpURL(urlPath);
			hrl.setUserinfo(uname, pwd);//建立webDav服务器连接
			wdr = new WebdavResource(hrl);//创建webDav客户端
			is=new FileInputStream(file); 
			String path = wdr.getPath();
			if (!path.endsWith("/"))
				path += "/";
			if(urlFileName!=null&&!"".equals(urlFileName)){
				if(!urlFileName.endsWith("/")){
					urlFileName+="/";
				}
				wdr.mkcolMethod(path+urlFileName);
				path=path+urlFileName; 
			} 
			putPath=path+fileName;//上传路径
			if (!wdr.isCollection()) {
				log.error("webDav服务器连接失败");
			} else {
				log.info("webDav服务器连接成功");
				// 尝试锁定
				if (wdr.isLocked()) {
					wdr.unlockMethod();
					bool = wdr.putMethod(putPath, is);
                    if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("删除webDav服务器文件成功:"+delPath);
    						}else{
    							log.info("删除webDav服务器文件失败:"+delPath);
    						}
    						
                    	}
                    	
					}  
                    
				} else {
					bool = wdr.putMethod(putPath, is);
					if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除图片路径
    						boolean flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("删除webDav服务器文件成功:"+delPath);
    						}else{
    							log.info("删除webDav服务器文件失败:"+delPath);
    						}
                    	}
                    	
					}
					
					
				}
				if(bool){
					log.info("上传成功，UploadFilePath is:"+urlPath+urlFileName+fileName);
				}else{
					log.info("上传失败，UploadFilePath is:"+urlPath+urlFileName+fileName);
				}
				wdr.unlockMethod();

		    }

		} catch (MalformedURLException mue) {
			log.error("MalformedURLException:" + mue.getMessage());
		} catch (HttpException he) {
			log.error("HttpException:" + he.getMessage());
		} catch (IOException ioe) {
			log.error("IOException:" + ioe.getMessage());
			log.error(urlPath+":webDavService Connect Failed!");
		} catch (Exception ex) {
			log.error("ThrowException:" + ex.getMessage());
		} finally {
			try {
				if(is!=null){
					is.close();
				}
				if (wdr != null) {
					wdr.close();
				}
				 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bool;

	}
		 
	                    	
	 
	/**
	 * 删除服务器上的图片
	 * @author zhaolei
	 * @date 2016-3-10 下午6:17:52
	 */
	public static boolean delFile(String urlPath, String uname,String pwd, List<String> list) {

		WebdavResource wdr = null;
		String delPath=null;
		boolean flg=false;
		try {
			HttpURL hrl = new HttpURL(urlPath);
			hrl.setUserinfo(uname, pwd);//建立webDav服务器连接
			wdr = new WebdavResource(hrl);//创建webDav客户端
			  
			String path = wdr.getPath();
			if (!path.endsWith("/"))
				path += File.separator;
			if (!wdr.isCollection()) {
				log.error("webDav服务器连接失败");
			} else {
				log.info("webDav服务器连接成功");
				// 尝试锁定
				if (wdr.isLocked()) {
					wdr.unlockMethod();
                    if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除路径
                    		flg=wdr.deleteMethod(delPath);
    						if(flg){
    							log.info("删除webDav服务器图片成功:"+delPath);
    						}else{
    							log.info("删除webDav服务器图片失败:"+delPath);
    						}
                    	}
                    	
					}  
				} else {
					if(list!=null&&list.size()!=0){
                    	for(int i=0;i<list.size();i++){
                    		delPath=path+list.get(i);//删除图片路径
                    		flg=wdr.deleteMethod(delPath);
                    		if(flg){
    							log.info("删除webDav服务器图片成功:"+delPath);
    						}else{
    							log.info("删除webDav服务器图片失败:"+delPath);
    						}
                    	}
                    	
					}  
					
					 
				}
				wdr.unlockMethod();

		    }

		} catch (MalformedURLException mue) {
			log.error("MalformedURLException:" + mue.getMessage());
		} catch (HttpException he) {
			log.error("HttpException:" + he.getMessage());
		} catch (IOException ioe) {
			log.error("IOException:" + ioe.getMessage());
			log.error(urlPath+":webDavService Connect Failed!");
		} catch (Exception ex) {
			log.error("ThrowException:" + ex.getMessage());
		} finally {
			try {
				if (wdr != null) {
					wdr.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return flg;

	}
	/**
	 * 获取webDav服务器连接信息
	 * @author zhaolei
	 * @date 2016-3-10 下午6:55:49
	 */
	public static Map<String,Object> getWebDavInfo(){
		/*ClassPathXmlApplicationContext ctx=new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
		WebDavMapper webDavMapper=(WebDavMapper) ctx.getBean("webDavMapper");*/
		WebDavMapper webDavMapper=(WebDavMapper)Const.WEB_APP_CONTEXT.getBean("webDavMapper");
		List<WebDav> webDavList = webDavMapper.selectWebDavList();
		if(webDavList==null||webDavList.size()==0){
			return null;
		}
		Map<String,Object> webDavMap=new HashMap<String,Object>();
		// 主机连接信息
		String hostName = null;
		int hostPort = 0;
		String hostUserName = null;
		String hostPassword = null;
		String hostUrl = null;
		String hostLookUrl=null;
		// 备机连接信息
		String standbyName = null;
		int standbyPort = 0;
		String standbyUserName = null;
		String standbyPassword = null;
		String standbyUrl = null;
		String standbyLookUrl=null;
        
		for (WebDav webDav : webDavList) {
			if ("1".equals(webDav.getWebDavNum())) {
				hostName = webDav.getWebDavHostName();
				hostPort = webDav.getWebDavPort();
				hostUserName = webDav.getWebDavUserName();
				hostPassword = DESUtil.getDesString(webDav.getWebDavPassword());
				hostUrl = webDav.getWebDavUrl();
				hostLookUrl=webDav.getWebDavLookUrl();
			} else if ("2".equals(webDav.getWebDavNum())) {
				standbyName = webDav.getWebDavHostName();
				standbyPort = webDav.getWebDavPort();
				standbyUserName = webDav.getWebDavUserName();
				standbyPassword = DESUtil.getDesString(webDav.getWebDavPassword());
				standbyUrl = webDav.getWebDavUrl();
				standbyLookUrl=webDav.getWebDavLookUrl();
			}
		}
		if(hostUrl!=null){
			if(!hostUrl.endsWith("/"))
				hostUrl+="/";
			 
	        if(!hostUrl.startsWith("/"))
	        	hostUrl="/"+hostUrl;
		}
		
		if(hostLookUrl!=null){
			if(!hostLookUrl.endsWith("/"))
	        	hostLookUrl+="/";
	        
	        if(!hostLookUrl.startsWith("/"))
	        	hostLookUrl="/"+hostLookUrl;
		}
		
		if(standbyUrl!=null){
			if(!standbyUrl.endsWith("/"))
	        	standbyUrl+="/";
	        
	        if(!standbyUrl.startsWith("/"))
	        	standbyUrl="/"+standbyUrl;
		}
        
		if(standbyLookUrl!=null){
			if(!standbyLookUrl.endsWith("/"))
	        	standbyLookUrl+="/";
	        if(!standbyLookUrl.startsWith("/"))
	        	standbyLookUrl="/"+standbyLookUrl;
		}
         
		
		webDavMap.put("hostName", hostName);
		webDavMap.put("hostPort", hostPort);
		webDavMap.put("hostUserName", hostUserName);
		webDavMap.put("hostPassword", hostPassword);
		webDavMap.put("hostUrl", hostUrl);
		webDavMap.put("hostLookUrl", hostLookUrl);
		webDavMap.put("standbyName", standbyName);
		webDavMap.put("standbyPort", standbyPort);
		webDavMap.put("standbyUserName", standbyUserName);
		webDavMap.put("standbyPassword", standbyPassword);
		webDavMap.put("standbyUrl", standbyUrl);
		webDavMap.put("standbyLookUrl", standbyLookUrl);

		return webDavMap;
	}
}
