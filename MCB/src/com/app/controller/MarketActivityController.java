package com.app.controller;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ActivityAndCityGps;
import com.app.entity.CityGps;
import com.app.entity.MarketActivity;
import com.app.entity.MarketActivityShop;
import com.app.entity.MarketPicture;
import com.app.entity.Role;
import com.app.entity.User;
import com.app.entity.WebDavInfo;
import com.app.service.BankEditorSaveService;
import com.app.service.MarketActivityService;
import com.app.service.UserService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
/**
 * 营销活动Controller 
 * @author admin
 *
 */
@Controller
@RequestMapping(value="/marketActivity")
public class MarketActivityController  {
	Logger log=Logger.getLogger(this.getClass());
	
	@Autowired
	private BankEditorSaveService bankEditorSaveService;
	@Autowired
	private WebDavService webDavService;
	@Autowired
	private UserService userService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	/**
	 * 营销活动显示列表
	 * @param model
	 * @param page 页数
	 * @return
	 */
	@RequestMapping
	public String marActivityList(User user,Model model,MarketActivity market,Role role,HttpSession session,HttpServletRequest request, ModelMap modelMap){
		//先获取权限Id,再根据权限进行查询
		//获取ID
		
		User seUser=(User)request.getSession().getAttribute("user");
//		role.setRoleId(seUser.getRoleId());
//		List<Role> roles = roleService.allRolesPageList(role);
//		List<Integer> roleIdList = new ArrayList<Integer>();
//		for (int i = 0,b=roles.size(); i <b; i++) {
//			roleIdList.add(roles.get(i).getRoleId());
//		}
//		user.setRoleIdList(roleIdList);
//		List<User> userlist = userService.listUser(user);
		//改为查询自己及自己创建用户创建的活动
		Integer userId = seUser.getUserId();
		List<User> userlist = userService.userSel(userId);
		userlist.add(0, seUser);
		List<Integer> userIdList = new ArrayList<Integer>();
		for (int i = 0,b=userlist.size(); i < b; i++) {
			userIdList.add(userlist.get(i).getUserId());
		}
		
		market.setUserIds(userIdList);
		market.setLogo(user.getPermissionsCategory());
	    market.setUserId(String.valueOf(userId));
		List<MarketActivity> list = marketActivityService.selectMarketActivityListPage(market);
		modelMap.put("list", list);
		model.addAttribute("path", path);
		model.addAttribute("market", market);
		return "marketActivity/marketActivity";
		
	}
	
	/**
	 * 请求新增营销活动发布页面
	 * @param model
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/add")
	public String toAdd(Model model,MarketActivity mar){
		model.addAttribute("mar", mar);
		List list=marketActivityService.selectAllActivityTypeId();
		model.addAttribute("list", list);
		return "marketActivity/addMarketActivity";
	}
	/**
	 * 添加信息
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@RequestMapping(value="/save")
	public ModelAndView saveUser(Model model,MarketActivity marketActivity,DefaultMultipartHttpServletRequest multipartRequest,HttpServletRequest request,HttpServletResponse response)
			throws ServletException, IOException{
			   User user=(User)request.getSession().getAttribute("user");
		 	   response.setContentType("text/html;charset=utf-8");
		 	   String strs=marketActivity.getActivityContent();
		 	   String cityNo=request.getParameter("cityNo");
			   ModelAndView mv = new ModelAndView();
			   boolean bool=false;
			   Date date = new Date();
			   SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
			   String format2 = format.format(date);
			   int radom1 = (int) (Math.random()*10000);
			   int radom2 = (int) (Math.random()*10000);
			   String id = format2+radom1+radom2;
			   marketActivity.setActivityId(id);
			   try {  
				      
				   	   MarketPicture mar=new MarketPicture();
				   	   //String marActId=marketActivityService.selectMarketActId();
				       Map<String,String> davMap=webDavService.getWebDavInfo();
					   String hostPath = request.getScheme() +davMap.get("A");
					   String standPath = request.getScheme() +davMap.get("B"); 
					   String returnActivtityImg =WebDavInfo.hostLookUrl+"uploadMarketActivityImg/";
					   String returnShopImg =WebDavInfo.hostLookUrl+"uploadMarketShopImg/";
					   //生成随机假的访问量
					  // int x=1+(int)(Math.random()*5000);
					   Random ra =new Random();
					   int x=1+ra.nextInt(100)*5000; 
					   marketActivity.setFalseClickCount(x+"");
					   String actPicId="";
					   String shopPicId="";
					    
						  marketActivity.setActivityInContent(strs);
						  marketActivity.setUserId(String.valueOf(user.getUserId()));
						  int flag=marketActivityService.insertMarketActivity(marketActivity);
						  if(cityNo!=null && !"".equals(cityNo)){
							  String cityNos[]= cityNo.split(",");
							  List list=new ArrayList();
							  for (int i = 0; i < cityNos.length; i++) {
								   ActivityAndCityGps act=new ActivityAndCityGps();
								   act.setActivityId(marketActivity.getActivityId());
								   act.setCityNo(cityNos[i]);
								   list.add(act);
							  }
							  int result=marketActivityService.insertActivityAndCityGps(list);
						  }
					   
					   //批量上传图片 
					   if (multipartRequest != null) {
						   List actPicFile=multipartRequest.getFiles("file");
						   List shopPicFile=multipartRequest.getFiles("files");
						   //活动图片
						   if(actPicFile!=null){
							   List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
							   for (int i = 0; i < actPicFile.size(); i++) {
								    //actPicId=marketActivityService.selectMarketPictureId();
								    MultipartFile file=(MultipartFile) actPicFile.get(i);
							    	//文件名是否为空
							    	if (StringUtils.hasText(file.getOriginalFilename())) {
							    		  SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
											String time = fmt.format(new Date());
											int radom = (int) (Math.random()*100000);
											String name = "marketActivity_"+radom+time;
							    		  Map<String,Object> newMap=new HashMap<String,Object>();
							    		  newMap.put("name", name);
							    		  newMap.put("file", file);
							    		  list.add(newMap);
								          try {  
									        	  
									        	 //取图片服务路径保存在数据服务器
									        	 String url=returnActivtityImg+name;
									        	 if(i==0){
										        		 mar.setPictureSize("a");//小图
									        	 }else{
									        		   	 mar.setPictureSize("b");//大图
									        	 }
								        		 mar.setActPictureUrl(url);
								        		 mar.setActictureName(name);
								        		 mar.setPictureFlag(1);
								        		 mar.setActivityId(marketActivity.getActivityId());
								        		 int radom3 = (int) (Math.random()*10000);
									  			 int radom4 = (int) (Math.random()*10000);
									  			 actPicId = format2+radom3+radom4;
								        		 mar.setPictureId(actPicId);
								   			   marketActivityService.insertMarketPicture(mar);
								          } catch (Exception e) {  
								    	    	 e.printStackTrace();  
								    	  } 	 
							    	}
						       }
							   
							   
								 
								//主机上传
							   bool=PutFileToWebDAV.putFiles(hostPath, "uploadMarketActivityImg",list,
									   WebDavInfo.hostUserName, WebDavInfo.hostPassword,null);
							   //备机上传
							   if(!bool){
								   bool=PutFileToWebDAV.putFiles(standPath, "uploadMarketActivityImg",list,
										   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,null);
							   }
									 
						   }		   
						   //商户图片
						   if(shopPicFile!=null){
							  for (int i = 0; i < shopPicFile.size(); i++) {
								  		//shopPicId=marketActivityService.selectMarketPictureId();
									    MultipartFile file=(MultipartFile) shopPicFile.get(i);
								    	//文件名是否为空
								    	if (StringUtils.hasText(file.getOriginalFilename())) {
								    		  //String name=UUID.randomUUID().toString().replace("-", "")+"_"+file.getOriginalFilename();
								    		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
											String time = fmt.format(new Date());
											int radom = (int) (Math.random()*100000);
											String name = "marketActivity_"+radom+time;
									          try {  
													  //主机上传
													  bool=PutFileToWebDAV.putFile(hostPath, "uploadMarketShopImg",name,
															  WebDavInfo.hostUserName, WebDavInfo.hostPassword,
																file,null);
													  //备机上传
													   if(!bool){
														   bool=PutFileToWebDAV.putFile(standPath, "uploadMarketShopImg",name,
																   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,file,null);
													   }
									        	     //取图片服务路径保存在数据服务器
										        	 String url=returnShopImg+name;
									        		 mar.setActPictureUrl(url);
									        		 mar.setActictureName(name);
									        		 mar.setPictureFlag(2);
									        		 mar.setPictureSize(null);
									        		 int radom3 = (int) (Math.random()*10000);
										  			 int radom4 = (int) (Math.random()*10000);
										  			 actPicId = format2+radom3+radom4;
									        		 mar.setPictureId(actPicId);
									        		 //mar.setPictureId(shopPicId);
									        		 mar.setActivityId(marketActivity.getActivityId());
									        		 marketActivityService.insertMarketPicture(mar);
									        		 shopPicId=mar.getPictureId();
									          } catch (Exception e) {  
									    	    	 e.printStackTrace();  
									    	  } 	 
								    	}
							   }
						  }
						  String lookUrl="";
						  if(!"".equals(strs)&&strs!=null){
							  String uploadPath = request.getSession().getServletContext()
										.getRealPath("kindEditor");
							  String uploadFile = uploadPath+File.separator;
							  File file = new File(uploadFile);
							  if(!file.exists()){
								  file.mkdir();
							  }
							  lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"marketActivity",marketActivity.getActivityId());
						  }
						  marketActivity.setActivityContent(lookUrl);
						  marketActivity.setActivityShopPictureId(shopPicId);
						  marketActivity.setActivityNo(marketActivity.getActivityId());
						  marketActivityService.updateMarketActivityById(marketActivity);
						  mv.setViewName("save_result");
				   }
		  } catch (Exception e) { 
					   e.printStackTrace(); 
		  } 
		  return mv; 
	} 
	
	/**
	 * 写入图片
	 * @param stream
	 * @param path
	 * @param filename
	 * @throws IOException
	 */
	@SuppressWarnings("unused")
	public void saveFileFromInputStream(InputStream stream,String path,String filename) throws IOException   {         
	    FileOutputStream fs=new FileOutputStream( path + "/"+ filename);   
	    byte[] buffer =new byte[1024*1024];   
	    int bytesum = 0;   
	    int byteread = 0;   
	    if(fs!=null&&stream!=null){
	    	while ((byteread=stream.read(buffer))!=-1){   
		        bytesum+=byteread;   
	        	fs.write(buffer,0,byteread);   
		        fs.flush();  
		        
		    }  
	    }
	    if(fs!=null){
	    	 fs.close(); 
	    }
	    if(stream!=null){
	    	 stream.close(); 
	    }
	}	
	
	/**
	 * 请求编辑用户页面
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/update")
	public ModelAndView toEdit(@RequestParam String activityId,@RequestParam String cityNo,HttpServletRequest request,HttpServletResponse response,String updateFlag){
		ModelAndView mv = new ModelAndView();
		 
		  
		request.getSession().setAttribute("updateFlag", updateFlag);
		 
		List<MarketActivity> li=(List<MarketActivity>)marketActivityService.selectMarketActivityById(activityId);
		MarketPicture pic=null;
		for (int i = 0; i < li.size(); i++) {
			MarketActivity mar=(MarketActivity) li.get(i);
		    pic=mar.getMarketPictures();
		}
		mv.addObject("li", li);
		mv.addObject("citysName",li.get(0).getCitysName());
		mv.addObject("cityNo", cityNo);
		List list=marketActivityService.selectAllActivityTypeId();
		mv.addObject("list", list);
		mv.setViewName("marketActivity/updateMarketActivity");
		mv.addObject("pic", pic);
		mv.addObject("path",path);
		return mv;
	}
	
	/**
	 * 详情页面
	 * @param userId
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/infodate")
	public ModelAndView infodate(@RequestParam String activityId,String cityNo,HttpServletRequest request,HttpServletResponse response,String updateFlag){
		ModelAndView mv = new ModelAndView();
		List<MarketActivity> li=(List<MarketActivity>)marketActivityService.selectMarketActivityById(activityId);
		MarketPicture pic=null;
		for (int i = 0; i < li.size(); i++) {
			MarketActivity mar=(MarketActivity) li.get(i);
		    pic=mar.getMarketPictures();
		}
		mv.addObject("li", li);
		mv.addObject("citysName",li.get(0).getCitysName());
		mv.addObject("cityNo", cityNo);
		List list=marketActivityService.selectAllActivityTypeId();
		mv.addObject("list", list);
		mv.setViewName("marketActivity/infoMarketActivity");
		mv.addObject("pic", pic);
		mv.addObject("path",path);
		return mv;
	}
	
	/**
	 * 保存修改信息
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({ "unused", "unchecked", "rawtypes" })
	@RequestMapping(value="/updateOk",method=RequestMethod.POST)
	public ModelAndView updateOk(MarketActivity marketActivity,HttpServletRequest request,DefaultMultipartHttpServletRequest multipartRequest,HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");  
		String strs=marketActivity.getActivityContent();
		ModelAndView mv = new ModelAndView();  
		List<String> picNameList=null;
		boolean bool=false;
		String hostPath = "";
		String standPath = ""; 	  
 	    try {
			String cityNo=request.getParameter("cityNo");
			String activityId=marketActivity.getActivityId();
			String shopPicId="";
   
		   
			//批量上传图片 
			if(multipartRequest != null){
			   List actPicFile=multipartRequest.getFiles("actPicFile");
			   List actBigPicFile=multipartRequest.getFiles("actBigPicFile");
			   List shopPicFile=multipartRequest.getFiles("shopPicFile");
			   if(actPicFile!=null||actBigPicFile!=null||shopPicFile!=null){
				   Map<String,String> davMap=webDavService.getWebDavInfo();
				   hostPath = request.getScheme() +davMap.get("A");
				   standPath = request.getScheme() +davMap.get("B");
			   }
			   
			   String returnActivtityImg =WebDavInfo.hostLookUrl+"uploadMarketActivityImg/";
			   String returnShopImg =WebDavInfo.hostLookUrl+"uploadMarketShopImg/";
			   //活动小图片
			   if(actPicFile!=null){
				   for (int i = 0; i < actPicFile.size(); i++) {
				    	MultipartFile file=(MultipartFile) actPicFile.get(i);
				    	//文件名是否为空
				    	if (StringUtils.hasText(file.getOriginalFilename())) {
				    		  String actPicId=marketActivity.getAcPicId();
				    		 // String name=UUID.randomUUID().toString().replace("-", "")+"_"+file.getOriginalFilename();
				    		  SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
								String time = fmt.format(new Date());
								int radom = (int) (Math.random()*100000);
								String name = "maketActivity_"+radom+time;
				    		  picNameList=this.getHistoryPicName(null,marketActivity.getAcPicId());
				    		  
						     //保存  
					         try {  
					        	  
								 // 主机上传
								 bool=PutFileToWebDAV.putFile(hostPath, "uploadMarketActivityImg",name,
										 WebDavInfo.hostUserName, WebDavInfo.hostPassword,
											file,picNameList);
								 //备机上传
								 if(!bool){
									 bool=PutFileToWebDAV.putFile(standPath,"uploadMarketActivityImg",name,
											 WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
												file,picNameList);
								 }
					        	 //取图片服务路径
					        	 String url=returnActivtityImg+name;
					        	 MarketPicture mar=new MarketPicture();
					        	 mar.setActPictureUrl(url);
				        		 mar.setActictureName(name);
				        		  
				        		 mar.setActivityId(activityId);
					        	 mar.setPictureSize("a");//小图
				        		 marketActivityService.updateMarketPictureById(mar);
				    	     } catch (Exception e) {  
				    	    	 e.printStackTrace();  
				    	     } 
				    	}
				   } 
			   }
			   
			   //活动大图片
			   if(actBigPicFile!=null){
				   for (int i = 0; i < actBigPicFile.size(); i++) {
				    	MultipartFile file=(MultipartFile) actBigPicFile.get(i);
				    	//文件名是否为空
				    	if (StringUtils.hasText(file.getOriginalFilename())) {
				    		  String actBigPicId=marketActivity.getAcBigPicId();
				    		  //String name=UUID.randomUUID().toString().replace("-", "")+"_"+file.getOriginalFilename();
				    		  SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
								String time = fmt.format(new Date());
								int radom = (int) (Math.random()*100000);
								String name = "maketActivity_"+radom+time;
				    		  picNameList=this.getHistoryPicName(null,marketActivity.getAcBigPicId());
				    		  
						      //保存  
					          try {  
								  //主机上传
								  bool=PutFileToWebDAV.putFile(hostPath, "uploadMarketActivityImg",name,
										  WebDavInfo.hostUserName, WebDavInfo.hostPassword,
											file,picNameList);
								  //备机上传
								  if(!bool){
									  bool=PutFileToWebDAV.putFile(standPath,"uploadMarketActivityImg", name,
											  WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
												file,picNameList);
								  }
					        	 //取图片服务路径
					        	 String url=returnActivtityImg+name;
					        	 MarketPicture mar=new MarketPicture();
					        	 mar.setActPictureUrl(url);
				        		 mar.setActictureName(name);
				        		 mar.setActivityId(activityId);
					        	 mar.setPictureSize("b");//大图
				        		 marketActivityService.updateMarketPictureById(mar);
				    	     } catch (Exception e) {  
				    	    	 e.printStackTrace();  
				    	     } 
				    	}
				   } 
			   }
			   //商户图片 
			   if(shopPicFile!=null){
				   for (int i = 0; i < shopPicFile.size(); i++) {
				    	MultipartFile file=(MultipartFile) shopPicFile.get(i);
				    	//文件名是否为空
				    	if (StringUtils.hasText(file.getOriginalFilename())) {
				    		  String shopPicIds="";
				    		  String name="";
				    		  String[] actShopPicId=null;
				    		  shopPicId=marketActivity.getShopPicId();
				    		  if(shopPicId==null||"".equals(shopPicId)){
				    			   //shopPicIds= marketActivityService.selectMarketPictureId();
				    			   //name=UUID.randomUUID().toString().replace("-", "")+"_"+file.getOriginalFilename();
				    			   SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
									String time = fmt.format(new Date());
									int radom = (int) (Math.random()*100000);
									name = "maketActivity_"+radom+time;
				    		  }else{
				    			   //name=UUID.randomUUID().toString().replace("-", "")+"_"+file.getOriginalFilename();
				    			   SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMddhhmmss");
									String time = fmt.format(new Date());
									int radom = (int) (Math.random()*100000);
									name = "maketActivity_"+radom+time;
				    		  }
				    		  if(marketActivity.getShopPicId()!=null&&!"".equals(marketActivity.getShopPicId())){
				            		 
				            	   picNameList=this.getHistoryPicName(null,marketActivity.getShopPicId());
				               }
				    		  
						      //保存  
					          try {  
								  //主机上传
								  bool=PutFileToWebDAV.putFile(hostPath, "uploadMarketShopImg",name,
										  WebDavInfo.hostUserName, WebDavInfo.hostPassword,
											file,picNameList);
								  //备机上传
								  if(!bool){
									  bool=PutFileToWebDAV.putFile(standPath, "uploadMarketShopImg",name,
											  WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
												file,picNameList);
								  }
				        	     //取图片服务路径保存在数据服务器
					        	 String url=returnShopImg+name;
					        	 MarketPicture mars=new MarketPicture();
				        		 //发布活动有添加商户图片时 修改图片时根据已存在的图片id更新
				        		 if(shopPicId!=null && !"".equals(shopPicId)){
				        			 mars.setShopPictureName(name);
					        		 mars.setShopPictureUrl(url);
				        			 mars.setPictureId(shopPicId);
				        			 marketActivityService.updateMarketPictureByIds(mars);
				        		 }else{
				        			 mars.setActictureName(name);
				        			 mars.setActPictureUrl(url);
				        			 mars.setPictureFlag(2);
					        		 //mars.setPictureId(shopPicIds);
					        		 mars.setActivityId(activityId);
					        		 int k=marketActivityService.insertMarketPicture(mars);
					        		 marketActivity.setActivityShopPictureId(shopPicIds);
				        		 }
				    	     } catch (Exception e) {  
				    	    	 e.printStackTrace();  
				    	     } 
				    	}
				   } 
			   }
			} 
			String lookUrl="";
			if(!"".equals(strs)&&strs!=null){
				String uploadPath = request.getSession().getServletContext()
						.getRealPath("kindEditor");
			    lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"marketActivity",marketActivity.getActivityId());
			}
			marketActivity.setActivityContent(lookUrl);
			marketActivity.setActivityInContent(strs);
			marketActivityService.updateMarketActivityById(marketActivity);
			marketActivityService.deleteActivityAndCityGps(activityId);
			if(cityNo!=null && !"".equals(cityNo)){
				    String cityNos[]= cityNo.split(",");
					List list=new ArrayList();
				    for (int i = 0; i < cityNos.length; i++) {
					   ActivityAndCityGps act=new ActivityAndCityGps();
					   act.setActivityId(activityId);
					   act.setCityNo(cityNos[i]);
					   list.add(act);
				     }
				    
					int result=marketActivityService.insertActivityAndCityGps(list);
			}
			mv.setViewName("save_result");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		return mv;
	}
	
	/**
	 * 删除信息
	 * @param marketActivity
	 * @return
	 */
	@SuppressWarnings({"rawtypes", "unchecked" })
	@RequestMapping(value="/delete")
	public void deletes(Model model,@RequestParam String str,PrintWriter out,HttpServletRequest request){
		boolean bool=false;
		String strs[]=str.split(",");
		StringBuilder actIdes = new StringBuilder("");
 		List list=new ArrayList();
		for (int i = 0; i < strs.length; i++) {
			list.add(strs[i]);
		 }
	    List lists=marketActivityService.selectActInfoByActId(list);
		for (int i = 0; i < strs.length; i++) {
			for (int j = 0; j < lists.size(); j++) {
				 MarketActivity mar=(MarketActivity) lists.get(j);
				 List listShopId= mar.getMarActivityShop();
				 String shopId=null;
				 for (int k = 0; k < listShopId.size(); k++) {
					MarketActivityShop shop=(MarketActivityShop) listShopId.get(k);
					if(shop!=null){
						if(shop.getShopId()!=null){
							 shopId=shop.getShopId();
						}
					}
					
				 }
				 
				 String actId=mar.getActivityId();
				 
				 if(strs[i].equals(actId)){
					 //活动已开始
					/*if(actStatus.equals("1")){
						actIds=actIds+actId+",";
					}*/
					//活动与商户存在关联
					if(shopId!=null){
						//actIdes=actIdes+strs[i]+",";
						actIdes.append(strs[i]+",");
					}
				 }
			}
		}
		
		if("".equals(actIdes.toString())){
			//往页面写入数据类型用print 字符串用write
			List<String> picNameList=null;
			Map<String,String> davMap=webDavService.getWebDavInfo(); 
			picNameList=this.getHistoryPicName(strs,null);
			List<String> activiPicList=new ArrayList<String>();
			List<String> shopPicList=new ArrayList<String>();
			for(int i=0;i<picNameList.size();i++){
				String picName=picNameList.get(i).split(",")[0];
				String flg=picNameList.get(i).split(",")[1];
				if("1".equals(flg)){         //1.营销活动图 2.营销商户图
					activiPicList.add(picName);
				}else if("2".equals(flg)){
					shopPicList.add(picName);
				}
			}
			
			String htmlPath = request.getScheme() + davMap.get("A")+"marketActivity/";
			bool=PutFileToWebDAV.delFile(htmlPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, list);//删除富文本HTML
			//备机删除
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"marketActivity/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, list); 
			}
			
			String hostPath = request.getScheme() + davMap.get("A")+"uploadMarketActivityImg/";
			bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, activiPicList);//删除活动大小图片
			//备机删除
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"uploadMarketActivityImg/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, activiPicList);//删除活动大小图片
			}
			String shopHostPath = request.getScheme() +davMap.get("A")+"uploadMarketShopImg/";
			bool=PutFileToWebDAV.delFile(shopHostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, shopPicList);//删除商户webDav图片
			if(!bool){
				String shopStandPath = request.getScheme() +davMap.get("B")+"uploadMarketShopImg/";
			    bool=PutFileToWebDAV.delFile(shopStandPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, shopPicList);//删除商户webDav图片
			}
			int i=marketActivityService.deleteMarketActivityById(strs);
			marketActivityService.deleteBankMarketPictureById(strs);
			if(i>0){
				out.write(actIdes.toString());
				out.close();
			}
			
		}else{
			out.write(actIdes.toString());
			out.close();
		}
	}
	
	/**
	 * 对数据进行审核
	 * @param shop
	 * @return
	 */
	@RequestMapping(value="/checkDada",method=RequestMethod.POST)
	public void checkDada(@RequestParam String str,PrintWriter out,HttpSession session,MarketActivity mar,HttpServletRequest req){
		User user=(User) session.getAttribute("user");
		String actIds[]=str.split(",");
		int i=0;
		String flag=req.getParameter("flag");
		//判断用户是否有权限进行审核 为超级系统管理员则可以进行审核
		if(user.getRoleId()!=null){
			String roleId=user.getPermissionsCategory().toString();
			if("1".equals(roleId) || "2".equals(roleId)){
				if(flag!=null && !"".equals(flag)){
					if("1".equals(flag) || "2".equals(flag)){
						i=marketActivityService.updateMarketActivityDataByIds(actIds);
					}else{
						i=marketActivityService.updateReturnDataByIds(actIds);
					}
				}
			}else{
				 i=marketActivityService.updateMarketActivityDataById(actIds);
			}
		}
		out.print(i);
		out.close();
	}
	
	/**
	 * 查询是否已存在活动展示顺序
	 * @param 
	 * @return
	 */
	@RequestMapping(value="/getNums",method=RequestMethod.POST)
	public void getNum(@RequestParam String num,PrintWriter out){
		int number=0;
		if(num!=null && !"".equals(num)){
			number=Integer.parseInt(num);
		}
		int k=marketActivityService.selectShowOrderCount(number);
		out.print(k);
		out.close();
	}
	
	/**
	 * 获取城市列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/getCitys")
	public ModelAndView selectAllCity(HttpServletRequest request){
		String cityNo=request.getParameter("cityNo");
		List listCityNo=null;
		if(cityNo!=null&&!"".equals(cityNo)){
			listCityNo=new ArrayList();
			String strs[]=cityNo.split(",");
			for (int i = 0; i < strs.length; i++) {
				listCityNo.add(strs[i]);
			}
		}
		ModelAndView mv = new ModelAndView();
		List listCity=marketActivityService.selectAllCity();
		String str[]=new String[listCity.size()];
		for (int i = 0; i < listCity.size(); i++) {
				CityGps ci=(CityGps)listCity.get(i);
				str[i]=ci.getCityNo();
		}
		mv.addObject("str", str);
		mv.addObject("listCityNo", listCityNo);
		mv.setViewName("cityList");
		mv.addObject("listCity", listCity);
		return mv;
	}
	
	//获取图片名称 
	public List<String> getHistoryPicName(String strs[],String pictureId){

		List<String> picNameList=new ArrayList<String>();
		@SuppressWarnings("rawtypes")
		List nameList=null;
		
		if(pictureId!=null&&!"".equals(pictureId)){
			String picName=marketActivityService.selectPicNameByid(pictureId);
			picNameList.add(picName);
		}else if(strs.length!=0){
			nameList=marketActivityService.selectActPicByPicId(strs);
		}else{
			return null;
		}
		if(nameList!=null){
			for(int i=0;i<nameList.size();i++){
				 MarketPicture pic=(MarketPicture) nameList.get(i);
				 String picName=pic.getActictureName();
				 Integer picFlg=pic.getPictureFlag();
				 String pics=picName+","+picFlg; 
				 picNameList.add(pics);
			}
		}
		 
		return picNameList;
	}
	
	 
	
	
	@Autowired
	private MarketActivityService marketActivityService;
	
	public MarketActivityService getMarketActivityService() {
		return marketActivityService;
	}
	public void setMarketActivityService(MarketActivityService marketActivityService) {
		this.marketActivityService = marketActivityService;
	}
	
	
	
	 
	 
}
