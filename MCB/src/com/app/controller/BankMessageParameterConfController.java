package com.app.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageType;
import com.app.entity.WebDavInfo;
import com.app.service.BankMessageParameterConfService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;


@Controller
@RequestMapping(value="/messageParameterConf")
public class BankMessageParameterConfController {
	
	@Autowired
	private BankMessageParameterConfService bankMessageParameterConfService;
	
	@Autowired
	private WebDavService webDavService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	/**
	 * 查询全部的消息盒子信息
	 * @param model
	 * @param bankMessageParameterConf
	 * @param modelMap
	 * @param request
	 * @return
	 */
	@RequestMapping
	public ModelAndView getAllMessageParameterConf(BankMessageParameterConf bankMessageParameterConf,HttpServletRequest request){
		List<BankMessageParameterConf> bankMessageParameterConfList = bankMessageParameterConfService.getBankMessageParameterConfPageList(bankMessageParameterConf);
		ModelAndView mv=new ModelAndView();
		mv.addObject("messageParameterConfList",bankMessageParameterConfList );
		mv.addObject("messageParameter",bankMessageParameterConf );
		mv.addObject("path", path);
		mv.setViewName("messageParameterConf/messageParameterConf");
		return mv;
	}
	
	/**
	 * 跳转添加盒子信息页面
	 * @return
	 */
	@RequestMapping(value="/addAllMessageParameterConf")
	public ModelAndView addAllMessageParameterConf(){
		ModelAndView mv=new ModelAndView();
		List<BankMessageType> bankMessageTypes=bankMessageParameterConfService.selBankMessageType();
		mv.addObject("bankMessageTypes", bankMessageTypes);
		mv.setViewName("messageParameterConf/addMessageParameterConf");
		return mv;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/saveAllMessageParameterConf")
	public ModelAndView saveAllMessageParameterConf(BankMessageParameterConf bankMessageParameterConf,
			DefaultMultipartHttpServletRequest multipartRequest,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		// 读取图片绝对路径
		boolean bool = false;
		// 上传图片
		if (multipartRequest != null) {
			List advertisingImgFile = multipartRequest.getFiles("bmpcUrlfile");
			// 图片
			if (advertisingImgFile != null) {
				Map<String, String> davMap = webDavService.getWebDavInfo();
				for (int i = 0; i < advertisingImgFile.size(); i++) {
					MultipartFile file = (MultipartFile) advertisingImgFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						// 保存
						try {

							String hostPath = request.getScheme()
									+ davMap.get("A");

							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath,
									"messageParameterConfImg", name,
									WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, null);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme()
										+ davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath,
										"messageParameterConfImg", name,
										WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, null);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl+ "messageParameterConfImg/" + PutFileToWebDAV.getName();
							bankMessageParameterConf.setBmpcUrl(url);
							bankMessageParameterConfService.insertBankMessageParameterConf(bankMessageParameterConf);
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
	
	@RequestMapping(value="/updateMessageParameterConfpage")
	public ModelAndView updateMessageParameterConfpage(@RequestParam String bmpcId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		BankMessageParameterConf bankMessageParameterConf=bankMessageParameterConfService.selMessageParameterConfById(bmpcId);
		List<BankMessageType> bankMessageTypes=bankMessageParameterConfService.selBankMessageType();
		mv.addObject("bankMessageTypes", bankMessageTypes);
		mv.addObject("path", path);
		mv.addObject("bankMessageParameterConf", bankMessageParameterConf);
		mv.setViewName("messageParameterConf/editMessageParameterConf");
		return mv;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="/mssageParameterConfpEidt")
	public ModelAndView MessageParameterConfpEidt(BankMessageParameterConf bankMessageParameterConf,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		System.out.println("qwwwwwwwwwwwwwwwww"+bankMessageParameterConf.getBmpcId());
		// 读取图片绝对路径
		boolean bool = false;
		// 上传图片
				if (multipartRequest != null) {
					List advertisingImgFile = multipartRequest.getFiles("bmpcUrlfile");
					// 图片
					if (advertisingImgFile != null) {

						Map<String, String> davMap = webDavService.getWebDavInfo();
						for (int i = 0; i < advertisingImgFile.size(); i++) {
							MultipartFile file = (MultipartFile) advertisingImgFile
									.get(i);
							// 文件名是否为空
							if (StringUtils.hasText(file.getOriginalFilename())) {
								delDav(bankMessageParameterConf, request);
								String name = file.getOriginalFilename();

								// 保存
								try {

									String hostPath = request.getScheme()
											+ davMap.get("A");

									// 主机上传
									bool = PutFileToWebDAV.putFile(hostPath,
											"messageParameterConfImg", name,
											WebDavInfo.hostUserName,
											WebDavInfo.hostPassword, file, null);
									// 备机上传
									if (!bool) {
										String standPath = request.getScheme()
												+ davMap.get("B");
										bool = PutFileToWebDAV.putFile(standPath,
												"messageParameterConfImg", name,
												WebDavInfo.standbyUserName,
												WebDavInfo.standbyPassword, file, null);
									}
									// 取图片服务路径保存在数据服务器
									String url = WebDavInfo.hostLookUrl
											+ "messageParameterConfImg/" + PutFileToWebDAV.getName();
									bankMessageParameterConf.setBmpcUrl(url);
									bankMessageParameterConfService.updateBankMessageParameterConf(bankMessageParameterConf);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
							bankMessageParameterConfService.updateBankMessageParameterConf(bankMessageParameterConf);
						}
					}
				}
			mv.setViewName("save_result");
		return mv;
	}
	
	private void delDav(BankMessageParameterConf bankMessageParameterConf,HttpServletRequest request){
		// 读取图片绝对路径
		boolean bool = false;
		Map<String, String> davMap = webDavService.getWebDavInfo();
		List<String> list = new ArrayList<String>();
		list.add(bankMessageParameterConf.getBmpcUrl());
		String hostPath = request.getScheme() + davMap.get("A")+ "messageParameterConfImg/";
		bool = PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName,WebDavInfo.hostPassword, list);// 删除webDav服务器图片
		if (!bool) {
			String standPath = request.getScheme() + davMap.get("B")+ WebDavInfo.standbyUrl + "messageParameterConfImg/";
			bool = PutFileToWebDAV.delFile(standPath,WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,list);// 删除webDav服务器图片
		}
	}
}
