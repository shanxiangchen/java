package com.app.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.app.entity.BankCarStaging;
import com.app.listener.BankCarStagingExcelView;
import com.app.listener.CarStagingService;
import com.app.listener.Const;
import com.app.service.BankCarStagingService;

/**
 * 汽车分期，经销商 Controller方法 create date 2016/6/29 <br/>
 * 
 * @author shiguangting@tansun.com.cn
 * 
 */
@Controller
@RequestMapping(value = "/carStaging")
public class bankCarStagingController {
	@Autowired
	private BankCarStagingService bankCarStagingService;

	@RequestMapping
	public ModelAndView selCarStrging(HttpServletRequest request,
			ModelMap modelMap, BankCarStaging bankCarStaging) {
		ModelAndView mv = new ModelAndView();
		List<BankCarStaging> bankCarStagings = bankCarStagingService
				.selbankCarStagingPageList(bankCarStaging);
		String carStagingCity=request.getParameter("carStagingCity");
		bankCarStaging.setCarStagingCity(carStagingCity);
		modelMap.put("bankCarStagings", bankCarStagings);
		request.setAttribute("bankCarStaging", bankCarStaging);
		request.setAttribute("carStagingCity", carStagingCity);
		mv.setViewName("StagingList/carStaging");
		return mv;
	}

	@RequestMapping(value = "/excel")
	public ModelAndView export2Excel(BankCarStaging bankCarStaging,
			HttpServletRequest request, @RequestParam String carStagingCity) throws UnsupportedEncodingException {
		Map<String, Object> dataMap = new HashMap<String, Object>();
		bankCarStaging.setCarStagingCity(carStagingCity);
		List<String> titles = new ArrayList<String>();
		titles.add("城市");
		titles.add("品牌");
		titles.add("经销商名称");
		titles.add("电话");
		titles.add("经销商地址");
		dataMap.put("titles", titles);
		List<BankCarStaging> expCarStaging = bankCarStagingService.expCarStaging(bankCarStaging);
		dataMap.put("expCarStaging", expCarStaging);
			
		BankCarStagingExcelView erv = new BankCarStagingExcelView();
		ModelAndView mv = new ModelAndView(erv, dataMap);
		return mv;
	}

	@RequestMapping(value = "/importout")
	public ModelAndView importout(Model model) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("StagingList/impCarStaging");
		return mv;
	}

	@Transactional
	@RequestMapping(value = "/addmerchantList")
	public void bank_book(@RequestParam("files") MultipartFile files,
			HttpServletRequest request, HttpServletResponse response,
			PrintWriter out, ModelMap model) {
		//ModelAndView mv = new ModelAndView();
		File targetFile = null;
		String ur = null;
		Date date = new Date();
		String errorInfo = "";
		int badNum = 0;
		int num = 1;
		try {
			if (files != null) {
				long size = files.getSize();
				if (size > 1024 * 1024) {
					errorInfo = "toBig";
				}else{
					// 获取服务器路径
					String uploadTxt = request.getSession().getServletContext().getRealPath("");
	
					// 文件名是否为空
					if (StringUtils.hasText(files.getOriginalFilename())) {
						String name = files.getOriginalFilename();
						String body = "";
						String ext = "";
	
						int pot = name.lastIndexOf(".");
						if (pot != -1) {
							body = date.getTime() + "";
							ext = name.substring(pot);
						} else {
							body = date.getTime() + "";
							ext = "";
						}
						String newName = body + ext;
						targetFile = new File(uploadTxt, newName);
						if (!targetFile.exists()) {
							targetFile.mkdirs();
						}
						
						files.transferTo(targetFile);
						ur = uploadTxt + File.separator+ newName;
						 
					}
	
					// 拿到路径
					List<BankCarStaging> listExcel = CarStagingService.getAllByExcel(ur);
	
					if (listExcel == null || listExcel.size() == 0) {
						errorInfo = "error";
					}else{
						BankCarStaging stuEntity1 = listExcel.get(0);
						String carStagingCityCode1 = stuEntity1.getCarStagingCityCode();
						if(carStagingCityCode1!=null&&(!"".equals(carStagingCityCode1))){
							//查询城市编码是否正确
							int carStagingCityCodeNum = bankCarStagingService.getBankCarStagingCityByCode(stuEntity1);
							if(carStagingCityCodeNum == 0){
								badNum = 1;
								errorInfo = "codeError";
							}else{
								BankCarStaging tmp = new BankCarStaging();
								tmp.setCarStagingCityCode(carStagingCityCode1);
								int bankCarStaging = bankCarStagingService.getBankCarStagingByCode(tmp);
								if(bankCarStaging >0){
									bankCarStagingService.deleteBankCarStagingByCode(carStagingCityCode1);
								}
								String sqlstart = "insert into bank_car_staging (CAR_STAGING_CITY,CAR_STAGING_LOCATION,CAR_STAGING_BRAND,MARKETING_NAME,CAR_STAGING_PHONE,CAR_STAGING_CITY_CODE) values ";
								String sqlend = ";";
								String str = "";
								String sql = "";
								for (int i = 0; i < listExcel.size(); i++) {
									badNum++;
									BankCarStaging stuEntity = listExcel.get(i);
									BankCarStaging sto = new BankCarStaging();
									String carStagingCity = stuEntity.getCarStagingCity();
									String carStagingBrand = stuEntity.getCarStagingBrand();
									String marketingName = stuEntity.getMarketingName();
									String carStagingLocation = stuEntity.getCarStagingLocation();
									String carStagingPhone = stuEntity.getCarStagingPhone();
									String carStagingCityCode = stuEntity.getCarStagingCityCode();
									if(carStagingCityCode!=null&&(!"".equals(carStagingCityCode))){
										if(!carStagingCityCode.equals(carStagingCityCode1)){
											errorInfo = "notSame";
											break;
										}
										sto.setCarStagingCityCode(carStagingCityCode);
										//查询城市编码是否正确
										carStagingCityCodeNum = bankCarStagingService.getBankCarStagingCityByCode(sto);
										if(carStagingCityCodeNum==0){
											errorInfo = "codeError";
											break;
										}else{
											//判断城市编码是否为空
											if(carStagingCityCode!=null&&(!"".equals(carStagingCityCode))&&carStagingPhone!=null&&(!"".equals(carStagingPhone))&&carStagingBrand!=null&&(!"".equals(carStagingBrand))
													&&marketingName!=null&&(!"".equals(marketingName))){
												sto.setCarStagingBrand(carStagingBrand);
												sto.setMarketingName(marketingName);
												sto.setCarStagingPhone(carStagingPhone);
												sto.setCarStagingCityCode(carStagingCityCode);
												int bankCarStagingNum = bankCarStagingService.getBankcarStagingNum(sto);
												if(bankCarStagingNum>0){
													errorInfo = "repeat";
													break;
												}
												String str1 ="('"+carStagingCity+"','"+carStagingLocation+"','"+carStagingBrand+"','"+marketingName+"','"+carStagingPhone+"','"+carStagingCityCode+"'),";
												String str2 ="','"+carStagingBrand+"','"+marketingName+"','"+carStagingPhone+"','"+carStagingCityCode+"'),";
												if(str.indexOf(str2)==-1){
													str = str+str1;
												}else{
													errorInfo = "repeat";
													break;
												}
												if((i+1)%num==0){
													str = str.substring(0,str.length()-1);
													sql = sqlstart+str+sqlend;
													DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
													Connection conn=datasource.getConnection();
												    Statement stmt = conn.createStatement(); 
												    stmt.addBatch(sql); 
												    stmt.executeBatch();//执行批处理 
												    stmt.close(); 
													str = "";
												}
											}else{
												continue;
											}
										}
									}
								}
								if("".equals(errorInfo)){
									if(str.length() != 0){
										str = str.substring(0,str.length()-1);
										sql = sqlstart+str+sqlend;
										DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
										Connection conn=datasource.getConnection();
										Statement stmt = conn.createStatement(); 
										stmt.addBatch(sql); 
										stmt.executeBatch();//执行批处理 
										stmt.close(); 
										str = "";
									}
									errorInfo = "success";
								}
								
							}
						}else{
							badNum = 1;
							errorInfo = "empty";
						}
					}
				}
			}
			} catch (Exception e) {
				errorInfo = "fail";
			} finally {
				targetFile.delete();
			}
		
		//返回前端数据
		Map<String,Object> data = new HashMap<String, Object>();
		data.put("errorInfo", errorInfo);
		data.put("badNum",badNum);
		System.out.println(errorInfo);
		PrintWriter pt = null;
		JSONObject json = JSONObject.fromObject(data);
		String jsonStr = json.toString();
		try {
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("utf-8");
			pt = response.getWriter();
			pt.write(jsonStr);
			pt.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(pt != null){
				pt.close();
			}
		}

	}

}