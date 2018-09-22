package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public abstract class BaseDBSource {
	
	private Connection conn = null;
	protected String url;
	protected String username;
	protected String password;
	protected String jndiName;
		
	protected void getConnection(){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");  
			conn = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			System.out.println("****���ݿ���������ʧ�ܣ�****");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("****��������ʧ�ܣ�****");
			e.printStackTrace();
		}  
	}
	
	protected void getConnection(String jndiName){
		try {
			Context ctx = new InitialContext();
			DataSource ds = (DataSource)ctx.lookup(jndiName);
			conn = ds.getConnection();
		} catch (SQLException e) {
			System.out.println("****��������ʧ�ܣ�****");
			e.printStackTrace();
		} catch (NamingException e) {
			System.out.println("****��JNDI����δ�ҵ���****");
			e.printStackTrace();
		}   
	}
	
	public ResultSet getResult(String sql) throws SQLException{
		Statement stmt = conn.createStatement();
		System.out.println("executeSQL: "+sql);
		return stmt.executeQuery(sql);
	}
	
	public void execute(String sql) throws SQLException{
		Statement stmt = conn.createStatement();
		System.out.println("executeSQL: "+sql);
		stmt.execute(sql);
	}
	
	public void setAutoCommit(boolean flag) throws SQLException{
		conn.setAutoCommit(flag);
	}
	
	public void commit() throws SQLException{
		conn.commit();
	}
	
	public void rollback (){
		if(conn!=null){
			try {
				conn.rollback();
				System.out.println("DBError:���� ����ع���");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void close(){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
