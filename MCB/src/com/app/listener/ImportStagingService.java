package com.app.listener;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.app.entity.Common;
import com.app.entity.StagingList;


/**
 * @author zh
 * 
 */
public class ImportStagingService {
	 
	/**
	 * 查询指定目录中电子表格中所有的数据
	 * @param file 文件完整路径
	 * @return
	 */
	public static List<StagingList> getAllByExcel(String path){
		 
		 List<StagingList> list=new ArrayList<StagingList>();
		 try {
				String postfix =getPostfix(path);
		        if (!Common.EMPTY.equals(postfix)) {
		            if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
		            	return readXls(path);
		            } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
							return readXlsx(path);
		            }
		        } /*else {
		        	log.debug(path + Common.NOT_EXCEL_FILE);
		            //System.out.println(path + Common.NOT_EXCEL_FILE);
		        }*/
			 
		 } catch (IOException e) {
				e.printStackTrace();
		}
		 return list;

		
	}
	
	public static String getPostfix(String path) {
        if (path == null || Common.EMPTY.equals(path.trim())) {
            return Common.EMPTY;
        }
        if (path.contains(Common.POINT)) {
            return path.substring(path.lastIndexOf(Common.POINT) + 1, path.length());
        }
        return Common.EMPTY;
    }
	
	/**
	  * 读取excel2003-2007
	  * @param path
	  * @return
	  * @throws IOException
	  */
	 public static List<StagingList> readXls(String path) throws IOException {
		  
		   List<StagingList> list = new ArrayList<StagingList>();
		   InputStream is =null;
		   try {
			   is = new FileInputStream(path);
	           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	           StagingList staging = null;
	          
	           HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
	           HSSFRow rows = hssfSheet.getRow(0);
	           int columnNum=rows.getPhysicalNumberOfCells();//获取总列数
	           if(columnNum<10){
	           	return null;
	           }
	           for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	               HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	               
	               if (hssfRow != null) {
	            	   staging = new StagingList();
	                   // XSSFCell cityName=xssfRow.getCell(0);//城市名称
	            	   HSSFCell city=hssfRow.getCell((short) 1);
	            	   HSSFCell name=hssfRow.getCell((short) 2);
	            	   HSSFCell phone=hssfRow.getCell((short) 3);
	            	   HSSFCell creditCardLimit=hssfRow.getCell((short) 4);
	            	   HSSFCell noMortgageLargeStaging=hssfRow.getCell((short) 5);
	            	   HSSFCell noMortgageCarStaging=hssfRow.getCell((short) 6);
	            	   HSSFCell noMortgageDirectStaging=hssfRow.getCell((short) 7);
	            	   HSSFCell mortgageCarStaging=hssfRow.getCell((short) 8);
	            	   HSSFCell mortgageDirectStaging=hssfRow.getCell((short) 9);
	            	   HSSFCell expiryDate=hssfRow.getCell((short) 9);
	            	   
	                    staging.setCity(getValue(city));
	                    staging.setName(getValue(name));
	                    staging.setPhone(getValue(phone));
	                    staging.setCreditCardLimit(getValue(creditCardLimit));
	                    staging.setNoMortgageLargeStaging(getValue(noMortgageLargeStaging));
	                    staging.setNoMortgageCarStaging(getValue(noMortgageCarStaging));
	                    staging.setNoMortgageDirectStaging(getValue(noMortgageDirectStaging));
	                    staging.setMortgageCarStaging(getValue(mortgageCarStaging));
	                    staging.setMortgageDirectStaging(getValue(mortgageDirectStaging));
	                    staging.setExpiryDate(getValue(expiryDate));

	                    list.add(staging);
	               }
	           }
	     
			
		   } catch (Exception e) {
			 e.printStackTrace();
		   }finally{
			    is.close();
		   }         
           
           return list;
	 }
		   
	 
	/**
	 * 读取excel2010
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<StagingList> readXlsx(String path) throws IOException {
		 List<StagingList> list = new ArrayList<StagingList>();
		  
		try {
        	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);                
        	StagingList staging = null;
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            XSSFRow rows = xssfSheet.getRow(0);
            int columnNum=rows.getPhysicalNumberOfCells();//获取总列数
            if(columnNum<10){
            	return null;
            }
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
                	staging = new StagingList();
                    
                    XSSFCell city=xssfRow.getCell(1);
                    XSSFCell name=xssfRow.getCell(2);
                    XSSFCell phone=xssfRow.getCell(3);
                    XSSFCell creditCardLimit=xssfRow.getCell(4);
                    XSSFCell noMortgageLargeStaging=xssfRow.getCell(5);
                    XSSFCell noMortgageCarStaging=xssfRow.getCell(6);
                    XSSFCell noMortgageDirectStaging=xssfRow.getCell(7);
                    XSSFCell mortgageCarStaging=xssfRow.getCell(8);
                    XSSFCell mortgageDirectStaging=xssfRow.getCell(9);
                    XSSFCell expiryDate=xssfRow.getCell(9);
                    
                    staging.setCity(getValue(city));
                    staging.setName(getValue(name));
                    staging.setPhone(getValue(phone));
                    staging.setCreditCardLimit(getValue(creditCardLimit));
                    staging.setNoMortgageLargeStaging(getValue(noMortgageLargeStaging));
                    staging.setNoMortgageCarStaging(getValue(noMortgageCarStaging));
                    staging.setNoMortgageDirectStaging(getValue(noMortgageDirectStaging));
                    staging.setMortgageCarStaging(getValue(mortgageCarStaging));
                    staging.setMortgageDirectStaging(getValue(mortgageDirectStaging));
                    staging.setExpiryDate(getValue(expiryDate));

                    list.add(staging);
                }
            }
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
       
        return list;
    }
	
	 
	 @SuppressWarnings("static-access")
	private static String getValue(XSSFCell xssfRow) {
		   if(xssfRow==null){
			   return "";
		   }
	       if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
	           return String.valueOf(xssfRow.getBooleanCellValue()); 
	       } else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
	            //return String.valueOf(xssfRow.getNumericCellValue());
	    	    // 返回数值类型的值
	            String  inputValue = null;// 单元格值
	            Long longVal = Math.round(xssfRow.getNumericCellValue());
	            Double doubleVal = xssfRow.getNumericCellValue();
	            if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
	                inputValue = longVal.toString();
	            }
	            else{
	                inputValue = doubleVal.toString();
	            }
	            return inputValue; 
	       } else {
	           return String.valueOf(xssfRow.getStringCellValue());
	       }
	 }

	
	 
   
   @SuppressWarnings({ "deprecation", "static-access" })
   private static String getValue(HSSFCell hssfCell) {
	       if(hssfCell==null){
	    	   return "";
	       }
           if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
               return String.valueOf(hssfCell.getBooleanCellValue());
           } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
                //return String.valueOf(hssfCell.getNumericCellValue());
        	   // DecimalFormat df = new DecimalFormat("#.####");    //格式化为四位小数，按自己需求选择；
               //return String.valueOf(df.format(inputValue));      //返回String类型
        	    // 返回数值类型的值
	            String  inputValue = null;// 单元格值
	            Long longVal = Math.round(hssfCell.getNumericCellValue());
	            Double doubleVal = hssfCell.getNumericCellValue();
	            if(Double.parseDouble(longVal + ".0") == doubleVal){   //判断是否含有小数位.0
	                inputValue = longVal.toString();
	            }
	            else{
	                inputValue = doubleVal.toString();
	            }
	            return inputValue; 
           } else {
               return String.valueOf(hssfCell.getStringCellValue());
           }
   }
}
	