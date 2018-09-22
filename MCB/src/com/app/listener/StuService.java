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
import com.app.entity.StoreStages;


/**
 * @author Javen
 * @Email zyw205@gmail.com
 * 
 */
public class StuService {
	 
	/**
	 * 查询指定目录中电子表格中所有的数据
	 * @param file 文件完整路径
	 * @return
	 */
	public static List<StoreStages> getAllByExcel(String path){
		 
		 List<StoreStages> list=new ArrayList<StoreStages>();
		 try {
				String postfix =getPostfix(path);
		        if (!Common.EMPTY.equals(postfix)) {
		            if (Common.OFFICE_EXCEL_2003_POSTFIX.equals(postfix)) {
		            	return readXls(path);
		            } else if (Common.OFFICE_EXCEL_2010_POSTFIX.equals(postfix)) {
					  return readXlsx(path);
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
	 public static List<StoreStages> readXls(String path) throws IOException {
		  
		   List<StoreStages> list = new ArrayList<StoreStages>();
		   InputStream is =null;
		   try {
			   is = new FileInputStream(path);
	           HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
	           StoreStages store = null;
	          
	           HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(0);
	           HSSFRow rows = hssfSheet.getRow(0);
	           int columnNum=rows.getPhysicalNumberOfCells();//获取总列数
	           if(columnNum<10){
	           	return null;
	           }
	           for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
	               HSSFRow hssfRow = hssfSheet.getRow(rowNum);
	               
	               if (hssfRow != null) {
	            	   store = new StoreStages();
	                   // XSSFCell cityName=xssfRow.getCell(0);//城市名称
	                    HSSFCell cityNo=hssfRow.getCell((short) 1);//城市编码
	                    HSSFCell storeNo=hssfRow.getCell((short) 2);//商户编号
	                    HSSFCell storeName=hssfRow.getCell((short) 3);//商户名称
	                    HSSFCell storeAddr=hssfRow.getCell((short) 4);//商户地址
	                    HSSFCell fee3=hssfRow.getCell((short) 5);//3期费率
	                    HSSFCell fee6=hssfRow.getCell((short) 6);//6期费率
	                    HSSFCell fee12=hssfRow.getCell((short) 7);//12期费率
	                    HSSFCell fee24=hssfRow.getCell((short) 8);//24期费率
	                    HSSFCell tradeName=hssfRow.getCell((short) 9);//行业名称
	                    store.setCityNo(getValue(cityNo));
	                    store.setStoreNo(getValue(storeNo));
	                    store.setStoreName(getValue(storeName));
	                    store.setStoreAddr(getValue(storeAddr));
	                    store.setFee3(getValue(fee3));
	                    store.setFee6(getValue(fee6));
	                    store.setFee12(getValue(fee12));
	                    store.setFee24(getValue(fee24));
	                    store.setTradeName(getValue(tradeName));
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
		   
	 
	/**
	 * 读取excel2010
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static List<StoreStages> readXlsx(String path) throws IOException {
		 List<StoreStages> list = new ArrayList<StoreStages>();
		  
		try {
        	XSSFWorkbook xssfWorkbook = new XSSFWorkbook(path);                
            StoreStages store = null;
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            /*XSSFRow rows = xssfSheet.getRow(0);
            int columnNum=rows.getPhysicalNumberOfCells();//获取总列数
            if(columnNum<10){
            	return null;
            }*/
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow != null) {
             	    store = new StoreStages();
                    
                   // XSSFCell cityName=xssfRow.getCell(0);//城市名称
                    XSSFCell cityNo=xssfRow.getCell(1);//城市编码
                    XSSFCell storeNo=xssfRow.getCell(2);//商户编号
                    XSSFCell storeName=xssfRow.getCell(3);//商户名称
                    XSSFCell storeAddr=xssfRow.getCell(4);//商户地址
                    XSSFCell fee3=xssfRow.getCell(5);//3期费率
                    XSSFCell fee6=xssfRow.getCell(6);//6期费率
                    XSSFCell fee12=xssfRow.getCell(7);//12期费率
                    XSSFCell fee24=xssfRow.getCell(8);//24期费率
                    XSSFCell tradeName=xssfRow.getCell(9);//行业名称
                    store.setCityNo(getValue(cityNo));
                    store.setStoreNo(getValue(storeNo));
                    store.setStoreName(getValue(storeName));
                    store.setStoreAddr(getValue(storeAddr));
                    store.setFee3(getValue(fee3));
                    store.setFee6(getValue(fee6));
                    store.setFee12(getValue(fee12));
                    store.setFee24(getValue(fee24));
                    store.setTradeName(getValue(tradeName));
                    list.add(store);
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
	
	
	 
	/*public static boolean isExist(String  storeStagesId){
		try {
//			DBhepler db=new DBhepler();
			ResultSet rs=db.Search("select * from stu where id=?", new String[]{id+""});
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}*/
   /*		try {
	File files=new File(file);
	FileInputStream in=new FileInputStream(files); 
	Workbook rwb=Workbook.getWorkbook(in);
	Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
	int clos=rs.getColumns();//得到所有的列
	int rows=rs.getRows();//得到所有的行
	
//	System.out.println(clos+" rows:"+rows);
	for (int i = 1; i < rows; i++) {
		for (int j = 1; j < clos; j++) {
			//第一个是列数，第二个是行数
			//String cityName=rs.getCell(j++, i).getContents();//城市名称
			String cityNo=rs.getCell(j++, i).getContents();//城市编码
			String storeName=rs.getCell(j++, i).getContents();//商户名称
			String storeAddr=rs.getCell(j++, i).getContents();//商户地址
			String fee3=rs.getCell(j++, i).getContents();//3期费率
			String fee6=rs.getCell(j++, i).getContents();//6期费率
			String fee12=rs.getCell(j++, i).getContents();//12期费率
			String fee24=rs.getCell(j++, i).getContents();//24期费率
			String tradeName=rs.getCell(j++, i).getContents();//行业名称
			String mayTransactStagesPeriods="";
			
			System.out.println(
					"storeName:"+storeName
					+" storeAddr:"+storeAddr
					+" fee3:"+fee3
					+" fee6:"+fee6
					+" fee12:"+fee12
					+" fee24:"+fee24
					+" cityNo:"+cityNo
					+" mayTransactStagesPeriods:"+mayTransactStagesPeriods
					+" tradeName:"+tradeName
					);
			list.add(new StoreStages(storeName,storeAddr,fee3,fee6,fee12,fee24,cityNo,mayTransactStagesPeriods,tradeName));
		}
	}
} catch (Exception e) {
	e.printStackTrace();
}*/ 

	
}