package com.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.app.entity.Advert;
import com.app.entity.BankInnerLink;
import com.app.entity.ModuleFunctionName;
import com.app.entity.WebDavInfo;
import com.app.service.AdvertService;
import com.app.service.BankInnerLinkService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
 

/**
 * 广告业务逻辑Controller
 * 
 * @author admin
 * 
 */

@Controller
@RequestMapping(value = "/advert")
public class AdvertController {
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private AdvertService advertService;
	@Autowired 
	private WebDavService webDavService;
	@Autowired
	private BankInnerLinkService bankInnerLinkService;
	
	/**
	 * 广告显示列表
	 * @param model
	 * @param moduleFunctionName
	 * @param request
	 * @param modelMap
	 * @return advert
	 */
	@RequestMapping
	public String marketShopList(Model model,
			ModuleFunctionName moduleFunctionName, HttpServletRequest request,
			ModelMap modelMap) {
		List<ModuleFunctionName> list = advertService.selectAdvertListPage(moduleFunctionName);
		modelMap.put("list", list);
		model.addAttribute("path", path);
		model.addAttribute("moduleFunctionName", moduleFunctionName);
		return "advert/advert";
	}

	/**
	 * 请求新增广告页面
	 * 
	 * @param model
	 * @return addAdvert
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/add")
	public String toAdd(Model model) {
		List list = advertService.selectAllModuleFunctionName();
		List listname = advertService.selectAllLinkName();
		model.addAttribute("list", list);
		model.addAttribute("listname", listname);
		return "advert/addAdvert";
	}

	/**
	 * 添加信息
	 * 
	 * @param Advert
	 *            ad
	 * @param DefaultMultipartHttpServletRequest
	 *            multipartReques6t
	 * @param HttpServletRequest
	 *            request
	 * @return ModelAndView mv
	 */

	@RequestMapping(value = "/saveAdvert", method = RequestMethod.POST)
	public ModelAndView saveUser(Advert ad,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if("3".equals(ad.getModuleFunctionNo()+"")){//乐活广告首页
			ad.setAdvertSeat("1");
		}
		
		int random1 = (int) (Math.random()*10000000);
		int random2 = (int) (Math.random()*10000000);
		int random3 = (int) (Math.random()*10000000);
		String adverId = ""+random1+random2+random3;
		ad.setAdvertId(adverId);
		boolean bool=false;
		Map<String,String> davMap=webDavService.getWebDavInfo();
		String hostPath = request.getScheme() + davMap.get("A");
		String standPath = request.getScheme() + davMap.get("B");
		try {
			 
			// 上传图片
			if (multipartRequest != null) {
				List<MultipartFile> advertPicFile = multipartRequest
						.getFiles("advertPicUrlFile");
				// 广告图片
				if (advertPicFile != null) {
					for (int i = 0; i < advertPicFile.size(); i++) {
						MultipartFile file = (MultipartFile) advertPicFile
								.get(i);

						// 文件名是否为空
						if (StringUtils.hasText(file.getOriginalFilename())) {
							String name = file.getOriginalFilename();
							// 上传到主机
						   bool=PutFileToWebDAV.putFile(hostPath, "uploadAdvertImg",name,
								   WebDavInfo.hostUserName, WebDavInfo.hostPassword,file,null);
						   //上传到备机
						   if(!bool){
							   bool=PutFileToWebDAV.putFile(standPath, "uploadAdvertImg",name,
									   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,file,null);
						   }
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl+"uploadAdvertImg/" + PutFileToWebDAV.getName();
							log.info("<upload picture of returnAdvertImg>"
									+ url);
							 
							ad.setAdvertPicUrl(url);
							ad.setAdvertPicture(PutFileToWebDAV.getName());
							String advertURL = "";
							String advertParam="";
							if("1".equals(ad.getLinkType())){
								//内链
								String linkNo = ad.getLinkNo();
								BankInnerLink bankInnerLink = bankInnerLinkService.getBankInnerLinkByNo(linkNo);
								String linkURL = bankInnerLink.getLinkURL();
								advertURL = linkURL;
								ad.setAdvertUrl(advertURL);
								if(ad.getAdvertParam().trim()!=""){
									advertParam = ad.getAdvertParam().replace(",", "&");
									ad.setAdvertParam(advertParam);
								}
							}else{
								//外链
								if(ad.getAdvertParam().trim()!=""){
									advertParam = ad.getAdvertParam().replace(",", "&");
									ad.setAdvertParam(advertParam);
								}
							}
							int flag = advertService.insertAdvert(ad);
							log.info("<insert count>" + flag);

						}
					}
				}else{
					String advertURL = "";
					String advertParam="";
					if("1".equals(ad.getLinkType())){
						//内链
						String linkNo = ad.getLinkNo();
						BankInnerLink bankInnerLink = bankInnerLinkService.getBankInnerLinkByNo(linkNo);
						String linkURL = bankInnerLink.getLinkURL();
						advertURL = linkURL;
						ad.setAdvertUrl(advertURL);
						if(ad.getAdvertParam().trim()!=""){
							advertParam = ad.getAdvertParam().replace(",", "&");
							ad.setAdvertParam(advertParam);
						}
					}else{
						//外链
						if(ad.getAdvertParam().trim()!=""){
							advertParam = ad.getAdvertParam().replace(",", "&");
							ad.setAdvertParam(advertParam);
						}
					}
					int flag = advertService.insertAdvert(ad);
					log.info("<insert count>" + flag);
				}
			}
			
		} catch (IllegalStateException e) {
			e.printStackTrace();
			log.error("<Exception>:" + e);
		}finally{
			mv.setViewName("save_result");
		}

		return mv;
	}

	/**
	 * 请求编辑广告页面
	 * 
	 * @param String
	 *            advertId
	 * @return ModelAndView mv
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/editAdvert")
	public ModelAndView toEdit(@RequestParam String advertId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List list = advertService.selectAdvertById(advertId);
		List moduleFunctionNameList = advertService.selectAllModuleFunctionName();
		List listname = advertService.selectAllLinkName();
		mv.addObject("moduleFunctionNameList", moduleFunctionNameList);
		mv.addObject("listname", listname);
		mv.addObject("list", list);
		mv.addObject("path", path);
		mv.setViewName("advert/updateAdvert");
		return mv;
	}

	/**
	 * 保存修改信息
	 * 
	 * @param Advert
	 *            ad
	 * @param DefaultMultipartHttpServletRequest
	 *            multipartRequest
	 * @param HttpServletRequest
	 *            request
	 * @return ModelAndView mv
	 */
	@RequestMapping(value = "/updateOkAdvert", method = RequestMethod.POST)
	public ModelAndView updateOk(Advert ad,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if("3".equals(ad.getModuleFunctionNo()+"")){//乐活广告首页
			ad.setAdvertSeat("1");
		}
		
		try {
			boolean bool = false;
			List<String> picNameList=null;
             
			
		 
			// 上传图片
			if (multipartRequest != null) {
				@SuppressWarnings("rawtypes")
				List advertPicFile = multipartRequest.getFiles("advertPicUrlFile");
				// 广告图片
				if (advertPicFile != null) {
					Map<String,String> davMap=webDavService.getWebDavInfo();
					String hostPath = request.getScheme() + davMap.get("A");
					for (int i = 0; i < advertPicFile.size(); i++) {
						MultipartFile file = (MultipartFile) advertPicFile
								.get(i);
						 
						// 文件名是否为空
						if (StringUtils.hasText(file.getOriginalFilename())) {
							String name = file.getOriginalFilename();
							String[] advertId = {ad.getAdvertId()};
							picNameList=this.getHistoryPicName(advertId);
							 
							try {
								// 上传到主机
								bool = PutFileToWebDAV.putFile(hostPath, "uploadAdvertImg",name,
										WebDavInfo.hostUserName, WebDavInfo.hostPassword,
										file,picNameList);
								//上传到备机
							   if(!bool){
								   String standPath = request.getScheme() + davMap.get("B");
								   bool=PutFileToWebDAV.putFile(standPath, "uploadAdvertImg",name,
										   WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,file,picNameList);
							   }

								// 取图片服务路径保存在数据服务器
								String url = WebDavInfo.hostLookUrl+"uploadAdvertImg/" + PutFileToWebDAV.getName();
								log.info("<upload picture of returnAdvertImg>"+ url);
								ad.setAdvertPicUrl(url);
								ad.setAdvertPicture(PutFileToWebDAV.getName());
								String advertURL = "";
								String advertParam="";
								if("1".equals(ad.getLinkType())){
									//内链
									String linkNo = ad.getLinkNo();
									BankInnerLink bankInnerLink = bankInnerLinkService.getBankInnerLinkByNo(linkNo);
									String linkURL = bankInnerLink.getLinkURL();
									advertURL = linkURL;
									ad.setAdvertUrl(advertURL);
									if(ad.getAdvertParam().trim()!=""){
										advertParam = ad.getAdvertParam().replace(",", "&");
										ad.setAdvertParam(advertParam);
									}
								}else{
									//外链
									if(ad.getAdvertParam().trim()!=""){
										advertParam = ad.getAdvertParam().replace(",", "&");
										ad.setAdvertParam(advertParam);
									}
								}
								int j = advertService.updateAdvertById(ad);
								log.info("<update count>" + j);
								mv.setViewName("save_result");
								return mv;
							} catch (Exception e) {
								e.printStackTrace();
								log.info("<Exception>", e);
							}
						}else{
							String advertURL = "";
							String advertParam="";
							if("1".equals(ad.getLinkType())){
								//内链
								String linkNo = ad.getLinkNo();
								BankInnerLink bankInnerLink = bankInnerLinkService.getBankInnerLinkByNo(linkNo);
								String linkURL = bankInnerLink.getLinkURL();
								advertURL = linkURL;
								ad.setAdvertUrl(advertURL);
								if(ad.getAdvertParam().trim()!=""){
									advertParam = ad.getAdvertParam().replace(",", "&");
									ad.setAdvertParam(advertParam);
								}
							}else{
								//外链
								if(ad.getAdvertParam().trim()!=""){
									advertParam = ad.getAdvertParam().replace(",", "&");
									ad.setAdvertParam(advertParam);
								}
							}
							int j = advertService.updateAdvertById(ad);
							log.info("<update count>" + j);
							mv.setViewName("save_result");
							return mv;
						}
					}
				}
			}
			advertService.updateAdvertByIds(ad);
			mv.setViewName("save_result");
		} finally {
		}
		return mv;
	}

	/**
	 * 删除信息
	 */
	@RequestMapping(value = "/delete")
	public void deleteAdvert(Model model, @RequestParam String str,
			PrintWriter out, HttpServletRequest request) {
		// 读取图片存储服务路径
		String strs[] = str.split(",");
		List<String> picNameList=null;
		boolean bool=false; 
		Map<String,String> davMap=webDavService.getWebDavInfo();
		String hostPath = request.getScheme() + davMap.get("A")+"uploadAdvertImg/";
		picNameList=this.getHistoryPicName(strs);
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"uploadAdvertImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);
		}
		int i = advertService.deleteAdvertById(strs);
		log.info("<delete count>" + i);
		out.print(i);
		out.close();
	}

	 
	//获取图片名
	public List<String> getHistoryPicName(String strs[]){
		List<String> nameList=new ArrayList<String>();
		if(strs.length!=0){
			@SuppressWarnings("rawtypes")
			List picNameList = advertService.selectAdvertPicNameByAdvertId(strs);
			if(picNameList.size()>0){
				for(int i=0;i<picNameList.size();i++){
					Advert ad = (Advert) picNameList.get(i);
					if(ad !=null){
						String picName = ad.getAdvertPicture();
						nameList.add(picName);
					}
				}
			} 
		}
		return nameList;
	}
}
