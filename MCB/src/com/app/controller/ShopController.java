package com.app.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

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

import com.app.entity.CityShop;
import com.app.entity.Gprs;
import com.app.entity.Shop;
import com.app.entity.ShopPhoto;
import com.app.entity.ShopPraise;
import com.app.entity.Type;
import com.app.entity.WebDavInfo;
import com.app.listener.ShopExcelView;
import com.app.service.CityShopService;
import com.app.service.GprsService;
import com.app.service.ShopPhotoService;
import com.app.service.ShopPraiseService;
import com.app.service.ShopService;
import com.app.service.TypeService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping(value = "/shop")
public class ShopController {
	Logger log = Logger.getLogger(this.getClass());
	@Autowired
	private ShopService shopService;
	@Autowired
	private TypeService typeService;
	@Autowired
    private WebDavService webDavService;
	@Autowired
	private ShopPraiseService shopPraiseService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@Autowired
	private GprsService gprsService; 
	@Autowired
	private ShopPhotoService shopPhotoService;
	@Autowired
	private CityShopService cityShopService;
	 
	/**
	 * 显示列表
	 * 
	 * @param model
	 * @param page
	 * @return
	 */

	@RequestMapping
	public String list(Shop shop,HttpServletRequest request, ModelMap modelMap) {
		 
		List<Shop> list = shopService.findAllShop(shop);
		modelMap.put("list", list);
		request.setAttribute("path", path);
		request.setAttribute("shop", shop);
		return "shops/shops";
	}	
	/**
	 * 查询所有城市
	 */
	@RequestMapping(value="/ajax_searchShops")
	public void searchShops(HttpServletResponse response){
		try {
			//调用查询分期类型的方法
			//List<BankCity> list = bankCityService.findAllCity();
			List<Gprs> list = gprsService.findAllGprs();
			//将list集合转换成json格式
			JSONArray array=JSONArray.fromObject(list);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.print(array.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@RequestMapping(value="/searchCityShop")
	public void  searchCityShop(HttpServletResponse response,HttpServletRequest request){
		try {
			String cityno=request.getParameter("cityno");
			//调用查询方法
			List<CityShop> list=cityShopService.listCityShop(cityno);
			JSONArray array=JSONArray.fromObject(list);
			//将数据响应给ajax plain 纯文本（文本格式）
			response.setContentType("text/plain;charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(array.toString());
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 请求新增页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public String toAdd(Model model, Type type, Gprs gprs, CityShop cityShop) {
		List<Type> typelist = typeService.listPagetype(type);
		List<CityShop> cityShoplist = cityShopService.findAllshopring(cityShop);
		List<Gprs> gpslist = gprsService.findAllGprs();
		model.addAttribute("cityShoplist", cityShoplist);
		model.addAttribute("typelist", typelist);
		model.addAttribute("gpslist", gpslist);
		
		return "shops/addshop_info";
	}
	/**
	 * 请求编辑页面
	 * 
	 * @param oddsshopid
	 * @param model
	 * @return
	 */
	@RequestMapping(value ="/edit")
	public String toEdit(@RequestParam String oddsshopid, Model model,HttpServletRequest request,Type type, Gprs gprs, CityShop cityShop) {
		List<Type> typelist = typeService.listPagetype(type);
		//List<BankCity> gpslist= bankCityService.findAllCity();
		List<Gprs> gpslist = gprsService.findAllGprs();
		Shop shop = shopService.getShopById(oddsshopid);
		String osid=shop.getOddsshopcity();
		List<CityShop> cityShoplist=cityShopService.listCityShop(osid);
		List<ShopPhoto> listShopPhoto=shopPhotoService.oddsShopIdByid(oddsshopid);
		 
		request.setAttribute("gpslist", gpslist);
		request.setAttribute("cityShoplist", cityShoplist);
		request.setAttribute("typelist", typelist);
		request.setAttribute("listShopPhoto", listShopPhoto);
		request.setAttribute("path", path);
		model.addAttribute("shop", shop);
		return "shops/updateshops_info";
	}
	/**
	 * 保存信息
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(Shop shop, ShopPhoto shopphoto,ShopPraise shoppraise,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		
		//获取系统当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String date=dateFormat.format(new java.util.Date());
		List<Map<String,Object>> putFiles=new ArrayList<Map<String,Object>>();
		// 读取图片绝对路径
		String shopid = null;
		String fileNames = null;
		String urls = null;
		boolean bool=false; 
		// 上传图片
		if (multipartRequest != null) {
			List advertPicFile = multipartRequest.getFiles("oddsshoppictureurlFile");
			List advertPic = multipartRequest.getFiles("oddsshopFile");
			Map<String,String> davMap=webDavService.getWebDavInfo(); 
			if (advertPicFile != null) {
				
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						String filename=UUID.randomUUID().toString().substring(0,8)+"_"+date+"_"+name;
						Map<String,Object> acvertPicMap=new HashMap<String,Object>();
						acvertPicMap.put("name", filename);
						acvertPicMap.put("file", file);
						putFiles.add(acvertPicMap); 
						// 取图片服务路径保存在数据服务器
						String url =WebDavInfo.hostLookUrl+"uploadShopImg/" + filename;
					     
						shop.setOddsshoppictureurl(url);
						shop.setOddsshoppicturename(filename);
						shop.setOddsshoppublictime(df.format(new Date()));
						if (shopService.saveshop(shop) == false) {
							mv.addObject("msg", "failed");
							mv.setViewName("save_result");
							return mv;
						} else {
							shopid=shop.getOddsshopid();
							mv.addObject("msg", "success");
						} 
						 
					}
				}
			}

			if (advertPic != null) {
				for (int i = 0; i < advertPic.size(); i++) {
					MultipartFile file = (MultipartFile) advertPic.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String names = file.getOriginalFilename();
						fileNames=UUID.randomUUID().toString().substring(0,8)+"_"+date+"_"+names;
						Map<String,Object> picMap=new HashMap<String,Object>();
						picMap.put("name", fileNames);
						picMap.put("file", file);
						putFiles.add(picMap);
						 
					    // 取图片服务路径保存在数据服务器
						urls =WebDavInfo.hostLookUrl+"uploadShopImg/" + fileNames;
						 
					}
					//String PicId = shopPhotoService.selectshopshotoid();
					shopphoto.setPhotoflag(2);
					shopphoto.setPhotourl(urls);
					shopphoto.setPhotoname(fileNames);
					shopphoto.setOddsshopid(shopid);
					//shopphoto.setPhotoalbumid(PicId);
					shopPhotoService.insertShopPhoto(shopphoto);
				    mv.setViewName("save_result");
				 
				}	
			}
			
			String hostPath = request.getScheme() + davMap.get("A");
		 
			//主机上传
		    bool=PutFileToWebDAV.putFiles(hostPath, "uploadShopImg",putFiles,
		    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,null);
		    //备机上传
		    if(!bool){
		    	String standPath = request.getScheme() + davMap.get("B");
		    	bool=PutFileToWebDAV.putFiles(standPath,"uploadShopImg",putFiles,
			    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,null);
		    }
		}
		 
		shoppraise.setPraisecount(0);
		shoppraise.setClickcount(0);
		// 点赞基数
		int maxx = 150;
		int minn = 100;
		Random randoms = new Random();
		int ss = randoms.nextInt(maxx) % (maxx - minn + 1) + minn;
		shoppraise.setPraiseradixcount(ss);
        //点击基数
		int max = 1000;
		int min = 300;
		Random random = new Random();
		int s = random.nextInt(max) % (max - min + 1) + min;
		shoppraise.setClickradixcount(s);
		
		shoppraise.setOddsshopid(shopid);
		shopPraiseService.insertShopPraise(shoppraise);	
		mv.setViewName("save_result");
		return mv;
	}	
	/**
	 * 修改保存
	 * 
	 * @param model
	 * @param shop
	 * @return
	 */
	@RequestMapping(value = "/saver", method = RequestMethod.POST)
	public ModelAndView updateOk(Shop ad, ShopPhoto shopphoto,ShopPraise shoppraise,DefaultMultipartHttpServletRequest multipartRequest,
		HttpServletRequest request) {
		//获取系统当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
		String date=dateFormat.format(new java.util.Date());
		 
		ModelAndView mv = new ModelAndView();
		//获取商店编码
		String oddsshopid=request.getParameter("oddsshopid");
		 
		List<String> picNameList=null;
		boolean bool=false;  
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List advertPicFile = multipartRequest.getFiles("oddsshoppictureurlFile");
			 
			if (advertPicFile != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo(); 
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					// 文件名是否为空StringUtils.hasText(file.getOriginalFilename())
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						String fileNmae=date+"_"+name;
						 
						// 保存
						try {
							picNameList=this.getShopPicName(oddsshopid);
							 
							String hostPath = request.getScheme() + davMap.get("A");
							 
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "uploadShopImg",fileNmae,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,picNameList);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() + davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "uploadShopImg",fileNmae,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,picNameList);
						    }
							// 取图片服务路径保存在数据服务器
							String url =WebDavInfo.hostLookUrl+"uploadShopImg/"+fileNmae;
							ad.setOddsshoppictureurl(url);
							ad.setOddsshoppicturename(fileNmae);
							shopService.saverShop(ad);
							mv.setViewName("save_result");
							return mv;
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		shopService.updateShop(ad);
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除信息
	 * @param oddsshopid
	 * @param out
	 */
	@RequestMapping(value = "/delete")
	public void deleteShop(@RequestParam String oddsshopid,PrintWriter out,HttpServletRequest request) {
		 
		List<String> picNameList=null;
		boolean bool=false;
		Map<String,String> davMap=webDavService.getWebDavInfo(); 
		String hostPath = request.getScheme() + davMap.get("A")+"uploadShopImg/";
		
		picNameList=this.getHistoryPicName(oddsshopid);
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"uploadShopImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
		}
		shopPhotoService.deleteshopshoto(oddsshopid);//删除数据库信息
		
		picNameList=this.getShopPicName(oddsshopid);
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"uploadShopImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
		}
		shopPraiseService.deleteShopPraise(oddsshopid);
		shopService.deleteShop(oddsshopid);
		out.write("success");
		out.close();
	}
	
	 
	public List<String> getHistoryPicName(String oddsshopid){
		List<String> nameList=new ArrayList<String>();
		if(oddsshopid!=null){
			List<ShopPhoto> listShopPhoto=shopPhotoService.oddsShopIdByid(oddsshopid);
			
			for(int i=0;i<listShopPhoto.size();i++){
				ShopPhoto sp=listShopPhoto.get(i);
				String photoName=sp.getPhotoname();
				nameList.add(photoName);
			}
		}
		return nameList;
	}
	
	//获取商户图片名
	public List<String> getShopPicName(String oddsshopid){
		List<String> nameList=new ArrayList<String>();
		if(oddsshopid!=null){
			List<Shop> listShop=shopService.odddsShopbyId(oddsshopid);
			
			for(int i=0;i<listShop.size();i++){
				Shop shop=listShop.get(i);
				String shopName=shop.getOddsshoppicturename();
				nameList.add(shopName);
			}
		}
		return nameList;
	}
	 
	@RequestMapping(value = "/excel")
	public ModelAndView export2Excel() {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		List<String> titles = new ArrayList<String>();
		titles.add("商户编号");
		titles.add("商户名称");
		titles.add("商户地址");
		titles.add("经度");
		titles.add("纬度");
		titles.add("联系方式");
		titles.add("市");
		titles.add("展示顺序");
		titles.add("商圈");
		titles.add("状态");
		titles.add("图片");
		titles.add("点击数");
		titles.add("活动类型");
		titles.add("人均消费");
		titles.add("分店名称");
		titles.add("活动简介");
		titles.add("截止日期");
		titles.add("特惠内容");
		dataMap.put("titles", titles);
		List<Shop> shopList = shopService.listAllshop();
		dataMap.put("shopList", shopList);
		ShopExcelView erv = new ShopExcelView();
		ModelAndView mv = new ModelAndView(erv, dataMap);
		return mv;
	}
	/**
	 * 跳转到多张图片页面
	 * @param oddsshopid
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value="listShopphoto")
	public String listShopphoto(@RequestParam String oddsshopid,ShopPhoto shopPhoto,HttpServletRequest request) {
			String oddsshopId=shopService.oddsshopId(oddsshopid);
			List<ShopPhoto>listShopPhoto=shopPhotoService.oddsShopIdByid(oddsshopid);
			 
			request.setAttribute("listShopPhoto", listShopPhoto);
			request.setAttribute("path", path);
			request.setAttribute("oddsshopId", oddsshopId);
		    return "shopPhoto/shopPhoto_list";
	}
}
