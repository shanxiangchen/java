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

import com.app.entity.BankCarStaging;
import com.app.entity.Common;

public class CarStagingService {
	/**
	 * 查询指定目录中电子表格中所有的数据
	 * @param file 文件完整路径
	 * @return
	 */
	public static List<BankCarStaging> getAllByExcel(String path){
		 
		 List<BankCarStaging> list=new ArrayList<BankCarStaging>();
		 try {
				String postfix =getPostfix(path);
		        if (!Common.EMPTY.equals(postfix)) {
		            if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
		            	return readXls(path);
		            } 
		        }
			 
		 } catch (IOException e) {
				// TODO Auto-generated catch block
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
	 public static List<BankCarStaging> readXls(String path) throws IOException {
		  
		   List<BankCarStaging> list = new ArrayList<BankCarStaging>();
		   InputStream is =null;
		   try {
			   is = new FileInputStream(path);
	           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	           BankCarStaging store = null;
	          
	           HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
	           HSSFRow rows = hssfSheet.getRow(0);
	           int columnNum=rows.getPhysicalNumberOfCells();//获取总列数
	           if(columnNum<6){
	           	return null;
	           }
	           for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	               HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	               
	               if (hssfRow != null) {
	            	   store =  new BankCarStaging();
	                    HSSFCell carStagingCity=hssfRow.getCell((short) 0);//城市
	                    HSSFCell carStagingBrand=hssfRow.getCell((short) 1);//品牌
	                    HSSFCell marketingName=hssfRow.getCell((short) 2);//经销商名称
	                    HSSFCell carStagingPhone=hssfRow.getCell((short) 3);//经销商电话
	                    HSSFCell carStagingLocation=hssfRow.getCell((short) 4);//经销商地址
	                    HSSFCell carStagingCityCode=hssfRow.getCell((short) 5);//城市编码
	                    
						store.setCarStagingCity(getValue(carStagingCity));
	                    store.setCarStagingBrand(getValue(carStagingBrand));
	                    store.setMarketingName(getValue(marketingName));
	                    store.setCarStagingPhone(getValue(carStagingPhone));
	                    store.setCarStagingLocation(getValue(carStagingLocation));
	                    store.setCarStagingCityCode(getValue(carStagingCityCode));
	                    list.add(store);
	               }
	           }
		   } catch (Exception e) {
			 e.printStackTrace();
		   }finally{
			    is.close();
		   }         
           
           return list;
	 }
   @SuppressWarnings({ "deprecation", "static-access" })
   private static String getValue(HSSFCell hssfCell) {
	       if(hssfCell==null){
	    	   return "";
	       }
           if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
               return String.valueOf(hssfCell.getBooleanCellValue());
           } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
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
