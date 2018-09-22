package com.database;


public class DBSourceJETT extends BaseDBSource {

	public DBSourceJETT() {
		this.url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
		this.username = "scott";
		this.password = "tiger";
		getConnection();
	}

}
