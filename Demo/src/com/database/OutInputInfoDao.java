package com.database;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class OutInputInfoDao extends BaseDao {
	
	public OutInputInfoDao(){
		super.tableName = "scott.jett_database_test";
	}
	
	public List<OutInputInfo> getResult(Map condition){
		List list = new ArrayList();
		OutInputInfo obj = new OutInputInfo();
		String sql = this.getSelectSQL(obj, condition);
		DBSourceJETT ds = new DBSourceJETT();
		try {
			ResultSet rs = ds.getResult(sql);
			while(rs.next()){
				obj = new OutInputInfo();
				try {
					int position = obj.getClass().toString().indexOf("com");
					String cpath = obj.getClass().toString().substring(position);
					Class cc = Class.forName(cpath);
					Method[] methods = cc.getDeclaredMethods();
					for (int i = 0; i < methods.length; i++) {
						String methodName = methods[i].getName();
						try {
							if (methodName.indexOf("set") == 0) {
								Type[] paramTypes = methods[i].getParameterTypes();
								String paramType = paramTypes[0].toString();
								if(paramType.indexOf("Date")!=-1){
									Timestamp dateTime = rs.getTimestamp(methodName.substring(3));
									if(dateTime!=null){
										methods[i].invoke(obj, new Date(dateTime.getTime()));
									}else{
										methods[i].invoke(obj, (Object)null);
									}
								}else if(paramType.indexOf("double")!=-1 || paramType.indexOf("Double")!=-1){
									methods[i].invoke(obj, rs.getDouble(methodName.substring(3)));
								}else if(paramType.indexOf("int")!=-1 || paramType.indexOf("Integer")!=-1){
									methods[i].invoke(obj, rs.getDouble(methodName.substring(3)));
								}else{
									methods[i].invoke(obj, rs.getString(methodName.substring(3)));
								}
							}
						} catch (SecurityException e) {
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				list.add(obj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
		return list;
	}
	
	public void save(OutInputInfo oii){
		DBSourceJETT ds = new DBSourceJETT();
		String sql = this.getInsertSQL(oii);
		try {
			ds.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
	}
	
	public void update(OutInputInfo oii,Map condition){
		DBSourceJETT ds = new DBSourceJETT();
		String sql = this.getUpdateSQL(oii, condition);
		try {
			ds.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
	}
	
	public void delete(Map condition){
		DBSourceJETT ds = new DBSourceJETT();
		String sql = this.getDeleteSQL(condition);
		try {
			ds.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			ds.close();
		}
	}

}
