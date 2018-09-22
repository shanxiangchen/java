package com.database;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test {

	public static void main(String[] args) {
		OutInputInfoDao oiiImpl = new OutInputInfoDao();
		OutInputInfo oii = new OutInputInfo();
		Map condition = new HashMap();
		//condition.put("doc_type", "T");
		//oiiImpl.delete(condition);
		String sql = "";
		List<OutInputInfo> list = oiiImpl.getResult(condition);
		DBSourceJETT ds = new DBSourceJETT();
		try {
			int s = 0;
			ds.setAutoCommit(false);
			for (int i = 0; i < list.size(); i++) {
				s++;
				System.out.println("开始处理第" + s + "条记录！");
				oii = (OutInputInfo) list.get(i);
				sql = oiiImpl.getInsertSQL(oii);
				ds.execute(sql);
				condition = new HashMap();
				condition.put("policy_no", oii.getPolicy_no());
				condition.put("Doc_type", oii.getDoc_type());
				sql = oiiImpl.getUpdateSQL(oii, condition);
				ds.execute(sql);
			}
			ds.commit();
		} catch (SQLException e) {
			ds.rollback();
			e.printStackTrace();
		} finally {
			ds.close();
		}

	}

}
