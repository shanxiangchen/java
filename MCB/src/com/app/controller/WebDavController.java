package com.app.controller;
 

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.WebDav;
import com.app.service.WebDavService;
import com.app.util.DESUtil;
 
 

/**
 * webDav
 * @author zhao.lei 
 * @date 2016-3-8 下午4:05:15
 */
@Controller
@RequestMapping(value = "/webDav")
public class WebDavController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired 
	private WebDavService webDavService;
	
	public WebDavService getWebDavService() {
		return webDavService;
	}
	public void setWebDavService(WebDavService webDavService) {
		this.webDavService = webDavService;
	}
	
	
	 
	@RequestMapping
	public ModelAndView selectNeswTemplet(Model model,WebDav webDav, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<WebDav> webDavList = webDavService.selectWebDavList(); 
		modelMap.put("webDavList", webDavList);
		mv.setViewName("webDav/webDav");
		return mv;
		
	}
	
	/**
	 * 新增webDav
	 * <p>Description: </p>
	 * @author zhao.lei
	 * @date 2016-3-8 下午4:37:21
	 */
	@RequestMapping(value = "/add")
	public String toAdd(Model model) {
		 
		return "webDav/addWebDav";
	}
	/**
	 * 保存webDav信息
	 * @author zhaolei
	 * @date 2016-3-12 下午3:51:58
	 */
	@RequestMapping(value = "/saveWebDav", method = RequestMethod.POST)
	public ModelAndView saveUser(WebDav webDav,HttpServletRequest request,PrintWriter out) {
		ModelAndView mv = new ModelAndView();
		if(webDav!=null){
			int count=webDavService.selectWebDavCount(webDav.getWebDavId());
			if(count>0){
				out.print("1");
				out.close();
				return null;
			}
			int countTwo=webDavService.selectWebDavNumCount(webDav.getWebDavNum());
			if(countTwo>0){
				out.print("2");
				out.close();
				return null;
			}
			String pwd = DESUtil.getEncString(webDav.getWebDavPassword());// 加密字符串,返回String的密文
			webDav.setWebDavPassword(pwd);
			webDav.setWebDavIsOk("1");//服务器默认1：正常
		}
		webDavService.insertWebDav(webDav);
		mv.setViewName("save_result");	
		return mv;
	}
	
	/**
	 * 删除webDav
	 * @author zhaolei
	 * @date 2016-3-12 下午4:37:02
	 */
	@RequestMapping(value = "/deleteWebDav" )
	public void  deleteTemplet(@RequestParam String webDavId, PrintWriter out){
		 
		   String strs[]=webDavId.split(",");
		   webDavService.deleteWebDav(strs);
		   out.print("success");
		   out.close();
	}
	
	/**
	 * 编辑webDav
	 */
	@RequestMapping(value = "/edit")
	public String edtiTemplet(@RequestParam String webDavId, Model model) {
		WebDav webDav= webDavService.selectWebDavById(webDavId);
		webDav.setWebDavPassword(DESUtil.getDesString(webDav.getWebDavPassword()));
		model.addAttribute("webDav", webDav);
		return "webDav/updateWebDav";
	}
	
	/**
	 * 保存修改webDav
	 */
	@RequestMapping(value="/updateWebDav",method=RequestMethod.POST)
	public ModelAndView updateWebDav(WebDav webDav){
		
 	    String strEnc = DESUtil.getEncString(webDav.getWebDavPassword());
 	    webDav.setWebDavPassword(strEnc);
		ModelAndView mv = new ModelAndView();
		webDavService.updateWebDav(webDav); 
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * webDav定时任务
	 * @author zhao.lei
	 * @date 2016-5-10 上午9:12:54
	 */
	public void work(){
		  log.info("==> 开始执行定时任务，更新webDav服务器状态");
		  Socket socket=null;
		  try {
			  List<WebDav> webDavList = webDavService.selectWebDavList();
			  if(webDavList!=null&&webDavList.size()>0){
				  for (WebDav webDav : webDavList) {
					  boolean flg=false;
					  String webDavId=webDav.getWebDavId();
					  String davIp=webDav.getWebDavHostName();
					  int davPort=webDav.getWebDavPort();
					 /* Map<String,String> map=new HashMap<String,String>();
					  SimpleDateFormat dm =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					  SimpleDateFormat df =new SimpleDateFormat("yyyy-MM-dd");
					  Date date=new Date();
					  String controlDate=df.format(date);
					  String controlTime=dm.format(date);
					  map.put("controlDate", controlDate);
					  map.put("controlTime", controlTime);*/
					  try {
						socket=new Socket(davIp,davPort);
						if(socket.isConnected()){
							flg=true;
							webDavService.updateWebDavIsOk(webDavId);//服务器更新状态1
							//map.put("controlIsOk", "Y");
							//webDavService.insertBankControlLog(map);
						}
					  }catch (UnknownHostException e) {
						e.printStackTrace();
						log.error("webDav定时任务执行异常");
					  }catch (IOException e) {
						log.error("webDav服务器连接失败,IP："+davIp+" 端口："+davPort);
						flg=false;
					  }finally{
						  if(socket!=null){
								try {
									socket.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
					  }
					  
					  if(!flg){
						  webDavService.updateWebDavIsNo(webDavId);//服务器更新状态2
						  //map.put("controlIsOk", "N");
						  //webDavService.insertBankControlLog(map);
						 
					  } 
					  
				  }
			  }
		} catch (BeansException e) {
			e.printStackTrace();
			log.error("webDav定时任务执行异常");
		}finally{
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		log.info("==> 定时任务执行结束");
	}

	 
}
