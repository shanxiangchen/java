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

import com.app.entity.BankCarBrandDetails;
import com.app.entity.WebDavInfo;
import com.app.service.BankCarBrandDetailsService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

@Controller
@RequestMapping("/BrandDetails")
public class BankCarBrandDetailsController {

	@Autowired
	private BankCarBrandDetailsService detailsService;
	@Autowired 
	private WebDavService webDavService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	
	@RequestMapping
	public ModelAndView listCarBrandDetails(BankCarBrandDetails brandDetails, HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		List<BankCarBrandDetails> bankCarBrandDetails = detailsService.bankCarBrandDetailsPageList(brandDetails);
		modelMap.put("bankCarBrandDetails", bankCarBrandDetails);
		mv.addObject("brandDetails", brandDetails);
		request.setAttribute("path", path);
		mv.setViewName("StagingList/BrandDetails");
		return mv;
	}
	
	/**
	 * 新增页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/BrandDetailsAdd")
	public ModelAndView BrandDetailsAdd(Model model) {
		List<BankCarBrandDetails> typelist=detailsService.selectCarBrandDetails();
		ModelAndView mv = new ModelAndView();
		model.addAttribute("typelist", typelist);
		mv.setViewName("StagingList/BrandDetailsAdd");
		return mv;
	}
	
	/**
	 * 保存
	 * @param brandDetails
	 * @param multipartRequest
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/saveCarBrandDetails", method = RequestMethod.POST)
	public ModelAndView save(BankCarBrandDetails brandDetails, DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request,
			ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
 
		boolean bool = false;
		
		// 上传图片
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List advertPicFile = multipartRequest.getFiles("detailsImgUrlFile");
			// 广告图片
			if (advertPicFile != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						// 保存
						try {
							String hostPath = request.getScheme() +davMap.get("A");
							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath, "CarBrandDetailsImg", name, WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, null);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme() + davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath, "CarBrandDetailsImg", name, WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, null);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl + "CarBrandDetailsImg/" + PutFileToWebDAV.getName();

							brandDetails.setDetailsImgUrl(url);
							brandDetails.setDetailsImgName(PutFileToWebDAV.getName());
							detailsService.insertCarBrandDetails(brandDetails);
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
	 * 编辑
	 * 
	 * @param
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/carBrandUpdate")
	public ModelAndView carBrandUpdate(String detailsId, Model model, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<BankCarBrandDetails> typelist=detailsService.selectCarBrandDetails();
		// 查询单条数据
		
		BankCarBrandDetails carBrandDetails = detailsService.getCarBrandDetailsbyid(detailsId);

		// 查询过后要返回对象
		model.addAttribute("typelist", typelist);
		model.addAttribute("carBrandDetails", carBrandDetails);
		request.setAttribute("path", path);
		mv.setViewName("StagingList/BrandDetailsUpdate");
		return mv;
	}
	
	/**
	 * 修改保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/carupdatesave.html", method = RequestMethod.POST)
	public ModelAndView updateOk(BankCarBrandDetails carBrand, DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
		 
		ModelAndView mv = new ModelAndView();
		
		boolean bool = false;
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List advertPicFile = multipartRequest.getFiles("detailsImgUrlFile");
			 
			if (advertPicFile != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < advertPicFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertPicFile.get(i);
					// 文件名是否为空StringUtils.hasText(file.getOriginalFilename())
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						List<String> picNameList = new ArrayList<String>();
						picNameList.add(carBrand.getDetailsImgName());
						// 保存
						try {
							String hostPath = request.getScheme() +davMap.get("A");

							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath, "CarBrandDetailsImg", name, WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, picNameList);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme() + davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath, "CarBrandDetailsImg", name, WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, picNameList);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl + "CarBrandDetailsImg/" + PutFileToWebDAV.getName();
							carBrand.setDetailsImgUrl(url);
							carBrand.setDetailsImgName(PutFileToWebDAV.getName());
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
				}
				detailsService.updateCarBrand(carBrand);
			}
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	//获取图片名
	public List<String> getCarBrand(String detailsId){
		List<String> nameList=new ArrayList<String>();
		if(!"".equals(detailsId)&&detailsId!=null){
			List<BankCarBrandDetails>infos=detailsService.listCarBrandDetailsByIds(detailsId);
			for(int i=0;i<infos.size();i++){
				BankCarBrandDetails brand= infos.get(i);
				String brandName=brand.getDetailsImgName();
				nameList.add(brandName);
			}
		}
		return nameList;
	}
		
		
	/**
	 * 删除
	 * 
	 * @param detailsId
	 * @param out
	 */
	@RequestMapping(value = "/delect")
	public void delectCarBrand(@RequestParam String detailsId, PrintWriter out ,HttpServletRequest request) {
		List<String> picNameList=null;
		boolean bool=false;
		Map<String,String> davMap=webDavService.getWebDavInfo(); 
		String hostPath = request.getScheme() +davMap.get("A")+"CarBrandDetailsImg/";
		
		picNameList=this.getCarBrand(detailsId);
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() +davMap.get("B")+"CarBrandDetailsImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
		} 
		detailsService.deleteCarBrandDetails(detailsId);
		out.write("success");
		out.close();
	}
	
}
