package com.app.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankCodeInfo;
import com.app.entity.CardInfo;
import com.app.entity.WebDavInfo;
import com.app.service.CardInfoService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;

/**
 * 卡产品信息表,Controller方法
 * create date 2016/3/15
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
@Controller
@RequestMapping(value="/cardInfo")
public class CardInfoController {
	@Autowired
	private CardInfoService cardInfoService;
	@Autowired
	private WebDavService webDavService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView listCardInfo(HttpServletRequest request, ModelMap modelMap,CardInfo cardInfo){
		ModelAndView mv=new ModelAndView();
		List<CardInfo> cardInfos = cardInfoService.cardInfoPageList(cardInfo);
		modelMap.put("cardInfos", cardInfos);
		request.setAttribute("cardInfo",cardInfo);
		request.setAttribute("path",path);
		mv.setViewName("cardInfo/cardInfo");
		return mv;
	}
	@RequestMapping(value="/addCardInfo")
	public ModelAndView addCardInfo(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<BankCodeInfo>list=cardInfoService.listBankCodeInfo();
		request.setAttribute("list",list);
		mv.setViewName("cardInfo/addCardInfo");
		return mv;
	}
	
	@RequestMapping(value = "/saveCardInfo", method = RequestMethod.POST)
	public ModelAndView saveCardInfo(CardInfo cardInfo,DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap){
			ModelAndView mv=new ModelAndView();
			//读取图片路径
			boolean bool=false; 
			
			//上传图片
			if(multipartRequest!=null){
				@SuppressWarnings("rawtypes")
				List cardPicUrl=multipartRequest.getFiles("cardPicUrlFile");
				//产品图片
				if(cardPicUrl!=null){
					Map<String,String> davMap=webDavService.getWebDavInfo();
					for (int i = 0; i < cardPicUrl.size(); i++) {
						MultipartFile file=(MultipartFile)cardPicUrl.get(i);
						//文件是否为空
						
						if(StringUtils.hasText(file.getOriginalFilename())){
							String name=file.getOriginalFilename();
							//保存
							String hostPath = request.getScheme() +davMap.get("A");
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "cardInfoImg",name,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,null);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() +davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "cardInfoImg",name,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,null);
						    }
						    // 取图片服务路径保存在数据服务器
						    String url=WebDavInfo.hostLookUrl+"cardInfoImg/" + PutFileToWebDAV.getName();
							cardInfo.setCardPicUrl(url);
							cardInfo.setCardPicName(PutFileToWebDAV.getName());
							cardInfoService.insertCardInfo(cardInfo);
						}
					}	
				}
			}		
				mv.setViewName("save_result");
			return mv;
	}
	
	@RequestMapping(value="listCardIfnoByid")
	public ModelAndView listCardIfnoByid(@RequestParam int cardId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<BankCodeInfo>list=cardInfoService.listBankCodeInfo();
		request.setAttribute("list",list);
		
		CardInfo cardInfo=cardInfoService.listCardInfoById(cardId);
		request.setAttribute("cardInfo", cardInfo);
		request.setAttribute("path", path);
		mv.setViewName("cardInfo/updateCardInfo");
		return mv;
	}
	
	//获取图片名
	public List<String> getCardInfoPicName(int cardId){
		List<String> nameList=new ArrayList<String>();
		if(cardId!=0){
			List<CardInfo>infos=cardInfoService.listCardInfoByIds(cardId);
			for(int i=0;i<infos.size();i++){
				CardInfo cardInfo= infos.get(i);
				String shopName=cardInfo.getCardPicName();
				nameList.add(shopName);
			}
		}
		return nameList;
	}
	
	
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/deitCardInfo", method = RequestMethod.POST)
	public ModelAndView deitCardInfo(CardInfo cardInfo,DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap){
			ModelAndView mv=new ModelAndView();
			//读取图片路径
			boolean bool=false; 
			List<String> picNameList=null;
			//上传图片
			if(multipartRequest!=null){
				 
				List cardPicUrl=multipartRequest.getFiles("cardPicUrlFile");
				//产品图片
				if(cardPicUrl!=null){
					Map<String,String> davMap=webDavService.getWebDavInfo();
					int cardId=Integer.parseInt(request.getParameter("cardId"));
					picNameList=this.getCardInfoPicName(cardId);
					for (int i = 0; i < cardPicUrl.size(); i++) {
						MultipartFile file=(MultipartFile)cardPicUrl.get(i);
						//文件是否为空
						
						if(StringUtils.hasText(file.getOriginalFilename())){
							String name=file.getOriginalFilename();
							//保存
							String hostPath = request.getScheme() +davMap.get("A");
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "cardInfoImg",name,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,picNameList);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() + davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "cardInfoImg",name,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,picNameList);
						    }
						    // 取图片服务路径保存在数据服务器
						    String url=WebDavInfo.hostLookUrl+"cardInfoImg/" + PutFileToWebDAV.getName();
							cardInfo.setCardPicUrl(url);
							cardInfo.setCardPicName(PutFileToWebDAV.getName());
							cardInfoService.editCardInfo(cardInfo);
						}
					}	
				}
			}
				cardInfoService.editCardInfos(cardInfo);
				mv.setViewName("save_result");
			return mv;
	}
	
	@RequestMapping(value="delCardInfo")
	public void delCardInfo(@RequestParam int cardId,HttpServletRequest request,PrintWriter out){
		List<String> picNameList=null;
		boolean bool=false;
		Map<String,String> davMap=webDavService.getWebDavInfo(); 
		String hostPath = request.getScheme() + davMap.get("A")+"cardInfoImg/";
		
		picNameList=this.getCardInfoPicName(cardId);
		bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);//删除webDav服务器图片
		if(!bool){
			String standPath = request.getScheme() + davMap.get("B")+"cardInfoImg/";
			bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);//删除webDav服务器图片
		}
		cardInfoService.delCardInfo(cardId);//删除数据库信息
		out.write("success");
		out.close();
	}
}
