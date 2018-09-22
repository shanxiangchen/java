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

import com.app.entity.BankCardType;
import com.app.entity.PageInfo;
import com.app.entity.WebDavInfo;
import com.app.service.BankCardTypeService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
 
@Controller
@RequestMapping(value="/cardType")
public class BankCardTypeController{

	@Autowired
	private BankCardTypeService bankCardTypeService;
	@Autowired 
	private WebDavService webDavService;  
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selectCardAddInfoList(Model model,PageInfo pageInfo,BankCardType bankCardType, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		 
		List<BankCardType> list = bankCardTypeService.bankCardTypePageList(bankCardType);
		List<BankCardType> bankCardTypes=new ArrayList<BankCardType>();
		for(BankCardType type:list){
			String[] strs=type.getCardOfType().split(",");
			String cardOfName=bankCardTypeService.selectCardOfName(strs);
			type.setCardOfName(cardOfName);
			bankCardTypes.add(type);
		}
		
		modelMap.put("bankCardTypes", bankCardTypes);
		request.setAttribute("path", path);
		request.setAttribute("bankCardType", bankCardType);
		mv.setViewName("bankCardType/bankCardTypeList");
		return mv;
		
	}
	/**
	 * 跳转到新增卡种页面
	 * @author zhaolei
	 * @date 2016-4-12 上午10:33:42
	 */
	@RequestMapping(value="/addCardType")
	public ModelAndView addCardType(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<Map<String,String>> listType=bankCardTypeService.selectBankCodeInfoList(); 
		request.setAttribute("listType", listType);
		mv.setViewName("bankCardType/addBankCardType");
		return mv;
	}
	
	/**
	 * 新增卡产品所属种类
	 * @author zhaolei
	 * @date 2016-4-12 下午3:00:12
	 */
	@RequestMapping(value = "/saveCardType", method = RequestMethod.POST)
	public ModelAndView saveCardInfo(BankCardType bankCardType,DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap,PrintWriter out){
			ModelAndView mv=new ModelAndView();
			int countNum=bankCardTypeService.selectBankCardTypeBynum(bankCardType.getTypeNum());
			if(countNum>0){
				out.print("1");
				out.close();
				return null;
			}
			//获取系统当前时间
			boolean bool=false; 
			//上传图片
			if(multipartRequest!=null){
				List<MultipartFile> typePicUrl=multipartRequest.getFiles("typePicFile");
				 
				if(typePicUrl!=null){
					Map<String,String> davMap=webDavService.getWebDavInfo(); 
					for (int i = 0; i < typePicUrl.size(); i++) {
						MultipartFile file=(MultipartFile)typePicUrl.get(i);
						//文件是否为空
						if(StringUtils.hasText(file.getOriginalFilename())){
							String name=file.getOriginalFilename();
							//保存
							String hostPath = request.getScheme() + davMap.get("A");
							//主机上传
						    bool=PutFileToWebDAV.putFile(hostPath, "cardTypeImg",name,
						    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,
									file,null);
						    //备机上传
						    if(!bool){
						    	String standPath = request.getScheme() +davMap.get("B");
						    	bool=PutFileToWebDAV.putFile(standPath, "cardTypeImg",name,
							    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,
										file,null);
						    }
						    // 取图片服务路径保存在数据服务器
						    String url=WebDavInfo.hostLookUrl+"cardTypeImg/" + PutFileToWebDAV.getName();
						    bankCardType.setTypePicUrl(url);
						    bankCardType.setTypePicName(PutFileToWebDAV.getName());
						    String bankcartTypeId = bankCardTypeService.selectBankCartTypeId();
						    int cartTypeId = 0;
						    if(bankcartTypeId != null && bankcartTypeId != ""){
						    	cartTypeId = Integer.parseInt(bankcartTypeId);
						    }
						    String	typeNum = ""+(cartTypeId+1);
						    bankCardType.setTypeNum(typeNum);
							bankCardTypeService.insertBankCardType(bankCardType);
							 
						}
					}	
				}
			}		
			mv.setViewName("save_result");
			return mv;
	}
	
	/**
	 * 删除卡产品种类
	 * @author zhaolei
	 * @date 2016-4-12 下午3:26:45
	 */
	@RequestMapping(value = "/deleteCardType" )
	public void  deleteCodeType(@RequestParam String typeIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=typeIds.split(",");
		   List<String> picNameList=null;
		   boolean bool=false; 
		   Map<String,String> davMap=webDavService.getWebDavInfo(); 
		   String hostPath = request.getScheme() + davMap.get("A")+"cardTypeImg/";
			picNameList=this.getHistoryPicName(strs);
			bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);
			if(!bool){
				String standPath = request.getScheme() +davMap.get("B")+"cardTypeImg/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);
			}
		   bankCardTypeService.deleteCodeType(strs); 
		   out.print("success");
		   out.close();
	}
	
	/**
	 * 获取删除图片名称
	 * @author zhaolei
	 * @date 2016-4-12 下午3:39:00
	 */
	public List<String> getHistoryPicName(String strs[]){
		List<String> nameList=new ArrayList<String>();
		if(strs.length!=0){
			List<String> picNameList = bankCardTypeService
					.selectBankCardTypePics(strs);
			if(picNameList!=null){
				for(int i=0;i<picNameList.size();i++){
					String picName = picNameList.get(i);
					nameList.add(picName);
				}
			} 
		}
		return nameList;
	}
	/**
	 * 跳转到编辑页面
	 * @author zhaolei
	 * @date 2016-4-12 下午3:58:51
	 */
	@RequestMapping(value="cardTypeListById")
	public ModelAndView cardTypeListById(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		BankCardType bankCardType=bankCardTypeService.cardTypeListById(typeId); 
		List<Map<String,String>> listType=bankCardTypeService.selectBankCodeInfoList(); 
		request.setAttribute("listType", listType); 
		request.setAttribute("path",path); 
		request.setAttribute("bankCardType",bankCardType); 
		mv.setViewName("bankCardType/updateBankCardType");
		return mv;
	}
	
	/**
	 * 编辑卡种类
	 * @author zhaolei
	 * @date 2016-4-12 下午4:14:46
	 */
	@RequestMapping(value = "/updateCardType", method = RequestMethod.POST)
	public ModelAndView updateCardType(BankCardType bankCardType,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
		// 获取系统当前时间
		List<String> nameList=new ArrayList<String>();
		nameList.add(bankCardType.getTypePicName());
		if (multipartRequest != null) {
			List<MultipartFile> typePicUrl = multipartRequest
					.getFiles("typePicFile");
			if (typePicUrl.size()!=0) {
				Map<String,String> davMap=webDavService.getWebDavInfo(); 
				for (int i = 0; i < typePicUrl.size(); i++) {
					MultipartFile file = (MultipartFile) typePicUrl.get(i);
					// 文件是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						String name = file.getOriginalFilename();
						// 保存
						String hostPath = request.getScheme() +davMap.get("A");
						
						// 主机上传
						boolean bool = PutFileToWebDAV.putFile(hostPath,
								"cardTypeImg", name,
								WebDavInfo.hostUserName,
								WebDavInfo.hostPassword, file,nameList);
						// 备机上传
						if (!bool) {
							String standPath = request.getScheme() + davMap.get("B");
							bool = PutFileToWebDAV.putFile(standPath,
									"cardTypeImg", name,
									WebDavInfo.standbyUserName,
									WebDavInfo.standbyPassword, file,nameList);
						}
						// 取图片服务路径保存在数据服务器
						String url = WebDavInfo.hostLookUrl + "cardTypeImg/"
								+ PutFileToWebDAV.getName();
						bankCardType.setTypePicUrl(url);
						bankCardType.setTypePicName(PutFileToWebDAV.getName());
						
					}
					bankCardTypeService.updateCardType(bankCardType);
					 
				}
			}else{
				bankCardTypeService.updateCardType(bankCardType);
			}
		}

		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 跳转到详情页面
	 * @author zhaolei
	 * @date 2016-4-12 下午4:52:16
	 */
	@RequestMapping(value="infoCardTypeById")
	public ModelAndView infoCardInfoByid(@RequestParam String typeId,HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		BankCardType bankCardType=bankCardTypeService.cardTypeListById(typeId); 
		List<Map<String,String>> listType=bankCardTypeService.selectBankCodeInfoList(); 
		request.setAttribute("listType", listType); 
		request.setAttribute("path",path); 
		request.setAttribute("bankCardType",bankCardType); 
		mv.setViewName("bankCardType/infoBankCardType");
		return mv;
	}
	 
	
	 
}
