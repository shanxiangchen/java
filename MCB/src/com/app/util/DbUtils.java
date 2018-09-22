package com.app.util;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
 
  
//import org.apache.log4j.Logger;  
  
/** 
 * 数据库操作辅助类 
 */  
public class DbUtils {  
      
    private static Logger logger = Logger.getLogger("DbUtils");  
        
    //private static DataSource ds ;
 

	/** 
     * 该语句必须是一个 SQL INSERT、UPDATE 或 DELETE 语句 
     * @param sql 
     * @param paramList：参数，与SQL语句中的占位符一一对应 
     * @return 
     * @throws Exception 
     */  
    public int execute(String sql, List<Object> paramList,Connection conn) throws Exception {  
        if(sql == null || sql.trim().equals("")) {  
            logger.info("parameter is valid!");  
        }  
        //conn.setAutoCommit(false);
        PreparedStatement pstmt = null;  
        int result = 0;  
        try {  
            pstmt = DbUtils.getPreparedStatement(conn, sql);  
            setPreparedStatementParam(pstmt, paramList);  
            if(pstmt == null) {  
                return -1;  
            }  
            result = pstmt.executeUpdate();  
            conn.commit();
        } catch (Exception e) {  
            logger.info(e.getMessage());
        	//conn.rollback();
            throw new Exception(e);  
        } finally {  
            closeStatement(pstmt);  
            //closeConn(conn);  
            //pool.returnConnection(conn);
        }  
  
        return result;  
    }  
    
    /** 
     * 批量执行 ，该语句必须是一个 SQL INSERT、UPDATE 或 DELETE 语句 
     * @param sql 
     * @param paramList：参数，与SQL语句中的占位符一一对应 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")
	public int[]  executeBatch(String sql, List<Object> list,Connection conn) throws Exception {  
        if(sql == null || sql.trim().equals("")) {  
            logger.info("parameter is valid!");  
        }  
        PreparedStatement pstmt = null;  
        int[]  result = null;  
        try {  
            pstmt = DbUtils.getPreparedStatement(conn, sql);  
            if(pstmt == null) {  
                return result;  
            }  
            for(int i=0;i<list.size();i++){
            	List<Object> paramList=(List<Object>) list.get(i);
            	setPreparedStatementParam(pstmt, paramList);  
    			//积攒
            	pstmt.addBatch();
    			//当积攒到20条时，就执行一次，并清空先前积攒的SQL
			    if((i+1)%20==0){
			    	pstmt.executeBatch();
			    	pstmt.clearBatch();
				}
    		}
    		//若总数不为执行的整数倍时，再执行一次
			if(list.size()%20!=0){
				result=pstmt.executeBatch();
				pstmt.clearBatch();
			}
            
        } catch (Exception e) {  
            logger.info(e.getMessage());
        	//conn.rollback();
            throw new Exception(e);  
        } finally {  
            closeStatement(pstmt);  
            //closeConn(conn);  
            //pool.returnConnection(conn);
        }  
  
        return result;  
    }  
      
    /** 
     * 将查询数据库获得的结果集转换为Map对象 
     * @param sql：查询语句 
     * @param paramList：参数 
     * @return 
     */  
    public List<Map<String, Object>> getQueryList(String sql, List<Object> paramList,Connection conn) throws Exception {  
        if(sql == null || sql.trim().equals("")) {  
            logger.info("parameter is valid!");  
            return null;  
        }  
  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Map<String, Object>> queryList = null;  
        try {  
            pstmt = DbUtils.getPreparedStatement(conn, sql);  
            setPreparedStatementParam(pstmt, paramList);  
            if(pstmt == null) {  
                return null;  
            }  
            rs = getResultSet(pstmt);  
            queryList = getQueryList(rs);  
        } catch (RuntimeException e) {  
            logger.info(e.getMessage());  
            //Utils.logger.info("parameter is valid!  "+e.getMessage()); 
            throw new Exception(e);  
        } finally {  
        	//pool.returnConnection(conn);
            closeResultSet(rs);  
            closeStatement(pstmt);  
            //closeConn(conn);  
        }  
        return queryList;  
    }  
      
    private void setPreparedStatementParam(PreparedStatement pstmt, List<Object> paramList) throws Exception {  
        if(pstmt == null || paramList == null || paramList.isEmpty()) {  
            return;  
        }  
        DateFormat df = DateFormat.getDateTimeInstance();  
        for (int i = 0; i < paramList.size(); i++) {  
            if(paramList.get(i) instanceof Integer) {  
                int paramValue = ((Integer)paramList.get(i)).intValue();  
                pstmt.setInt(i+1, paramValue);  
            } else if(paramList.get(i) instanceof Float) {  
                float paramValue = ((Float)paramList.get(i)).floatValue();  
                pstmt.setFloat(i+1, paramValue);  
            } else if(paramList.get(i) instanceof Double) {  
                double paramValue = ((Double)paramList.get(i)).doubleValue();  
                pstmt.setDouble(i+1, paramValue);  
            } else if(paramList.get(i) instanceof Date) {  
                pstmt.setString(i+1, df.format((Date)paramList.get(i)));  
            } else if(paramList.get(i) instanceof Long) {  
                long paramValue = ((Long)paramList.get(i)).longValue();  
                pstmt.setLong(i+1, paramValue);  
            } else if(paramList.get(i) instanceof String) {  
                pstmt.setString(i+1, (String)paramList.get(i));  
            }  
        }  
       // return;  
    }  
    /**
     * 判断连接是否可用
     */
    public boolean isValid(Connection conn){
    	
    	String sql = "select sysdate from dual";
		
		try {
			getQueryList(sql, null, conn);
		} catch (Exception e) {
			//Utils.logger.error(e.getClass() +" :"+ e);
			return false;
		}
		return true;
		
    	
    }
  
    private static PreparedStatement getPreparedStatement(Connection conn, String sql) throws Exception {  
        if(conn == null || sql == null || sql.trim().equals("")) {  
            return null;  
        }  
        PreparedStatement pstmt = conn.prepareStatement(sql.trim());  
        return pstmt;  
    }  
      
    /** 
     * 获得数据库查询结果集 
     * @param pstmt 
     * @return 
     * @throws SQLException 
     * @throws Exception 
     */  
    private ResultSet getResultSet(PreparedStatement pstmt) throws SQLException  {  
        if(pstmt == null) {  
            return null;  
        }  
        ResultSet rs = pstmt.executeQuery();  
        return rs;  
    }  
      
    /** 
     * @param rs 
     * @return 
     * @throws Exception 
     */  
    private List<Map<String, Object>> getQueryList(ResultSet rs) throws Exception {  
        if(rs == null) {  
            return null;  
        }  
        ResultSetMetaData rsMetaData = rs.getMetaData();  
        int columnCount = rsMetaData.getColumnCount();  
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
        while (rs.next()) {  
            Map<String, Object> dataMap = new HashMap<String, Object>();  
            for (int i = 0; i < columnCount; i++) {  
                dataMap.put(rsMetaData.getColumnName(i+1), rs.getObject(i+1));  
            }  
            dataList.add(dataMap);  
        }  
        return dataList;  
    }  
      
    /** 
     * 关闭数据库连接 
     * @param conn dssddsds
     */  
    public void closeConn(Connection conn) {  
        if(conn == null) {  
            return;  
        }  
        try {  
        	//Utils.logger.info("conn is closed :"+conn.isClosed());
          //  pool.returnConnection(conn);
        	conn.close();
        } catch (SQLException e) {  
            logger.info(e.getMessage());  
        }  
    }  
      
    /** 
     * 关闭 
     * @param stmt 
     */  
    public void closeStatement(Statement stmt) {  
        if(stmt == null) {  
            return;  
        }  
        try {  
            stmt.close();  
        } catch (SQLException e) {  
            logger.info(e.getMessage());  
        }  
    }  
      
    /** 
     * 关闭 
     * @param rs 
     */  
    public void closeResultSet(ResultSet rs) {  
        if(rs == null) {  
            return;  
        }  
        try {  
            rs.close();  
        } catch (SQLException e) {  
            logger.info(e.getMessage());  
        }  
    }  
     /*
      * 获取序列 
      */
    public String getSequence(String seqId,Connection conn){
    
    	
	    PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        List<Map<String, Object>> queryList = null;
        String seq = "";
	    try {
			String sql = "select "+seqId+".nextval from dual";
			pstmt = DbUtils.getPreparedStatement(conn, sql);  
			rs = getResultSet(pstmt);  
			
			queryList = getQueryList(rs);
			//seq = ((BigDecimal)queryList.get(0).get("NEXTVAL")).longValue();
			seq = ((BigDecimal)queryList.get(0).get("NEXTVAL")).toString();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
    	return seq;
    }
	
	
	 /**
	  * 分页语句组装
	  * @param sql 查询语句
	  * @param curPage 当前页码
	  * @param pageRows 每页条数
	  * @return 分页查询语句
	  */
	public String getPageSql(String sql, String curPage, int pageRows) {
		StringBuffer sb = new StringBuffer();
		/*sb.append(" select * From  (Select inside.*, Rownum As row_num From ( ")
				.append(sql).append(" )  inside Where rownum <= ")
				.append(Integer.parseInt(curPage) * pageRows)
				.append(" ) outside where outside.row_num > ")
				.append((Integer.parseInt(curPage) - 1) * pageRows);*/
		sb.append(" select * From  (Select inside.*, Rownum As row_num From ( ")
		.append(sql).append(" )  inside ")
		.append(" ) outside where outside.row_num > ")
		.append((Integer.parseInt(curPage) - 1) * pageRows)
		.append(" and outside.row_num <= ")
		.append(Integer.parseInt(curPage) * pageRows);
		return sb.toString();
	}
	
    /**
	  * 分页语句组装
	  * @param sql 查询语句
	  * @param curPage 当前页码
	  * @param pageRows 每页条数
	  * @return 分页查询语句
	  */
		public String getPageSql(String sql, String curPage, int pageRows,String distance) {
				
				StringBuffer sb = new StringBuffer();
				/*sb.append(" select * From  (Select inside.*, Rownum As row_num From ( ")
						.append(sql).append(" )  inside Where rownum <= ")
						.append(Integer.parseInt(curPage) * pageRows)
						.append(" ) outside where outside.row_num > ")
						.append((Integer.parseInt(curPage) - 1) * pageRows);*/
				sb.append(" select * From  (Select inside.*, Rownum As row_num From ( ")
				.append(sql).append(" )  inside where 1=1 ");
				if(!"".equals(distance)){
					sb.append(distance);
				}		
				sb.append(" ) outside where outside.row_num > ")
				.append((Integer.parseInt(curPage) - 1) * pageRows)
				.append(" and outside.row_num <= ")
				.append(Integer.parseInt(curPage) * pageRows);
				return sb.toString();
			}

	
	/**
	 * 获取查询数据的总条数
	 * @param sql 查询语句
	 * @return
	 */
	public String getTotalCountSql(String sql) {
		StringBuffer sb = new StringBuffer();
		sb.append("select count(1) total from ( ");
		sb.append(sql);
		sb.append(" ) where 1=1 ");
		
		return sb.toString();
	}
	
	
  
}  
