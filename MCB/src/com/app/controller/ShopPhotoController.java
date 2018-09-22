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
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.ShopPhoto;
import com.app.entity.WebDavInfo;
import com.app.service.ShopPhotoService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping(value = "shopphto")
public class ShopPhotoController {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ShopPhotoService shopPhotoService;
	@Autowired
	private WebDavService webDavService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	/**
	 * 显示列表
	 * 
	 * @param model
	 * @param page
	 * @return
	 */
	@RequestMapping
	public String list(ShopPhoto shopphoto,HttpServletRequest request) {
		List<ShopPhoto> shopphotoList = shopPhotoService
				.findAllShopPhoto(shopphoto);
		ModelAndView mv = new ModelAndView();
		mv.addObject("shopphotoList", shopphotoList);
		mv.addObject("shopphoto", shopphoto);
		request.setAttribute("path", path);
		return "shopPhoto/shopPhoto_list";
	}

	/**
	 * 跳转添加页面
	 * 
	 * @param oddsshopId
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/addShopPhoto")
	public ModelAndView addShopPhoto(@RequestParam String oddsshopId,
			Model model) {
		ModelAndView mv = new ModelAndView();
		model.addAttribute("oddsshopId", oddsshopId);
		mv.setViewName("shopPhoto/shopPhoto_add");
		return mv;
	}

	/***
	 * 查询多张图片
	 * 
	 * @param photoalbumid
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/editShopPhoto")
	public ModelAndView editShopPhoto(@RequestParam int photoalbumid,
			Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		ShopPhoto shopPhoto = shopPhotoService.photoalbumidByid(photoalbumid);
		model.addAttribute("shopPhoto", shopPhoto);
		request.setAttribute("path", path);
		mv.setViewName("shopPhoto/shopPhoto_edit");
		return mv;
	}

	/***
	 * 实现修改图片
	 * 
	 * @param shopphoto
	 * @param multipartRequest
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/updateShopPhoto", method = RequestMethod.POST)
	public ModelAndView updateShopPhoto(ShopPhoto shopphoto,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		String name = null;
		String url = null;
		List<String> picNameList=null;
		boolean bool=false;
		String photoalbumid = request.getParameter("photoalbumid");
		// 上传图片
		if (multipartRequest != null) {
			List advertPic = multipartRequest.getFiles("oddsshopFile");
			 
			if (advertPic != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo(); 
				String returnAdvertImg = WebDavInfo.hostLookUrl+"uploadShopImg/";
				for (int i = 0; i < advertPic.size(); i++) {
					MultipartFile file = (MultipartFile) advertPic.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						name = file.getOriginalFilename();
 
						// 保存
						try {
							picNameList=this.getShopPicName(photoalbumid);
							 
							String hostPath = request.getScheme() + davMap.get("A");
  
							//主机上传
							bool=PutFileToWebDAV.putFile(hostPath, "uploadShopImg",name,
									WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file, picNameList);
							if(!bool){
								String standPath = request.getScheme() + davMap.get("B");
								bool=PutFileToWebDAV.putFile(standPath, "uploadShopImg",name,
										WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file, picNameList);

							}
							// 取图片服务路径保存在数据服务器
							url = returnAdvertImg + PutFileToWebDAV.getName();
							shopphoto.setPhotourl(url);
							shopphoto.setPhotoname(PutFileToWebDAV.getName());
							shopPhotoService.saverShopPhoto(shopphoto);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		mv.setViewName("save_result");
		return mv;
	}
    /**
     * 新增
     */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/insertShopPhoto", method = RequestMethod.POST)
	public ModelAndView insertShopPhoto(ShopPhoto shopPhoto,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		// 读取图片绝对路径
		String shopid = request.getParameter("oddsshopId");
		String urls = null;
		boolean bool=false;
  
		// 上传图片
		if (multipartRequest != null) {
			List advertPic = multipartRequest.getFiles("oddsshopFile");

			// 其他图片
			if (advertPic != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo(); 
				String returnAdvertImg = WebDavInfo.hostLookUrl+"uploadShopImg/";
				for (int i = 0; i < advertPic.size(); i++) {
					MultipartFile file = (MultipartFile) advertPic.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String names = file.getOriginalFilename();
					  
						// 保存
						try {
							String hostPath = request.getScheme() + davMap.get("A");
 
							//主机上传
							bool=PutFileToWebDAV.putFile(hostPath,"uploadShopImg", names,
									WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file, null);
							//备机上传
							if(!bool){
								String standPath = request.getScheme() + davMap.get("B");
										 
								bool=PutFileToWebDAV.putFile(standPath, "uploadShopImg",names,
										WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file, null);
							}
							// 取图片服务路径保存在数据服务器
							urls = returnAdvertImg + PutFileToWebDAV.getName();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					 
					shopPhoto.setPhotoflag(2);
					shopPhoto.setPhotourl(urls);
					shopPhoto.setPhotoname(PutFileToWebDAV.getName());
					shopPhoto.setOddsshopid(shopid);
					shopPhotoService.insertShopPhoto(shopPhoto);
					mv.setViewName("save_result");
				}
			}
		}
		mv.setViewName("save_result");
		return mv;
	}

	/***
	 * 实现按Id删除单条信息
	 */
	@RequestMapping(value = "/deleteShopPhoto")
	public void delShopPhoto(@RequestParam String photoalbumid,
			PrintWriter printWriter, HttpServletRequest request) {
		 
		List<String> picNameList=null;
		boolean bool=false;
		Map<String,String> davMap=webDavService.getWebDavInfo();  
		String hostPath = request.getScheme() +davMap.get("A")+"uploadShopImg/";
		 
		picNameList=this.getShopPicName(photoalbumid);
	 
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"uploadShopImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
		}
		shopPhotoService.deleteShopPhoto(photoalbumid);//删除数据库信息
		printWriter.write("success");
		printWriter.close();
	}
	
	//获取删除图片名
	public List<String> getShopPicName(String oddsshopid){
			List<String> nameList=new ArrayList<String>();
			if(oddsshopid!=null){
				int pbid = Integer.parseInt(oddsshopid);
				ShopPhoto listShopPhoto = shopPhotoService.photoalbumidByid(pbid);
				String shopName=listShopPhoto.getPhotoname();
				nameList.add(shopName);
				 
			}
			return nameList;
	}
	 
}
