package com.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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

import com.app.entity.BankCarBrand;
import com.app.entity.BankCarBrandDetails;
import com.app.entity.WebDavInfo;
import com.app.service.BankCarBrandDetailsService;
import com.app.service.BankCarBrandService;
import com.app.service.BankEditorSaveService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping("/carBrand")
public class BankCarBrandController {
	@Autowired
	private BankCarBrandService brandService;
	@Autowired
	private BankCarBrandDetailsService detailsService;
	@Autowired 
	private WebDavService webDavService;
	@Autowired
	private BankEditorSaveService bankEditorSaveService;
	
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView listBeauideal(BankCarBrand brand, HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<BankCarBrand> bankCarBrands = brandService.carBrandPageList(brand);
		modelMap.put("bankCarBrands", bankCarBrands);
		request.setAttribute("path", path);
		request.setAttribute("brand", brand);
		mv.setViewName("StagingList/carBrand");
		return mv;
	}
	/**
	 * 删除
	 * 
	 * @param brandId
	 * @param out
	 */
	@RequestMapping(value = "/delect")
	public void delectCarBrand(@RequestParam String brandId,@RequestParam String imgName,PrintWriter out ,HttpServletRequest request) {
		boolean bool=false;
		List<String> list=new ArrayList<String>();
		list.add(imgName);
		List<String> lists=new ArrayList<String>();
		lists.add(brandId);
		Map<String,String> davMap=webDavService.getWebDavInfo();
		String hostPath = request.getScheme() + davMap.get("A")+"carBrandImg/";
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, list);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"carBrandImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, list);//删除webDav服务器图片
		}
		String htmlPath = request.getScheme() +davMap.get("A")+"carBrand/";
		bool=PutFileToWebDAV.delFile(htmlPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, lists);//删除富文本HTML
		//备机删除
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"carBrand/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, lists); 
		}
		brandService.deleteCarBrand(brandId);
		List<String> idList=brandService.selectDetailsIdById(brandId);
	 
		for(int i=0;i<idList.size();i++){//关联删除品牌详情
			List<String> picNameList=new ArrayList<String>();
			boolean bools=false;
			String hostPaths = request.getScheme() +davMap.get("A")+"CarBrandDetailsImg/";
			List<BankCarBrandDetails>infos=detailsService.listCarBrandDetailsByIds(idList.get(i));
			for(int j=0;j<infos.size();j++){
				BankCarBrandDetails brand= infos.get(j);
				String brandName=brand.getDetailsImgName();
				picNameList.add(brandName);
			}
			bools=PutFileToWebDAV.delFile(hostPaths, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
			if(!bools){
				String standPaths = request.getScheme() +davMap.get("B")+"CarBrandDetailsImg/";
				bools=PutFileToWebDAV.delFile(standPaths, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
			} 
			detailsService.deleteCarBrandDetails(idList.get(i));
			 
		}
		out.write("success");
		out.close();
	}


	//获取图片名
	public List<String> getCarBrand(String brandId){
		List<String> nameList=new ArrayList<String>();
		if(!"".equals(brandId)&&brandId!=null){
			List<BankCarBrand>infos=brandService.listCarBrandByIds(brandId);
			for(int i=0;i<infos.size();i++){
				BankCarBrand brand= infos.get(i);
				String brandName=brand.getBrandName();
				nameList.add(brandName);
			}
		}
		return nameList;
	}
	
	
	
	
	
	/**
	 * 编辑
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carBrandUpdate")
	public ModelAndView carBrandUpdate(String brandId, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 查询单条数据
		BankCarBrand bankCarBrand = brandService.getCarBrandbyid(brandId);

		// 查询过后要返回对象
		model.addAttribute("bankCarBrand", bankCarBrand);
		request.setAttribute("path", path);
		mv.setViewName("StagingList/carBrandUpdate");
		return mv;
	}

	/**
	 * 新增页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carBrandAdd")
	public ModelAndView carBrandAdd(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("StagingList/carBrandAdd");
		return mv;
	}

	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/saveCarBrand", method = RequestMethod.POST)
	public ModelAndView save(BankCarBrand carBrand, DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request,
			ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		
		int random1 = (int) (Math.random()*100000);
		int random2 = (int) (Math.random()*100000);
		int random3 = (int) (Math.random()*100000);
		String carBrandId = ""+random1+random2+random3;
		carBrand.setBrandId(carBrandId);

		String strs=carBrand.getBrandDetailsPc();
       
		boolean bool = false;
		// 上传图片
		if (multipartRequest != null) {
			List advertPicFile = multipartRequest.getFiles("imgAddressUrlFile");
			// 广告图片
			if (advertPicFile != null) {
				 Map<String,String> davMap=webDavService.getWebDavInfo();
				 
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String filename = file.getOriginalFilename();
						// 保存
						try {
							
							String hostPath = request.getScheme() + davMap.get("A");
							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath, "carBrandImg", filename, WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, null);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme() + davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath, "carBrandImg", filename, WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, null);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl + "carBrandImg/" + PutFileToWebDAV.getName();
							carBrand.setImgAddressUrl(url);
							carBrand.setImgName(PutFileToWebDAV.getName());
		
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		String	rul="/weba/carBrand/"+carBrandId;
		carBrand.setBrandDetailsMb(rul);
		brandService.insertCarBrand(carBrand);
		/*List<BankCarBrand> bankCarBrands = brandService.carBrandPageList(carBrand);
		carBrand = bankCarBrands.get(bankCarBrands.size()-1);*/
		  if(!"".equals(strs)&&strs!=null){
			  String uploadPath = request.getSession().getServletContext()
						.getRealPath("kindEditor");
			    bankEditorSaveService.printHtml(strs,uploadPath,"carBrand",carBrandId);
		  }
		
		  mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 修改保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatesave", method = RequestMethod.POST)
	public ModelAndView updateOk(BankCarBrand carBrand, DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
		 
		ModelAndView mv = new ModelAndView();
		String strs=carBrand.getBrandDetailsPc();
		boolean bool = false;
		
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List advertPicFile = multipartRequest.getFiles("imgAddressUrlFile");
			// 广告图片
			if (advertPicFile != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				String hostPath = request.getScheme() + davMap.get("A");
				
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String fileName = file.getOriginalFilename();
						List<String> picNameList = new ArrayList<String>();
						picNameList.add(carBrand.getImgName());
						// 保存
						try {
							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath, "carBrandImg", fileName, WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, picNameList);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme() + davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath, "carBrandImg", fileName, WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, picNameList);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl + "carBrandImg/" + PutFileToWebDAV.getName();
							carBrand.setImgAddressUrl(url);
							carBrand.setImgName(PutFileToWebDAV.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		    if(!"".equals(strs)&&strs!=null){
			   String uploadPath = request.getSession().getServletContext()
						.getRealPath("kindEditor");
			   bankEditorSaveService.printHtml(strs,uploadPath,"carBrand",carBrand.getBrandId());
		    }
			carBrand.setBrandDetailsPc(strs);
//			carBrand.setBrandDetailsMb(lookUrl);
			brandService.updateCarBrand(carBrand); 
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	@RequestMapping(value="/brandParticulars")
	public ModelAndView brandParticulars(Model model,String brandId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		BankCarBrand bankCarBrand = brandService.getCarBrandbyid(brandId);
		request.setAttribute("bankCarBrand", bankCarBrand);
		request.setAttribute("path", path);
		mv.setViewName("StagingList/brandParticulars");
		return mv;
	}

}