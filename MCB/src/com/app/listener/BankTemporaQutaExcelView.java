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

import com.app.entity.BankTemporaryQuota;

public class BankTemporaQutaExcelView extends AbstractExcelView {
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response)
			throws Exception {
		 
		Date date = new Date();
		String filename = Tools.date2Str(date, "yyyyMMdd");
		HSSFSheet sheet;
		HSSFCell cell;
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename="+filename+".xls");
		sheet = workbook.createSheet("临额转人工记录");
		
		List<String> titles = (List<String>) model.get("titles");
		 
		HSSFCellStyle headerStyle = workbook.createCellStyle(); //标题样式
		headerStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		headerStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont headerFont = workbook.createFont();	//标题字体
		headerFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		headerFont.setFontHeightInPoints((short)11);
		headerStyle.setFont(headerFont);
		short width = 20,height=25*20;
		sheet.setDefaultColumnWidth(width);
		for(int i=0; i<titles.size(); i++){ //设置标题
			String title = titles.get(i);
			cell = getCell(sheet, 0, i);
			cell.setCellStyle(headerStyle);
			setText(cell,title);
		}
		sheet.getRow(0).setHeight(height);
		
		HSSFCellStyle contentStyle = workbook.createCellStyle(); //内容样式
		contentStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		List<BankTemporaryQuota> list = (List<BankTemporaryQuota>) model.get("list");
		 
		for(int i=0; i< list.size(); i++){
			BankTemporaryQuota temporaryQuota = list.get(i);
			
			String applyDate = temporaryQuota.getApplyDate();//申请日期
			cell = getCell(sheet, i+1, 0);
			cell.setCellStyle(contentStyle);
			setText(cell,applyDate);
			 
		
			String aplyTime = temporaryQuota.getApplyTime();//申请时间
			cell = getCell(sheet, i+1, 1);
			cell.setCellStyle(contentStyle);
			setText(cell,aplyTime);
			
			
			
			String cardNo = temporaryQuota.getCardNo();//卡号
			cell = getCell(sheet, i+1, 2);
			cell.setCellStyle(contentStyle);
			setText(cell,cardNo);
			
			
			String expectAmount = temporaryQuota.getExpectAmount();//期望调升后的额度
			cell = getCell(sheet, i+1, 3);
			cell.setCellStyle(contentStyle);
		    setText(cell,expectAmount==null?"":expectAmount.toString());
			 
			
			
			String effectiveDate = temporaryQuota.getEffectiveDate();//临时额度生效日期
			cell = getCell(sheet, i+1, 4);
			cell.setCellStyle(contentStyle);
			setText(cell,effectiveDate);
			 
			String expirationDate = temporaryQuota.getExpirationDate();//临时额度失效日期
			cell = getCell(sheet, i+1, 5);
			cell.setCellStyle(contentStyle);
			setText(cell,expirationDate);
			
			String usedType = temporaryQuota.getUsedTypeName();//临时额度用途
			cell = getCell(sheet, i+1, 6);
			cell.setCellStyle(contentStyle);
			setText(cell,usedType);
			
			String decisionResult = temporaryQuota.getDecisionResult();//账务决策结果（A或R）
			cell = getCell(sheet, i+1, 7);
			cell.setCellStyle(contentStyle);
			setText(cell,decisionResult);
			
			String adjustableAmount = temporaryQuota.getAdjustableAmount();//可调整金额
			cell = getCell(sheet, i+1, 8);
			cell.setCellStyle(contentStyle);
			setText(cell,adjustableAmount);
	     }
	}
}
