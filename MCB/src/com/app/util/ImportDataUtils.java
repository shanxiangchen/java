package com.app.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.app.listener.Const;

/**
 * 汽车快捷分期导入工具类
 * @author niushaohai
 *
 */
public class ImportDataUtils implements Runnable{
	
	private final Logger log = Logger.getLogger(this.getClass());
	
	private String fileName;
	private String filePath;
	
	public ImportDataUtils(String fileName, String filePath) {
		this.fileName = fileName;
		this.filePath = filePath;
	}
	@Override
	public void run() {
		loadTestAllData();	
	}
	
	public void loadTestAllData(){
		Connection conn=null;
		Statement stmt = null;
		
		DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
		try {
			conn=datasource.getConnection();
			InputStream dataStream = new FileInputStream(filePath+fileName);
			String sql = "load data LOCAL infile '"+fileName+"' "+
						"IGNORE into table bank_speedy_list "+
						"character set utf8 fields terminated by ',' IGNORE 1 LINES"+
						"(NAME,PHONE,NO_MORTGAGE_CAR_STAGING,NO_MORTGAGE_DIRECT_STAGING,MORTGAGE_CAR_STAGING,MORTGAGE_DIRECT_STAGING)";
			int i = bulkLoadFromInputStream(sql,dataStream,conn) ;
			log.info("汽车快捷分期名单导入成功：本次共计导入数据 "+i+"条！");
		} catch (SQLException e) {
			e.printStackTrace();
			log.error("汽车快捷分期名单导入失败： "+e);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			log.error("汽车快捷分期名单导入失败： "+e);
		} finally {

			try {
				if (conn != null) {
					conn.close();
				}
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
				log.error("汽车快捷分期名单导入失败： "+e);
			}

		}
	}
	
	public int bulkLoadFromInputStream(String loadDataSql,  
            InputStream dataStream,Connection conn) throws SQLException {  
        if(dataStream==null){  
        	log.error("汽车快捷分期名单导入失败： 文件内容为空！"); 
            return 0;  
        }  
        PreparedStatement statement = conn.prepareStatement(loadDataSql);  
  
        int result = 0;  
  
        if (statement.isWrapperFor(com.mysql.jdbc.Statement.class)) {  
  
            com.mysql.jdbc.PreparedStatement mysqlStatement = statement.unwrap(com.mysql.jdbc.PreparedStatement.class);  
  
            mysqlStatement.setLocalInfileInputStream(dataStream);  
            result = mysqlStatement.executeUpdate();  
            statement.close();
        }  
        return result;  
    } 
	
}
