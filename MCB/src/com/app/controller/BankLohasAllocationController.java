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

import com.app.entity.BankInnerLink;
import com.app.entity.BankLohasAllocation;
import com.app.entity.WebDavInfo;
import com.app.service.AdvertService;
import com.app.service.BankInnerLinkService;
import com.app.service.BankLohasAllocationService;
import com.app.service.WebDavService;
import com.app.util.PutFileToWebDAV;
import com.app.util.RuntimeProperites;
 
@Controller
@RequestMapping(value="/lohasAllocation")
public class BankLohasAllocationController{

	@Autowired
	private BankLohasAllocationService bankLohasAllocationService;
	@Autowired
	private WebDavService webDavService;
	@Autowired
	private AdvertService advertService;
	@Autowired
	private BankInnerLinkService bankInnerLinkService;
	private String path=RuntimeProperites.getCIBPROPMAP("path");
	@RequestMapping
	public ModelAndView selectBankLohasAllocationList(Model model,BankLohasAllocation bankLohasAllocation, ModelMap modelMap,HttpServletRequest request){
		ModelAndView mv=new ModelAndView(); 
		List<BankLohasAllocation> allocations=bankLohasAllocationService.selectBankLohasAllocationPageList(bankLohasAllocation);
		if(allocations.size()>0){
			for (int i = 0,b=allocations.size(); i < b; i++) {
				String lohasParam = allocations.get(i).getLohasParam();
				if(lohasParam!="" && lohasParam!= null){
					lohasParam = lohasParam.trim().replace("&", ",");
					allocations.get(i).setLohasParam(lohasParam);
				}
			}
		}
		request.setAttribute("path", path); 
		modelMap.put("allocations", allocations);
		@SuppressWarnings("rawtypes")
		List listname = advertService.selectAllLinkName();
		request.setAttribute("listname",listname);
		request.setAttribute("bankLohasAllocation",bankLohasAllocation);
		mv.setViewName("bankLohasAllocation/bankLohasAllocation");
		return mv;
		
	}
	
	/**
	 * 跳转到新增页面
	 * @author zhao.lei
	 * @date 2016-5-4 下午1:53:19
	 */
	@RequestMapping(value="/addLohasAllocation")
	public ModelAndView addLohasAllocation(HttpServletRequest request){
		ModelAndView mv=new ModelAndView();
		List<Map<String,String>> innerLinkList=bankLohasAllocationService.selectInnerLink(); 
		request.setAttribute("innerLinkList",innerLinkList);
		@SuppressWarnings("rawtypes")
		List listname = advertService.selectAllLinkName();
		request.setAttribute("listname",listname);
		mv.setViewName("bankLohasAllocation/addBankLohasAllocation");
		return mv;
	}
	
	
	@RequestMapping(value = "/saveLohasAllocation", method = RequestMethod.POST)
	public void saveLohasAllocation(BankLohasAllocation bankLohasAllocation,DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request,ModelMap modelMap,PrintWriter out){
		    int num=bankLohasAllocationService.selectCountByimgWhere(bankLohasAllocation);
		    String showType=bankLohasAllocation.getLohasShowType();
		    if(showType==null||"".equals(showType)){
		    	bankLohasAllocation.setLohasShowType("0");
		    }
		   if(num>0){
		    	out.print("1");
		    	out.close();
		    }else{
		    	//获取系统当前时间
				List<Map<String,Object>> list=new ArrayList<Map<String,Object>>(); 
				boolean bool=false; 
				String[] lohasURLstrs=bankLohasAllocation.getLohasOutLinks().split(",");
				String[] linkTypeStrs = bankLohasAllocation.getLinkTypes().split(",");
				String lohasParamsStr = bankLohasAllocation.getLohasParams().replace(";", "&");
				String[] lohasParamStrs = lohasParamsStr.split(",");
				String[] linkNoStrs = bankLohasAllocation.getLinkNos().split(",");
				String[] isAddPhones = bankLohasAllocation.getIsAddPhones().split(",");
				//上传图片
				if(multipartRequest!=null){
					Map<String,String> davMap=webDavService.getWebDavInfo();
					List<MultipartFile> lohasPicUrl=multipartRequest.getFiles("lohasfile");
					if(lohasPicUrl!=null){
						for (int i = 0; i < lohasPicUrl.size(); i++) {
							MultipartFile file=(MultipartFile)lohasPicUrl.get(i);
							//文件是否为空
							if(StringUtils.hasText(file.getOriginalFilename())){
								//String name=file.getOriginalFilename();
								String fileName=file.getOriginalFilename();
								Map<String,Object> newMap=new HashMap<String,Object>();
								newMap.put("name", fileName);
								newMap.put("file", file);
								list.add(newMap);
							    // 取图片服务路径保存在数据服务器
							    String url=WebDavInfo.hostLookUrl+"lohasImg/" + PutFileToWebDAV.getName();
							    String linkType = linkTypeStrs[i];
							    String isAddPhone = isAddPhones[i];
							    if("1".equals(linkType)){
							    	//linkNo
							    	String linkNo = linkNoStrs[i];
							    	BankInnerLink bankInnerLink = new BankInnerLink();
							    	bankInnerLink.setLinkNo(linkNo);
							    	BankInnerLink selectBankInnerLink = bankInnerLinkService.selectBankInnerLink(bankInnerLink);
							    	String linkURL = selectBankInnerLink.getLinkURL();
							    	bankLohasAllocation.setLohasOutLink(linkURL);
							    	bankLohasAllocation.setLinkNo(linkNo);
							    }else if("2".equals(linkType)){
							    	bankLohasAllocation.setLohasOutLink(lohasURLstrs[i]);
							    }
							    bankLohasAllocation.setIsAddPhone(isAddPhone);
							    bankLohasAllocation.setLohasSortNo(i);
							    bankLohasAllocation.setLohasImgUrl(url); 
							    bankLohasAllocation.setLohasImgName(PutFileToWebDAV.getName()); 
							    bankLohasAllocation.setLinkType(linkType);
							    if(lohasParamStrs != null && lohasParamStrs.length != 0){
							    	String lohasParam = lohasParamStrs[i];
							    	if(lohasParam!=""&&lohasParam!=null){
							    		bankLohasAllocation.setLohasParam(lohasParamStrs[i]);
							    	}else{
							    		bankLohasAllocation.setLohasParam("");
							    	}
							    }
								bankLohasAllocationService.insertLohasAllocation(bankLohasAllocation);
								 
							}
						}
						
						 //保存
						String hostPath = request.getScheme() + davMap.get("A");
						//主机上传
					    bool=PutFileToWebDAV.putFiles(hostPath, "lohasImg",list,
					    		WebDavInfo.hostUserName, WebDavInfo.hostPassword,null);
					    //备机上传
					    if(!bool){
					    	String standPath = request.getScheme() +davMap.get("B");
					    	bool=PutFileToWebDAV.putFiles(standPath, "lohasImg",list,
						    		WebDavInfo.standbyUserName, WebDavInfo.standbyPassword,null);
					    }
					    if(!bool){
					    	out.print("2");
							out.close();
					    }else{
					    	out.print("success");
							out.close();
					    }
					}
				}		
		    }
		    
			
			 
	}
	
	
	
	@RequestMapping(value = "/delLohasAllocation" )
	public void  delLohasAllocation(@RequestParam String lohasIds, PrintWriter out,HttpServletRequest request){
		 
		   String strs[]=lohasIds.split(",");
		   List<String> picNameList=null;
		   boolean bool=false; 
		   Map<String,String> davMap=webDavService.getWebDavInfo();
			String hostPath = request.getScheme() +davMap.get("A")+"lohasImg/";
			picNameList=this.getHistoryPicName(strs);
			bool=PutFileToWebDAV.delFile(hostPath, WebDavInfo.hostUserName, WebDavInfo.hostPassword, picNameList);
			if(!bool){
				String standPath = request.getScheme() + davMap.get("B")+"lohasImg/";
				bool=PutFileToWebDAV.delFile(standPath, WebDavInfo.standbyUserName, WebDavInfo.standbyPassword, picNameList);
			}
		    int count=bankLohasAllocationService.deleteLohasAllocation(strs); 
		    if(count>0){
		    	out.print("success");
			    out.close();
		    }else{
		    	out.print("error");
			    out.close();
		    }
		    
	}
	
	
	public List<String> getHistoryPicName(String strs[]){
		List<String> nameList=new ArrayList<String>();
		if(strs.length!=0){
			List<String> picNameList = bankLohasAllocationService
					.selectLohasAllocationPics(strs);
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
	 * @author zhao.lei
	 * @date 2016-5-5 下午3:15:03
	 */
	@RequestMapping(value="editLohasAllocation")
	public ModelAndView editLohasAllocation(@RequestParam String lohasId,HttpServletRequest request,BankLohasAllocation bankLohasAllocation){
		ModelAndView mv=new ModelAndView();
		BankLohasAllocation lohas=bankLohasAllocationService.selectBankLohasAllocationById(lohasId);
		String lohasParam = lohas.getLohasParam();
		if(lohasParam!="" && lohasParam!= null){
			lohasParam = lohasParam.trim().replace("&", ",");
			lohas.setLohasParam(lohasParam);
		}
		request.setAttribute("lohas", lohas);
		request.setAttribute("path", path); 
		@SuppressWarnings("rawtypes")
		List listname = advertService.selectAllLinkName();
		request.setAttribute("listname",listname);
		mv.setViewName("bankLohasAllocation/updateBankLohasAllocation");
		return mv;
	}
	
	
	@RequestMapping(value = "/updateLohasAllocation", method = RequestMethod.POST)
	public ModelAndView updateLohasAllocation(BankLohasAllocation bankLohasAllocation,
			DefaultMultipartHttpServletRequest multipartRequest,
			HttpServletRequest request, ModelMap modelMap) {
		ModelAndView mv = new ModelAndView();
	    String showType=bankLohasAllocation.getLohasShowType();
	    if(showType==null||"".equals(showType)){
	    	bankLohasAllocation.setLohasShowType("0");
	    }
		// 获取系统当前时间
		List<String> nameList=new ArrayList<String>();
		nameList.add(bankLohasAllocation.getLohasImgName());
		String linkType = bankLohasAllocation.getLinkType();
		if (multipartRequest != null) {
			List<MultipartFile> lohasfile = multipartRequest
					.getFiles("lohasfile");
			if (lohasfile!= null && lohasfile.size() != 0) {
				Map<String,String> davMap=webDavService.getWebDavInfo();
				for (int i = 0; i < lohasfile.size(); i++) {
					MultipartFile file = (MultipartFile) lohasfile.get(i);
					// 文件是否为空
					if (StringUtils.hasText(file.getOriginalFilename())) {
						//String name = file.getOriginalFilename();
						String fileName =file.getOriginalFilename();
						// 保存
						String hostPath = request.getScheme() + davMap.get("A");
						// 主机上传
						boolean bool = PutFileToWebDAV.putFile(hostPath,
								"lohasImg", fileName,
								WebDavInfo.hostUserName,
								WebDavInfo.hostPassword, file,nameList);
						// 备机上传
						if (!bool) {
							String standPath = request.getScheme() +davMap.get("B");
							bool = PutFileToWebDAV.putFile(standPath,
									"lohasImg", fileName,
									WebDavInfo.standbyUserName,
									WebDavInfo.standbyPassword, file,nameList);
						}
						// 取图片服务路径保存在数据服务器
						String url = WebDavInfo.hostLookUrl + "lohasImg/"
								+ PutFileToWebDAV.getName();
						bankLohasAllocation.setLohasImgUrl(url);
						bankLohasAllocation.setLohasImgName(PutFileToWebDAV.getName());
						if("1".equals(linkType)){
							String linkNo = bankLohasAllocation.getLinkNo();
							BankInnerLink bankInnerLink = new BankInnerLink();
							bankInnerLink.setLinkNo(linkNo);
					    	BankInnerLink selectBankInnerLink = bankInnerLinkService.selectBankInnerLinkByLinkNo(bankInnerLink);
					    	String linkURL = selectBankInnerLink.getLinkURL();
					    	bankLohasAllocation.setLohasOutLink(linkURL);
						}
					}
					 
				}
			}else{
				if("1".equals(linkType)){
					String linkNo = bankLohasAllocation.getLinkNo();
					BankInnerLink bankInnerLink = new BankInnerLink();
					bankInnerLink.setLinkNo(linkNo);
			    	BankInnerLink selectBankInnerLink = bankInnerLinkService.selectBankInnerLinkByLinkNo(bankInnerLink);
			    	String linkURL = selectBankInnerLink.getLinkURL();
			    	bankLohasAllocation.setLohasOutLink(linkURL);
			    	bankLohasAllocation.setIsAddPhone("0");
				}
			}
		}
		String lohasParam = bankLohasAllocation.getLohasParam();
		if(lohasParam.trim() != "" && lohasParam != null){
			lohasParam = bankLohasAllocation.getLohasParam().replace(",", "&");
			bankLohasAllocation.setLohasParam(lohasParam);
		}else{
			lohasParam ="";
		}
		bankLohasAllocationService.updateLohasAllocation(bankLohasAllocation);
		mv.setViewName("save_result");
		return mv;
	}
	 
	 
}
