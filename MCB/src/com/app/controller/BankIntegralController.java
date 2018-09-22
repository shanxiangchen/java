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

import com.app.entity.BankIntegral;
import com.app.entity.WebDavInfo;
import com.app.service.BankEditorSaveService;
import com.app.service.BankIntegralService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
/**
 * 积分，Controller类
 * cretae date 2016/4/20
 * <br/>
 * @author shiguangting@tansun.cn.com
 *
 */
@Controller
@RequestMapping(value="/bankIntegral")
public class BankIntegralController {
	@Autowired
	private BankIntegralService bankIntegralService;
	@Autowired
	private BankEditorSaveService bankEditorSaveService;
	@Autowired
	private WebDavService webDavService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selBankIntegral(Model model,BankIntegral bankIntegral,ModelMap modelMap,
			HttpServletRequest request){
			ModelAndView mv=new ModelAndView();
			List<BankIntegral> bankIntegrals=bankIntegralService.selBankIntegralPageList(bankIntegral);
			modelMap.put("bankIntegrals", bankIntegrals);
			mv.addObject("bankIntegral", bankIntegral);
			model.addAttribute("path",path);
			mv.setViewName("bankIntegral/bankIntegral");
			return mv;
	}
	@RequestMapping(value="addBankIntegral")
	public ModelAndView addBankIntegral(Model model){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("bankIntegral/addBankIntegral");
		return mv;
	}
	@SuppressWarnings("rawtypes")
	@RequestMapping(value="saveBankIntegral",method = RequestMethod.POST)
	public ModelAndView saveBankIntegral(BankIntegral bankIntegral,HttpServletRequest request,
			DefaultMultipartHttpServletRequest multipartRequest){
			ModelAndView mv=new ModelAndView();
			//获取系统当前时间
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss"); 
			String date=dateFormat.format(new java.util.Date());
			String strs=bankIntegral.getIntegralDetailsPc();
		
			List integralImgRulFile = multipartRequest.getFiles("integralImgRulFile");
			// 读取图片绝对路径
			boolean bool=false; 
			//图片
			if(integralImgRulFile!=null){
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < integralImgRulFile.size(); i++) {
					MultipartFile file = (MultipartFile) integralImgRulFile.get(i);
					// 文件名是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						// 保存
						try {
							 
							String hostPath = request.getScheme() + davMap.get("A");
								  
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "uploadBankIntegral",name,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,null);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() + davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "uploadBankIntegral",name,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,null);
						    }
							// 取图片服务路径保存在数据服务器
							String url =WebDavInfo.hostLookUrl+"uploadBankIntegral/" + PutFileToWebDAV.getName();
							bankIntegral.setIntegralImgRul(url);
							bankIntegral.setIntegralImgRulName(PutFileToWebDAV.getName());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
			int radom1 = (int)Math.random()*100000000;
			String bankIntegralId = radom1+date;
			String integral_mb="/weba/Integral/"+bankIntegralId;
			bankIntegral.setIntegralId(bankIntegralId);
			 bankIntegral.setIntegralDetailsMb(integral_mb);
			  bankIntegralService.saveBankIntegral(bankIntegral);
			  if(!"".equals(strs)&&strs!=null){
				  String uploadPath = request.getSession().getServletContext()
							.getRealPath("kindEditor");
				  String  lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"Integral",bankIntegralId);
			  }
			  
			  mv.setViewName("save_result");
			return mv;
	}
		
	@RequestMapping(value="IntegralDetails")
	public String IntegralDetails(@RequestParam String integralId,String id,BankIntegral bankIntegral,HttpServletRequest request) {			
		if(id.equals("2")){
			BankIntegral integral=bankIntegralService.selIntegralDetails(integralId);
			request.setAttribute("integral", integral);
			request.setAttribute("path", path);
			 return "bankIntegral/editBankIntegra";
		}
		if(id.equals("1")){
			BankIntegral integral=bankIntegralService.selIntegralDetails(integralId);
			request.setAttribute("integral", integral);
			request.setAttribute("path", path);
			 return "bankIntegral/IntegralDetails";
		}
		return null;
	}
	

	/**
	 * 修改保存
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "editBankIntegral", method = RequestMethod.POST)
	public ModelAndView editBankIntegral(BankIntegral bankIntegral, DefaultMultipartHttpServletRequest multipartRequest, HttpServletRequest request) {
		 
		String strs=bankIntegral.getIntegralDetailsPc();
		ModelAndView mv = new ModelAndView();
		List<String> list=new ArrayList<String>();
		list.add(bankIntegral.getIntegralImgRulName());
		boolean bool = false;
		if (multipartRequest != null) {
			@SuppressWarnings("rawtypes")
			List integralImgRulFile = multipartRequest.getFiles("integralImgRulFile");
			// 广告图片
			if (integralImgRulFile != null) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < integralImgRulFile.size(); i++) {
					MultipartFile file = (MultipartFile) integralImgRulFile.get(i);
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();

						// 保存
						try {
							String hostPath = request.getScheme() + davMap.get("A");

							// 主机上传
							bool = PutFileToWebDAV.putFile(hostPath, "uploadBankIntegral", name, WebDavInfo.hostUserName,
									WebDavInfo.hostPassword, file, list);
							// 备机上传
							if (!bool) {
								String standPath = request.getScheme() + davMap.get("B");
								bool = PutFileToWebDAV.putFile(standPath, "uploadBankIntegral", name, WebDavInfo.standbyUserName,
										WebDavInfo.standbyPassword, file, list);
							}
							// 取图片服务路径保存在数据服务器
							String url = WebDavInfo.hostLookUrl + "uploadBankIntegral/" + PutFileToWebDAV.getName();
							String lookUrl="";
							  if(!"".equals(strs)&&strs!=null){
								  String uploadPath = request.getSession().getServletContext()
											.getRealPath("kindEditor");
								  lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"Integral",bankIntegral.getIntegralId());
							  }
							bankIntegral.setIntegralImgRul(url);
							bankIntegral.setIntegralImgRulName(PutFileToWebDAV.getName());
							bankIntegral.setIntegralDetailsPc(lookUrl);
							  
							bankIntegralService.updateBankIntegral(bankIntegral);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
				  if(!"".equals(strs)&&strs!=null){
					  String uploadPath = request.getSession().getServletContext()
								.getRealPath("kindEditor");
					  String  lookUrl=bankEditorSaveService.printHtml(strs,uploadPath,"Integral",bankIntegral.getIntegralId());
				  }
				bankIntegral.setIntegralDetailsPc(strs);
				bankIntegralService.updateBankIntegral(bankIntegral);
		}
		mv.setViewName("save_result");
		return mv;
	}
	
	
	@RequestMapping(value="delBankIntegral")
	public void delCardInfo(@RequestParam String integralId,@RequestParam String integralImgRulName,HttpServletRequest request,PrintWriter out){
		boolean bool=false;
		 
		List<String> list=new ArrayList<String>();
		list.add(integralImgRulName);
		List<String> lists=new ArrayList<String>();
		lists.add(integralId);
		Map<String,String> davMap=webDavService.getWebDavInfo();
		String hostPath = request.getScheme() + davMap.get("A")+"uploadBankIntegral/";
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, list);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"uploadBankIntegral/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, list);//删除webDav服务器图片
		}
		String htmlPath = request.getScheme() +davMap.get("A")+"Integral/";
		bool=PutFileToWebDAV.delFile(htmlPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, lists);//删除富文本HTML
		//备机删除
		if(!bool){
			String standPath = request.getScheme() +davMap.get("B")+"Integral/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, lists); 
		}
		bankIntegralService.deleteBankIntegral(integralId);//删除数据库信息
		out.write("success");
		out.close();
	}
}

