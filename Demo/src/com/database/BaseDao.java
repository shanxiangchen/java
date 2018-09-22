package com.database;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Map;

public class BaseDao {
	
	protected  String tableName = "";

	public String getSelectSQL(Object obj,Map condition) {
		StringBuffer bf = new StringBuffer();
		try {
			int position = obj.getClass().toString().indexOf("com");
			String cpath = obj.getClass().toString().substring(position);
			bf.append("select ");
			Class cc = Class.forName(cpath);
			Field field[] = cc.getDeclaredFields();
			for (int i = 0; i < field.length; i++) {
				String fileName = field[i].getName();
				bf.append(fileName + ",");
			}
			bf.setLength(bf.length() - 1);
			bf.append(" from ");
			bf.append(tableName + " ");
			if (condition != null && condition.size()>0) {
				bf.append("where ");
				Iterator it = condition.entrySet().iterator();
				int count = 0;
				while (it.hasNext()) {
					count++;
					Map.Entry entry = (Map.Entry) it.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					if (count > 1) {
						bf.append("and ");
					}
					if (value == null || "".equals(value.toString()) || "null".equalsIgnoreCase(value.toString())) {
						bf.append(key.toString() + " is null ");
					} else {
						bf.append(key.toString()+"='"+value.toString()+ "' ");
					}
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bf.toString();
	}

	public String getInsertSQL(Object obj) {
		StringBuffer bf = new StringBuffer();
		try {
			int position = obj.getClass().toString().indexOf("com");
			String cpath = obj.getClass().toString().substring(position);
			bf.append("insert into ");
			bf.append(tableName + " (");
			Class cc = Class.forName(cpath);
			Field[] field = cc.getDeclaredFields();
			String[] methods = new String[field.length];
			for (int i = 0; i < field.length; i++) {
				String fileName = field[i].getName();
				bf.append(fileName + ",");
				methods[i] = fileName;
			}
			bf.setLength(bf.length() - 1);
			bf.append(") values (");
			for (int i = 0; i < methods.length; i++) {
				String methodName = "get"+methods[i].substring(0, 1).toUpperCase()+methods[i].substring(1);
				try {
					Method method = cc.getMethod(methodName, null);
					Object returnObject = method.invoke(obj, null);
					String returnType = method.getReturnType().toString();
					String tmpvalue = "";
					if (returnType.indexOf("Date") != -1) {
						if (returnObject != null) {
							java.sql.Date sqlDate = (java.sql.Date) returnObject;
							java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String strDate = sdf.format(utilDate);
							tmpvalue = "to_date('" + strDate + "','yyyy-mm-dd HH24:mi:ss')";
						}
						if (tmpvalue == null || "".equals(tmpvalue) || "null".equalsIgnoreCase(tmpvalue)) {
							bf.append("null,");
						} else {
							bf.append("" + tmpvalue + ",");
						}
					} else {
						if (returnObject != null) {
							tmpvalue = returnObject.toString();
						}
						if (tmpvalue == null || "".equals(tmpvalue) || "null".equalsIgnoreCase(tmpvalue)) {
							bf.append("null,");
						} else {
							bf.append("'" + tmpvalue + "',");
						}
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			bf.setCharAt(bf.length() - 1, ')');
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bf.toString();
	}
	
	public String getUpdateSQL(Object obj,Map condition) {
		StringBuffer bf = new StringBuffer();
		try {
			int position = obj.getClass().toString().indexOf("com");
			String cpath = obj.getClass().toString().substring(position);
			bf.append("update ");
			bf.append(tableName + " ");
			bf.append("set ");
			Class cc = Class.forName(cpath);
			Field[] field = cc.getDeclaredFields();
			String[] methods = new String[field.length];
			for (int i = 0; i < field.length; i++) {
				String fileName = field[i].getName();
				methods[i] = fileName;
			}
			for (int i = 0; i < methods.length; i++) {
				String methodName = "get"+methods[i].substring(0, 1).toUpperCase()+methods[i].substring(1);
				try {
					Method method = cc.getMethod(methodName, null);
					Object returnObject = method.invoke(obj, null);
					String returnType = method.getReturnType().toString();
					String tmpvalue = "";
					if (returnType.indexOf("Date") != -1) {
						if (returnObject != null) {
							java.sql.Date sqlDate = (java.sql.Date) returnObject;
							java.util.Date utilDate = new java.util.Date(sqlDate.getTime());
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String strDate = sdf.format(utilDate);
							tmpvalue = "to_date('" + strDate + "','yyyy-mm-dd HH24:mi:ss')";
						}
						if (tmpvalue == null || "".equals(tmpvalue) || "null".equalsIgnoreCase(tmpvalue)) {
							bf.append(methods[i]+" = null,");
						} else {
							bf.append(methods[i]+"=" + tmpvalue + ",");
						}
					} else {
						if (returnObject != null) {
							tmpvalue = returnObject.toString();
						}
						if (tmpvalue == null || "".equals(tmpvalue) || "null".equalsIgnoreCase(tmpvalue)) {
							bf.append(methods[i]+" = null,");
						} else {
							bf.append(methods[i]+"='" + tmpvalue + "',");
						}
					}
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}
			bf.setLength(bf.length() - 1);
			if (condition != null && condition.size()>0) {
				bf.append(" where ");
				Iterator it = condition.entrySet().iterator();
				int count = 0;
				while (it.hasNext()) {
					count++;
					Map.Entry entry = (Map.Entry) it.next();
					Object key = entry.getKey();
					Object value = entry.getValue();
					if (count > 1) {
						bf.append("and ");
					}
					if (value == null || "".equals(value.toString()) || "null".equalsIgnoreCase(value.toString())) {
						bf.append(key.toString() + " is null ");
					} else {
						bf.append(key.toString()+"='"+value.toString()+ "' ");
					}
				}

			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return bf.toString();
	}
	
	public String getDeleteSQL(Map condition) {
		StringBuffer bf = new StringBuffer();
		bf.append("delete from ");
		bf.append(tableName + " ");
		if (condition != null && condition.size()>0) {
			bf.append("where ");
			Iterator it = condition.entrySet().iterator();
			int count = 0;
			while (it.hasNext()) {
				count++;
				Map.Entry entry = (Map.Entry) it.next();
				Object key = entry.getKey();
				Object value = entry.getValue();
				if (count > 1) {
					bf.append("and ");
				}
				if (value == null || "".equals(value.toString()) || "null".equalsIgnoreCase(value.toString())) {
					bf.append(key.toString() + " is null ");
				} else {
					bf.append(key.toString()+"='"+value.toString()+ "' ");
				}
			}
		}
		return bf.toString();
	}

}
