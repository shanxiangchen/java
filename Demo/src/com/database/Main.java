package com.database;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) {
		DBSourceJETT db = new DBSourceJETT();
		String sql = "";
		try {
			db.setAutoCommit(false);
			sql = "insert into scott.jett_database_test values('9229011021','1','01',sysdate,'1001')";
			db.execute(sql);
			sql = "insert into scott.jett_database_test values('9229011022','2','01',sysdate,'1002')";
			db.execute(sql);
			sql = "insert into scott.jett_database_test values('9229011023','D','02',sysdate,'1314')";
			db.execute(sql);
			sql = "insert into scott.jett_database_test values('9229011024','T','02',sysdate,'1546')";
			db.execute(sql);
			sql = "select policy_no,doc_type,status,sys_date,account_select from scott.jett_database_test";
			ResultSet rs = db.getResult(sql);
			while (rs.next()) {
				System.out.print(rs.getString("policy_no") + ",");
				System.out.print(rs.getString("doc_type") + ",");
				System.out.print(rs.getString("status") + ",");
				System.out.print(rs.getString("sys_date") + ",");
				System.out.print(rs.getString("account_select"));
				System.out.println();
			}
			db.commit();
		} catch (SQLException e) {
			db.rollback();
			e.printStackTrace();
		} finally {
			db.close();
		}
	}

}
