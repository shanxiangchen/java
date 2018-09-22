package com.app.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
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

import com.app.entity.BankInnerLink;
import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageSmallType;
import com.app.entity.BankMessageTemplate;
import com.app.entity.WebDavInfo;
import com.app.service.BankInnerLinkService;
import com.app.service.BankMessageTemplateService;
import com.app.service.WebDavService;
import com.app.socket.SocketMessage;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

/**
 * 消息模板 controller方法 create date 2016/4/14 <br/>
 * 
 * @author shiguangting@tansun.cn.com
 * 
 */
@SuppressWarnings({ "unused", "rawtypes" })
@Controller
@RequestMapping(value = "bankMessageTemplate")
public class BankMessageTemplateController {
	@Autowired
	private BankMessageTemplateService bankMessageTemplateService;
	@Autowired
	private WebDavService webDavService;
	@Autowired
	private BankInnerLinkService bankInnerLinkService;
	
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selBankMessageTemplate(
			BankMessageTemplate bankMessageTemplate, ModelMap modelMap,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		List<BankMessageTemplate> bankMessageTemplates = bankMessageTemplateService
				.bankMessageTemplatePageList(bankMessageTemplate);
		List<BankMessageParameterConf> list = bankMessageTemplateService.bankMessageParameterConfList();
		List listModel = bankInnerLinkService.selectBankInnerLinkPageList(new BankInnerLink());
		modelMap.put("bankMessageTemplates", bankMessageTemplates);
		request.setAttribute("bankMessageTemplate",bankMessageTemplate);
		request.setAttribute("path", path);
		request.setAttribute("list", list);
		mv.addObject("listModel", listModel);
		mv.setViewName("messageTemplate/messageTemplate");
		return mv;
	}
	
	

	@RequestMapping(value = "addBankMessageTemplate")
	public ModelAndView addBankMessageTemplate(BankMessageParameterConf bankMessageParameterConf) {
		ModelAndView mv = new ModelAndView();
		List<BankMessageParameterConf> list=bankMessageTemplateService.bankMessageParameterConfList();
		List listModel = bankInnerLinkService.selectBankInnerLinkPageList(new BankInnerLink());
		mv.addObject("list", list);
		mv.addObject("listModel", listModel);
		mv.setViewName("messageTemplate/addMessageTemplate");
		return mv;
	}
	
	

	@RequestMapping(value = "saveBankMessageTemplate")
	public ModelAndView saveBankMessageTemplate(
			BankMessageTemplate bankMessageTemplate,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dateFormat.format(new java.util.Date());

		// 读取图片绝对路径
		boolean bool = false;
		// 上传图片
		if (multipartRequest != null) {
			List advertisingImgFile = multipartRequest
					.getFiles("advertisingImgFile");
			List activityInformationImgFile = multipartRequest
					.getFiles("activityInformationImgFile");
			// 图片
			if (advertisingImgFile != null) {
				Map<String, String> davMap = webDavService.getWebDavInfo();
				for (int i = 0; i < advertisingImgFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertisingImgFile
							.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						String filename = date + "_" + name;

						// 保存
						try {

							String hostPath = request.getScheme()
									+ davMap.get("A");

							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath,
									"uploadTemplateImg", filename,
									WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, null);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme()
										+ davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath,
										"uploadTemplateImg", filename,
										WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, null);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl
									+ "uploadTemplateImg/" + filename;
							bankMessageTemplate.setAdvertisingImg(url);
							bankMessageTemplate.setAdvertisingImgName(filename);
							if(!org.apache.commons.lang.StringUtils.isBlank(bankMessageTemplate.getInGoAndSeeLink())){
								BankInnerLink bankInnerLink = new BankInnerLink();
								bankInnerLink.setLinkNo(bankMessageTemplate.getInGoAndSeeLink());
								String inLinkUrl = bankInnerLinkService.selectBankInnerLink(bankInnerLink).getLinkURL();
								bankMessageTemplate.setRemark(bankMessageTemplate.getInGoAndSeeLink());
								bankMessageTemplate.setInGoAndSeeLink(inLinkUrl);
							}
							bankMessageTemplateService
									.saveBankMessageTemplate(bankMessageTemplate);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		}
		Map<String, Object> netionResMap = SocketMessage.upTemp(bankMessageTemplate.getTypeNo(),bankMessageTemplate.getSmallTypeNo(),bankMessageTemplate.getWrittenContent(),bankMessageTemplate.getMessageTitle());
		Map<String, Object> map =SocketMessage.callNetionService(netionResMap);
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	@RequestMapping(value = "getBankMessageTemplateById")
	public ModelAndView getBankMessageTemplateById(Model model,
			@RequestParam int messageTemplateId, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		BankMessageTemplate bankMessageTemplate = bankMessageTemplateService
				.getMessageTemplateById(messageTemplateId);
		List<BankMessageParameterConf> list = bankMessageTemplateService.bankMessageParameterConfList();
		List<BankMessageSmallType> smallList = bankMessageTemplateService
				.bankMessageSmallTypeList(bankMessageTemplate.getTypeNo());
		
		List listModel = bankInnerLinkService.selectBankInnerLinkPageList(new BankInnerLink());
		model.addAttribute("bankMessageTemplate", bankMessageTemplate);
		model.addAttribute("path", path);
		model.addAttribute("list", list);
		model.addAttribute("smallList", smallList);
		model.addAttribute("listModel", listModel);
		mv.setViewName("messageTemplate/editMessageTemplate");
		return mv;

	}

	@RequestMapping(value = "editBankMessageTemplate", method = RequestMethod.POST)
	public ModelAndView editBankMessageTemplate(
			BankMessageTemplate bankMessageTemplate,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String date = dateFormat.format(new java.util.Date());

		// 读取图片绝对路径
		boolean bool = false;
		// 上传图片
		if (multipartRequest != null) {
			List advertisingImgFile = multipartRequest
					.getFiles("advertisingImgFile");
			// 图片
			if (advertisingImgFile != null) {
				Map<String, String> davMap = webDavService.getWebDavInfo();
				for (int i = 0; i < advertisingImgFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertisingImgFile
							.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						String filename = date + "_" + name;

						// 保存
						try {

							String hostPath = request.getScheme()
									+ davMap.get("A");

							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath,
									"uploadTemplateImg", filename,
									WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, null);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme()
										+ davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath,
										"uploadTemplateImg", filename,
										WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, null);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl
									+ "uploadTemplateImg/" + filename;
							bankMessageTemplate.setAdvertisingImg(url);
							bankMessageTemplate.setAdvertisingImgName(filename);
							bankMessageTemplateService
									.updateMessageTemplate(bankMessageTemplate);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					bankMessageTemplateService.updateMessageTemplate(bankMessageTemplate);
				}
			}
		}
		Map<String, Object> netionResMap = SocketMessage.upTemp(bankMessageTemplate.getTypeNo(),bankMessageTemplate.getSmallTypeNo(),bankMessageTemplate.getWrittenContent(),bankMessageTemplate.getMessageTitle());
		Map<String, Object> map =SocketMessage.callNetionService(netionResMap);
		System.out.println(map);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

	@RequestMapping(value = "delMessageTemplate")
	public void delMessageTemplate(@RequestParam int messageTemplateId,
			HttpServletRequest request, PrintWriter out) {
		boolean bool = false;
		BankMessageTemplate bankMessageTemplate = new BankMessageTemplate();
		List<String> list = new ArrayList<String>();
		list.add(bankMessageTemplate.getAdvertisingImgName());
		Map<String, String> davMap = webDavService.getWebDavInfo();
		String hostPath = request.getScheme() + davMap.get("A")
				+ "uploadTemplateImg/";
		bool = PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName,
				WebDavInfo.hostPassword, list);// 删除webDav服务器图片
		if (!bool) {
			String standPath = request.getScheme() + davMap.get("B")
					+ WebDavInfo.standbyUrl + "uploadTemplateImg/";
			bool = PutFileToWebDAV.delFile(standPath,
					WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
					list);// 删除webDav服务器图片
		}
		bankMessageTemplateService.deleteMessageTemplate(messageTemplateId);// 删除数据库信息
		out.write("success");
		out.close();
	}
}
