package com.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel {
	
	public static void main(String[] args) throws Exception {
		//readExcel();
		writeExcel();
	}

    /**
    * 读取Excel测试，兼容 Excel 2003/2007/2010
    */
   public static void readExcel()
   {
       SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
       try {
           //同时支持Excel 2003、2007、2010
           File excelFile = new File("F:/Excel/ReadExcel.xls"); //创建文件对象
           FileInputStream is = new FileInputStream(excelFile); //文件流
           Workbook workbook = WorkbookFactory.create(is); //这种方式 Excel 2003/2007/2010 都是可以处理的
           int sheetCount = workbook.getNumberOfSheets();  //Sheet的数量
           //遍历每个Sheet
           for (int s = 0; s < sheetCount; s++) {
               Sheet sheet = workbook.getSheetAt(s);
               int rowCount = sheet.getPhysicalNumberOfRows(); //获取总行数
               //遍历每一行
               for (int r = 0; r < rowCount; r++) {
                   Row row = sheet.getRow(r);
                   int cellCount = row.getPhysicalNumberOfCells(); //获取总列数
                   //遍历每一列
                   for (int c = 0; c < cellCount; c++) {
                       Cell cell = row.getCell(c);
                       int cellType = cell.getCellType();
                       String cellValue = null;
                       switch(cellType) {
                           case Cell.CELL_TYPE_STRING: //文本
                               cellValue = cell.getStringCellValue();
                               break;
                           case Cell.CELL_TYPE_NUMERIC: //数字、日期
                               if(DateUtil.isCellDateFormatted(cell)) {
                                   cellValue = fmt.format(cell.getDateCellValue()); //日期型
                               }
                               else {
                                   cellValue = String.valueOf(cell.getNumericCellValue()); //数字
                               }
                               break;
                           case Cell.CELL_TYPE_BOOLEAN: //布尔型
                               cellValue = String.valueOf(cell.getBooleanCellValue());
                               break;
                           case Cell.CELL_TYPE_BLANK: //空白
                               cellValue = cell.getStringCellValue();
                               break;
                           case Cell.CELL_TYPE_ERROR: //错误
                               cellValue = "错误";
                               break;
                           case Cell.CELL_TYPE_FORMULA: //公式
                               cellValue = "错误";
                               break;
                           default:
                               cellValue = "错误";
                       }
                       System.out.print(cellValue + "    ");
                   }
                   System.out.println();
               }
           }
       }
       catch (Exception e) {
           e.printStackTrace();
       }
       System.out.println("读取完毕！");
   }
   
   public static void writeExcel()
   {
       try {
           //同时支持Excel 2003、2007、2010
           FileOutputStream fs = new FileOutputStream("F:/Excel/WriteExcel.xlsx");
           XSSFWorkbook book = new XSSFWorkbook();
           XSSFSheet sheet = book.createSheet();
           XSSFRow row = null;
           for(int i=0;i<10;i++){
        	   row = sheet.createRow(i);
        	   for(int j=0;j<10;j++){
        		   XSSFCell cell = row.createCell(j);
        		   cell.setCellType(Cell.CELL_TYPE_STRING);
        		   cell.setCellValue("0123456789");
        	   }
           }
           book.write(fs);
           fs.close();
           
       }catch(Exception e){
    	   e.printStackTrace();
       }
       System.out.println("写入完毕！");
   }
      
}
