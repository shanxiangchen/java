package com.app.service.impl;


 
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.BankEditorSave;
import com.app.entity.WebDavInfo;
import com.app.mapper.BankEditorSaveMapper;
import com.app.service.BankEditorSaveService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;

 
public class BankEditorSaveServiceImpl implements BankEditorSaveService{
	
	private BankEditorSaveMapper  bankEditorSaveMapper;
	@Autowired
	private WebDavService webDavService;
	 
	public BankEditorSaveMapper getBankEditorSaveMapper() {
		return bankEditorSaveMapper;
	}
 
	public void setBankEditorSaveMapper(BankEditorSaveMapper bankEditorSaveMapper) {
		this.bankEditorSaveMapper = bankEditorSaveMapper;
	}



	@Override
	public List<BankEditorSave> selectBankEditorList() {
		 
		return bankEditorSaveMapper.selectBankEditorList();
	}

	@Override
	public int saveEditor(BankEditorSave bankEditorSave) {
		 
		int count=bankEditorSaveMapper.saveEditor(bankEditorSave);
		return count;
	}

	@Override
	public BankEditorSave selectBankEditorInfoById(String editorNum) {
		 
		return bankEditorSaveMapper.selectBankEditorInfoById(editorNum);
	}

	@Override
	public int updateEditor(BankEditorSave bankEditorSave) {
		 
		int count=bankEditorSaveMapper.updateEditor(bankEditorSave);
		return count;
	}
 
	/**
	 * 富文本内容生成HTML页面并上传到WebDav
	 * @author zhao.lei
	 * @param strs 富文本内容
	 * @param path 富文本存储内管服务器路径
	 * @param fileName webDav存储文件夹名称
	 * @param name 文件名
	 * @date 2016-4-8 上午11:22:14
	 */
	public String printHtml(String strs,String path,String fileName,String name){
		String startStr="<!DOCTYPE html>\n"+
						"<html>\n"+
						"<head lang=\"en\">\n"+
						"<meta charset=\"UTF-8\">\n"+
						"<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge,Chrome=1\" />\n"+
						"<meta name=\"description\" content=\"\">\n"+
						"<meta name=\"keywords\" content=\"\">\n"+
						"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no\">\n"+
						"<style type=\"text/css\">\n" +
						"html{width:97%;height:auto;margin:0px auto 0px;}\n"+
						"body{width:97%;height:auto;margin:0px auto 0px;}\n"+ 
                        "div{word-wrap:break-word;word-break:break-all;}\n"+
						"</style>\n"+
						"<script type='text/javascript'>\n"+
		                "window.onload=function(){\n"+
			            "if(location.href.indexOf('?xyz=')<0){\n"+
				        "location.href=location.href+'?xyz='+Math.random();\n"+ 
			            "} }\n"+
		                "</script>\n"+
						"</head>\n"+
						"<body>\n"+
						"<div style=\"padding:0px auto;\">\n";
		String endStr="\n</div>\n</body>\n</html>";
		int imgIndex=strs.indexOf("<img");
		int kindIndex=strs.indexOf("/kindEditor/");
		if(imgIndex!=-1&&kindIndex!=-1){
			String strOne=strs.substring(imgIndex, kindIndex);
			int srcIndex=strOne.indexOf("src=\"");
			String strTwo=strOne.substring(srcIndex);
		    String strThree=strs.replaceAll(strTwo, "src=\"..");
		    strs=startStr+strThree+endStr;
		}else{
			strs=startStr+strs+endStr;
		}
		 
		String lookUrl="";
		OutputStreamWriter out=null;
		BufferedWriter bw=null;
		webDavService.getWebDavInfo(); 
		String url=path+File.separator+name+".html";
        if("".equals(name)||name==null){
        	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    		name = UUID.randomUUID().toString().substring(0,8)+dateFormat.format(new java.util.Date());
        }
		try {
			if(!"".equals(strs)&&strs!=null){
				File file = new File(url);
				  if(!file.exists()){
					  file.createNewFile(); 
				  }
				out = new OutputStreamWriter(new FileOutputStream(url),"UTF-8");
			    bw=new BufferedWriter(out);
			    bw.write(strs);
			   
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(bw!=null){
					bw.flush();
					bw.close();
				}
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		 File file=new File(url);
		 String hostPath ="http://"
					+ WebDavInfo.hostName + ":" + WebDavInfo.hostPort+WebDavInfo.hostUrl;
		  
		 //主机上传
		 boolean bool=PutFileToWebDAV.putFileTwo(hostPath, fileName,name,
				  WebDavInfo.hostUserName, WebDavInfo.hostPassword,
					file,null);
		 if(!bool){
			  String standPath ="http://"
						+ WebDavInfo.standbyName + ":" + WebDavInfo.standbyPort+WebDavInfo.standbyUrl;
			  bool=PutFileToWebDAV.putFileTwo(standPath, fileName,name,
					  WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
						file,null);
		 }
		 if(file.exists()){
			  file.delete();
		 }
		 if(bool){
			  lookUrl=WebDavInfo.hostLookUrl+fileName+"/"+name;
		 }
		 
		 
		return lookUrl;
	}
	
	 
	 
	 
}
