package com.database;


public class DBSourceAAS extends BaseDBSource {
	
	public DBSourceAAS(){
		this.jndiName = "jdbc/jettdb";
		getConnection(jndiName);
	}
	
}
