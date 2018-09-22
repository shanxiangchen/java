package com.app.controller;

  
 
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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

import com.app.entity.BankCodeInfo;
import com.app.entity.CardAddInfo;
import com.app.entity.WebDavInfo;
import com.app.service.CardAddInfoService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
 
@Controller
@RequestMapping(value="/cardAddInfo")
public class CardAddInfoController{

	@Autowired
	private CardAddInfoService cardAddInfoService;
	@Autowired
	private WebDavService webDavService;  
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selectCardAddInfoList(Model model,CardAddInfo cardAddInfo, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<CardAddInfo> cardAddInfos = cardAddInfoService.cardAddInfoPageList(cardAddInfo);
		request.setAttribute("cardAddInfos", cardAddInfos);
		mv.addObject("cardAddInfo", cardAddInfo);
		request.setAttribute("path", path);
		mv.setViewName("cardAddInfo/cardInfoList");
		return mv;
		
	}
	
	/**
	 * 跳转到新增卡页面
	 * @author zhaolei
	 * @date 2016-3-31 上午11:30:04
	 */
	@RequestMapping(value="/addCardInfo")
	public ModelAndView addCardInfo(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<BankCodeInfo> listOf=cardAddInfoService.listCardOfType();
		List<BankCodeInfo> listFor=cardAddInfoService.listCardForAge();
		List<BankCodeInfo> listAdd=cardAddInfoService.listCardAddType();
		List<BankCodeInfo> listLike=cardAddInfoService.listCardForLike();
		request.setAttribute("listOf",listOf);
		request.setAttribute("listFor",listFor);
		request.setAttribute("listAdd",listAdd);
		request.setAttribute("listLike",listLike);
		mv.setViewName("cardAddInfo/addCardInfo");
		return mv;
	}
	 
	
	/**
	 * 新增加办卡
	 * @author zhaolei
	 * @date 2016-4-6 下午3:38:29
	 */
	@RequestMapping(value = "/saveAddCardInfo", method = RequestMethod.POST)
	public ModelAndView saveCardInfo(CardAddInfo cardAddInfo,DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap,PrintWriter out){
			ModelAndView mv=new ModelAndView();
			//获取系统当前时间
			boolean bool=false; 
			//上传图片
			if(multipartRequest!=null){
				List<MultipartFile> cardPicUrl=multipartRequest.getFiles("cardPicUrlFile");
				//产品图片
				if(cardPicUrl.size()!=0){
					Map<String,String> davMap=webDavService.getWebDavInfo();
					for (int i = 0; i < cardPicUrl.size(); i++) {
						MultipartFile file=(MultipartFile)cardPicUrl.get(i);
						//文件是否为空
						if(StringUtils.hasText(file.getOriginalFilename())){
							String name=file.getOriginalFilename();
							//保存
							String hostPath = request.getScheme() + davMap.get("A");
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "cardAddInfoImg",name,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,null);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() + davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "cardAddInfoImg",name,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,null);
						    }
						    // 取图片服务路径保存在数据服务器
						    String url=WebDavInfo.hostLookUrl+"cardAddInfoImg/" + PutFileToWebDAV.getName();
						    cardAddInfo.setCardFlag("2");//加办卡产品
							cardAddInfo.setCardPicUrl(url);
							cardAddInfo.setCardPicName(PutFileToWebDAV.getName());
							cardAddInfoService.insertCardAddInfo(cardAddInfo);
							int cardId = cardAddInfoService.getCardId();
								String[] strs=cardAddInfo.getCardForLike().split(",");
								for(int j=0;j<strs.length;j++){
									Map<String,Object> map=new HashMap<String,Object>();
									map.put("cardId", cardId);
									map.put("cardForLike", strs[j]);
									cardAddInfoService.insertBankCardLike(map);
								} 
								
							
						}
					}	
				}
			}		
			mv.setViewName("save_result");
			return mv;
	}
	
	/**
	 * 删除加办卡
	 * @author zhaolei
	 * @date 2016-4-9 下午1:51:59
	 */
	@RequestMapping(value = "/deleteCardAdd" )
	public void  deleteCodeType(@RequestParam String cardIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=cardIds.split(",");
		   List<String> picNameList=null;
		   boolean bool=false; 
		   Map<String,String> davMap=webDavService.getWebDavInfo();
			String hostPath = request.getScheme() +davMap.get("A")+"cardAddInfoImg/";
			picNameList=this.getHistoryPicName(strs);
			bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"cardAddInfoImg/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);
			}
		   cardAddInfoService.deleteBankCardLike(strs);
		   cardAddInfoService.deleteCodeAddInfo(strs); 
		   out.print("success");
		   out.close();
	}
	
	/**
	 * 跳转到加办卡编辑页面
	 * @author zhaolei
	 * @date 2016-4-9 下午1:54:25
	 */
	@RequestMapping(value="listCardIAddInfoById")
	public ModelAndView listCardIfnoByid(@RequestParam String cardId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		CardAddInfo cardAddInfo=cardAddInfoService.getBankCardInfoById(cardId); 
		List<BankCodeInfo> listOf=cardAddInfoService.listCardOfType();
		List<BankCodeInfo> listFor=cardAddInfoService.listCardForAge();
		List<BankCodeInfo> listAdd=cardAddInfoService.listCardAddType();
		List<BankCodeInfo> listLike=cardAddInfoService.listCardForLike();
		List<CardAddInfo> cardAddLikes=cardAddInfoService.selectBankCardLikeById(cardId);
		StringBuffer strs=new StringBuffer();
		for(int i=0;i<cardAddLikes.size();i++){
			String cardForLike=cardAddLikes.get(i).getCardForLike();
			 strs.append(cardForLike).append(",");
		}
		cardAddInfo.setCardForLike(strs.toString());
		request.setAttribute("listOf",listOf);
		request.setAttribute("listFor",listFor);
		request.setAttribute("listAdd",listAdd);
		request.setAttribute("listLike",listLike);
		request.setAttribute("path",path); 
		request.setAttribute("cardAddInfo",cardAddInfo); 
		mv.setViewName("cardAddInfo/updateInfo");
		return mv;
	}
	/**
	 * 跳转到详情页面
	 * @author zhaolei
	 * @date 2016-4-11 下午3:52:51
	 */
	@RequestMapping(value="infoCardAddInfoById")
	public ModelAndView infoCardInfoByid(@RequestParam String cardId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		CardAddInfo cardAddInfo=cardAddInfoService.getBankCardInfoById(cardId); 
		List<BankCodeInfo> listOf=cardAddInfoService.listCardOfType();
		List<BankCodeInfo> listFor=cardAddInfoService.listCardForAge();
		List<BankCodeInfo> listAdd=cardAddInfoService.listCardAddType();
		List<BankCodeInfo> listLike=cardAddInfoService.listCardForLike();
		List<CardAddInfo> cardAddLikes=cardAddInfoService.selectBankCardLikeById(cardId);
		StringBuffer strs=new StringBuffer();
		for(int i=0;i<cardAddLikes.size();i++){
			String cardForLike=cardAddLikes.get(i).getCardForLike();
			 strs.append(cardForLike).append(",");
		}
		cardAddInfo.setCardForLike(strs.toString());
		request.setAttribute("listOf",listOf);
		request.setAttribute("listFor",listFor);
		request.setAttribute("listAdd",listAdd);
		request.setAttribute("listLike",listLike);
		request.setAttribute("path",path); 
		request.setAttribute("cardAddInfo",cardAddInfo); 
		mv.setViewName("cardAddInfo/infoCardAddInfo");
		return mv;
	}
	
	/**
	 * 编辑加办卡卡产品
	 * @author zhaolei
	 * @date 2016-4-11 下午12:16:09
	 */
	@RequestMapping(value = "/updateAddCardInfo", method = RequestMethod.POST)
	public ModelAndView updateCardInfo(CardAddInfo cardAddInfo,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, ModelMap modelMap,PrintWriter out) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		List<String> nameList=new ArrayList<String>();
		nameList.add(cardAddInfo.getCardPicName());
		if (multipartRequest != null) {
			List<MultipartFile> cardPicUrl=multipartRequest.getFiles("cardPicUrlFile");
			if (cardPicUrl.size() != 0) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < cardPicUrl.size(); i++) {
					MultipartFile file = (MultipartFile) cardPicUrl.get(i);
					// 文件是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();

						// 保存
						String hostPath = request.getScheme() + davMap.get("A");
						
						// 主机上传
						boolean bool = PutFileToWebDAV.putFile(hostPath,
								"cardAddInfoImg", name,
								WebDavInfo.hostUserName,
								WebDavInfo.hostPassword, file,nameList);
						// 备机上传
						if (!bool) {
							String standPath = request.getScheme() +davMap.get("B");
							bool = PutFileToWebDAV.putFile(standPath,
									"cardAddInfoImg", name,
									WebDavInfo.standbyUserName,
									WebDavInfo.standbyPassword, file,nameList);
						}
						// 取图片服务路径保存在数据服务器
						String url = WebDavInfo.hostLookUrl + "cardAddInfoImg/"
								+ PutFileToWebDAV.getName();
						cardAddInfo.setCardPicUrl(url);
						cardAddInfo.setCardPicName(PutFileToWebDAV.getName());
						
					}
					cardAddInfo.setCardFlag("2");// 加办卡产品
					cardAddInfo.setCardNum(request.getParameter("cardNum"));
					int  a=cardAddInfoService.updateBankCardInfoById(cardAddInfo);
					if(a>0){
						cardAddInfoService.deleteBankCardLikes(cardAddInfo.getCardId());
						String[] strs = cardAddInfo.getCardForLike().split(",");
						for (int j = 0; j < strs.length; j++) {
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("cardId",cardAddInfo.getCardId());
							map.put("cardForLike", strs[j]);
							cardAddInfoService.insertBankCardLike(map);
						}
					}
				}
			}else{
				cardAddInfo.setCardFlag("2");// 加办卡产品
				int count = cardAddInfoService
						.updateBankCardInfoById(cardAddInfo);
				String cardId = cardAddInfo.getCardId();
				if (count > 0) {
					cardAddInfoService.deleteBankCardLikes(cardId);
					String[] strs = cardAddInfo.getCardForLike().split(",");
					for (int j = 0; j < strs.length; j++) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("cardId", cardAddInfo.getCardId());
						map.put("cardForLike", strs[j]);
						cardAddInfoService.insertBankCardLike(map);
					}
				}
			}
		}
		mv.setViewName("save_result");
		return mv;
	}

	 
	public List<String> getHistoryPicName(String strs[]){
		List<String> nameList=new ArrayList<String>();
		if(strs.length!=0){
			List<String> picNameList = cardAddInfoService
					.selectBankCardAddInfoPics(strs);
			if(picNameList!=null){
				for(int i=0;i<picNameList.size();i++){
					String picName = picNameList.get(i);
					nameList.add(picName);
				}
			} 
		}
		return nameList;
	}
	 
}
