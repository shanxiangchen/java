package com.app.listener;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.app.entity.Shop;


public class ShopExcelView extends AbstractExcelView{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMdd");
		HSSFSheet sheet;
		HSSFCell cell;
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("特惠商户");
		
		List<String> titles = (List<String>) model.get("titles");
		int len = titles.size();
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
		for(int i=0; i<len; i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		sheet.getRow(0).setHeight(height);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<Shop> shopList = (List<Shop>) model.get("shopList");
		
		int shopCount = shopList.size();
		for(int i=0; i<shopCount; i++){
			Shop shop = shopList.get(i);
			
			String oddsshopid = shop.getOddsshopid();
			cell = getCell(sheet, i+1, 0);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopid);
		
			String oddsshopname = shop.getOddsshopname();
			cell = getCell(sheet, i+1, 1);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopname);
			
			String oddsshopaddress = shop.getOddsshopaddress();
			cell = getCell(sheet, i+1, 2);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopaddress);
			
			String longitude = shop.getOddsshoplongitude()==null?"":shop.getOddsshoplongitude();
			String oddsshoplongitude=String.valueOf(longitude);
			cell = getCell(sheet, i+1, 3);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshoplongitude);
			
			
			String oddsshop = shop.getOddsshoplatitude()==null?"":shop.getOddsshoplatitude();
			String oddsshoplatitude=String.valueOf(oddsshop);
			cell = getCell(sheet, i+1, 4);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshoplatitude);
			
			
			String oddsshopphone = shop.getOddsshopphone();
			cell = getCell(sheet, i+1, 5);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopphone);
			
			String cityname = shop.getGprs().getCityname()!=null ? shop.getGprs().getCityname() : "";
			cell = getCell(sheet, i+1, 6);
			cell.setCellStyle(contentStyle);
			setText(cell,cityname);
			
			
	       String oddsshoporder = shop.getOddsshoporder()!=null?shop.getOddsshoporder().toString():"";
			cell = getCell(sheet, i+1, 7);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshoporder);
			
			String oddsshopring = shop.getGprs().getShopringname()!=null ? shop.getGprs().getShopringname():"";
			cell = getCell(sheet, i+1, 8);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopring);
			
			String oddsshopstatus = shop.getOddsshopstatus()!=null?shop.getOddsshopstatus().toString():"";
			cell = getCell(sheet, i+1, 9);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopstatus);
			
			
			String photourl = shop.getShopphoto().getPhotourl()!=null ? shop.getShopphoto().getPhotourl() :"";
			cell = getCell(sheet, i+1, 10);
			cell.setCellStyle(contentStyle);
			setText(cell,photourl);
			
			 
			String tShoppraise =shop.getShoppraise().getClickradixcount()!=null  ? shop.getShoppraise().getClickradixcount().toString():"";
			cell = getCell(sheet, i+1, 11);
			cell.setCellStyle(contentStyle);
			setText(cell,tShoppraise);
			
			String oddsshoptype = shop.getType()!=null ? shop.getType().getOddsshoptype() :"";
			cell = getCell(sheet, i+1, 12);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshoptype);
			
			
			String oddsshopconsumption = shop.getOddsshopconsumption();
			cell = getCell(sheet, i+1, 13);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopconsumption);
			
			String oddsshopsubbranchname = shop.getOddsshopsubbranchname();
			cell = getCell(sheet, i+1, 14);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopsubbranchname);
			
			
			String oddsshopfavorableinfo = shop.getOddsshopfavorableinfo();
			cell = getCell(sheet, i+1, 15);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopfavorableinfo);
			
			String oddsshopendtime = shop.getOddsshopendtime()!=null ? shop.getOddsshopendtime() : "";
			cell = getCell(sheet, i+1, 16);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopendtime);
			
			
			String oddsshopbriefintroduction = shop.getOddsshopbriefintroduction();
			cell = getCell(sheet, i+1, 17);
			cell.setCellStyle(contentStyle);
			setText(cell,oddsshopbriefintroduction);
			
			
			
		}
		
	}

}
