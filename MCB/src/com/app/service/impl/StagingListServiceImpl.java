package com.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.app.entity.StagingList;
import com.app.mapper.StagingListMapper;
import com.app.service.StagingListService;
import com.app.util.ImportDataUtils;

public class StagingListServiceImpl implements StagingListService {
	private StagingListMapper listMapper;

	public StagingListMapper getListMapper() {
		return listMapper;
	}

	public void setListMapper(StagingListMapper listMapper) {
		this.listMapper = listMapper;
	}
	@Override
	public List<StagingList> stagingListPageList(StagingList lista) {
		List<StagingList> stagingLists = listMapper.stagingListPageList(lista);
		return stagingLists;
	}

	@Override
	public void insertStagingList(StagingList stagingList) {

	}

	@Override
	public void getJdbcCon(List<StagingList> list) {
		ApplicationContext ct = new ClassPathXmlApplicationContext(
				"spring/ApplicationContext.xml");
		// DriverManagerDataSource
		// datasource=(DriverManagerDataSource)ct.getBean("dataSource");
		DataSource datasource = ct.getBean("dataSource", DataSource.class);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			sql = "insert into BANK_SPEEDY_LIST"
					+ "(SPEEDY_LIST,CITY,NAME,PHONE,CREDIT_CARD_LIMIT,NO_MORTGAGE_LARGE_STAGING,NO_MORTGAGE_CAR_STAGING,NO_MORTGAGE_DIRECT_STAGING,MORTGAGE_CAR_STAGING,MORTGAGE_DIRECT_STAGING,EXPIRY_DATE)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			StagingList stagingList = new StagingList();
			for (int i = 0; i < list.size(); i++) {

				stagingList = list.get(i);
				Object[] params = { stagingList.getCity(),
						stagingList.getName(), stagingList.getPhone(),
						stagingList.getCreditCardLimit(),
						stagingList.getNoMortgageLargeStaging(),
						stagingList.getNoMortgageCarStaging(),
						stagingList.getNoMortgageDirectStaging(),
						stagingList.getMortgageCarStaging(),
						stagingList.getMortgageDirectStaging(),
						stagingList.getExpiryDate() };
				// 填充占位符
				for (int j = 0; j < params.length; j++) {
					ps.setObject(j + 1, params[j]);
				}
				// 执行操作
				// ps.executeUpdate();
				// 积攒
				ps.addBatch();
				// 当积攒到20条时，就执行一次，并清空先前积攒的SQL
				if ((i + 1) % 20 == 0) {
					ps.executeBatch();
					ps.clearBatch();
				}
			}
			// 若总数不为执行的整数倍时，再执行一次
			if (list.size() % 20 != 0) {
				ps.executeBatch();
				ps.clearBatch();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		} finally {
			try {
				conn.commit();
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public int deleteStagingList(String[] strs) {
		return listMapper.delectStagingList(strs);
	}

	@Override
	public int StagingListbyid() {
		return listMapper.getStagingListbyid();
	}

	/**
	 * 查询城市编码
	 */
	@Override
	public int selectCity() {
		return listMapper.selectCity();
	}

	@Override
	public int getStangeListNum(StagingList stagingList) {
		return listMapper.getStangeListNum(stagingList);
	}

	@Override
	public int deleteAllStaging() {
		return listMapper.deleteAllStaging();
	}

	@Override
	public void batchImportData(String fileName, String filePath,
			Integer fileCount) throws Exception{
		//批量导入sql，采用预编译
		ImportDataUtils importDataUtils = new ImportDataUtils(fileName, filePath);
		Thread thReadData=new Thread(importDataUtils);
	   // thReadData.sleep(1000);
	    thReadData.setDaemon(true);
	    thReadData.start();
	}


}
