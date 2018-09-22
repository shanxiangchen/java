package com.app.entity;
/**
 * mybatis 动态拼SQL
 * @author zhaolei 
 * @date 2016-3-28 上午10:26:44
 */
public class BankPrQuartzSQL {
	
	private String sql;
	
	public BankPrQuartzSQL(String sql) {
		
		this.sql = sql;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}
	
	

}
