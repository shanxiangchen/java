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

import com.app.entity.ActivityApplyList;

public class ActivitApplyLitstExcelView extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMdd");
		HSSFSheet sheet;
		HSSFCell cell;
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("活动报名名单");
		
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
		List<ActivityApplyList> activityApplyList = (List<ActivityApplyList>) model.get("activityApplyList");
		
		int activityApplyListCount = activityApplyList.size();
		for(int i=0; i<activityApplyListCount; i++){
			ActivityApplyList activityApply = activityApplyList.get(i);
			
			String activityApplyListId = activityApply.getActivityApplyListId();
			cell = getCell(sheet, i+1, 0);
			cell.setCellStyle(contentStyle);
			setText(cell,activityApplyListId);
			
			String activityApplyListPhone = activityApply.getActivityApplyListPhone();
			cell = getCell(sheet, i+1, 1);
			cell.setCellStyle(contentStyle);
			setText(cell,activityApplyListPhone);
			
		
		
			String activityTitle = activityApply.getMarketActivity().getActivityTitle()!=null ? activityApply.getMarketActivity().getActivityTitle() : "";
			cell = getCell(sheet, i+1, 2);
			cell.setCellStyle(contentStyle);
			setText(cell,activityTitle);
			
			
			
			String activityStartDate = activityApply.getMarketActivity().getActivityStartDate()!=null ? activityApply.getMarketActivity().getActivityStartDate():"";
			cell = getCell(sheet, i+1, 3);
			cell.setCellStyle(contentStyle);
			setText(cell,activityStartDate);
			
			String activityEndDate = activityApply.getMarketActivity().getActivityEndDate().toString()!=null ? activityApply.getMarketActivity().getActivityEndDate().toString():"";
			cell = getCell(sheet, i+1, 4);
			cell.setCellStyle(contentStyle);
			setText(cell,activityEndDate);
	}
	}
}
