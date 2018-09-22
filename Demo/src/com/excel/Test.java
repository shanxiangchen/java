package com.excel;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Test {

	public void readExcel() throws Exception {
		File file = new File("F:/Excel/ReadExcel.xls");
		Workbook wb = WorkbookFactory.create(file);
		Sheet sheet = wb.getSheet("Sheet1");
		for (int i = 0; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			if (row != null) {
				for (int j = 0; j < row.getLastCellNum(); j++) {
					Cell cell = row.getCell(j);
					System.out.print(cell.getStringCellValue() + "  ");
				}
			}
			System.out.println();
		}
		System.out.println("读取完毕！");
	}

	public void creatExcel() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		for(int s = 0; s <= 5; s++){
			HSSFSheet sheet = wb.createSheet("Sheet"+s);
			for (int i = 0; i <= 100; i++) {
				HSSFRow row = sheet.createRow(i);
				for (int j = 0; j <= 100; j++) {
					HSSFCell cell = row.createCell(j);
					cell.setCellValue(100+i+"x"+(j+100));
				}
			}
		}
		FileOutputStream fs = new FileOutputStream("F:/Excel/WriteExcel.xls");
		wb.write(fs);
		fs.close();
		System.out.println("保存完毕！");
	}
	
	public void creatBatchExcel() throws Exception {
		int num = 100;
		while(num<999){
			HSSFWorkbook wb = new HSSFWorkbook();
			for(int s = 0; s <= 5; s++){
				HSSFSheet sheet = wb.createSheet("Sheet"+s);
				for (int i = 0; i <= 100; i++) {
					HSSFRow row = sheet.createRow(i);
					for (int j = 0; j <= 100; j++) {
						HSSFCell cell = row.createCell(j);
						cell.setCellValue(100+i+"x"+(j+100));
					}
				}
			}
			FileOutputStream fs = new FileOutputStream("F:/Excel/WriteExcel"+num+++".xls");
			wb.write(fs);
			fs.close();
			System.out.println("保存完毕！");
		}
	}
}
