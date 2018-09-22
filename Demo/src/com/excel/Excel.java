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
    * ��ȡExcel���ԣ����� Excel 2003/2007/2010
    */
   public static void readExcel()
   {
       SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
       try {
           //ͬʱ֧��Excel 2003��2007��2010
           File excelFile = new File("F:/Excel/ReadExcel.xls"); //�����ļ�����
           FileInputStream is = new FileInputStream(excelFile); //�ļ���
           Workbook workbook = WorkbookFactory.create(is); //���ַ�ʽ Excel 2003/2007/2010 ���ǿ��Դ����
           int sheetCount = workbook.getNumberOfSheets();  //Sheet������
           //����ÿ��Sheet
           for (int s = 0; s < sheetCount; s++) {
               Sheet sheet = workbook.getSheetAt(s);
               int rowCount = sheet.getPhysicalNumberOfRows(); //��ȡ������
               //����ÿһ��
               for (int r = 0; r < rowCount; r++) {
                   Row row = sheet.getRow(r);
                   int cellCount = row.getPhysicalNumberOfCells(); //��ȡ������
                   //����ÿһ��
                   for (int c = 0; c < cellCount; c++) {
                       Cell cell = row.getCell(c);
                       int cellType = cell.getCellType();
                       String cellValue = null;
                       switch(cellType) {
                           case Cell.CELL_TYPE_STRING: //�ı�
                               cellValue = cell.getStringCellValue();
                               break;
                           case Cell.CELL_TYPE_NUMERIC: //���֡�����
                               if(DateUtil.isCellDateFormatted(cell)) {
                                   cellValue = fmt.format(cell.getDateCellValue()); //������
                               }
                               else {
                                   cellValue = String.valueOf(cell.getNumericCellValue()); //����
                               }
                               break;
                           case Cell.CELL_TYPE_BOOLEAN: //������
                               cellValue = String.valueOf(cell.getBooleanCellValue());
                               break;
                           case Cell.CELL_TYPE_BLANK: //�հ�
                               cellValue = cell.getStringCellValue();
                               break;
                           case Cell.CELL_TYPE_ERROR: //����
                               cellValue = "����";
                               break;
                           case Cell.CELL_TYPE_FORMULA: //��ʽ
                               cellValue = "����";
                               break;
                           default:
                               cellValue = "����";
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
       System.out.println("��ȡ��ϣ�");
   }
   
   public static void writeExcel()
   {
       try {
           //ͬʱ֧��Excel 2003��2007��2010
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
       System.out.println("д����ϣ�");
   }
      
}
