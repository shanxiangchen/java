package com.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.app.entity.BankCarStaging;
import com.app.listener.Const;
import com.app.mapper.BankCarStagingMapper;
import com.app.service.BankCarStagingService;

/**
 * 汽车分期，经销商，服务接口实现类 create date 2016/02/30 <br/>
 * 
 * @author shiguangting@tansun.com.cn
 * 
 */
public class BankCarStagingServiceImpl implements BankCarStagingService {
	private BankCarStagingMapper bankCarStagingMapper;

	public BankCarStagingMapper getBankCarStagingMapper() {
		return bankCarStagingMapper;
	}

	public void setBankCarStagingMapper(
			BankCarStagingMapper bankCarStagingMapper) {
		this.bankCarStagingMapper = bankCarStagingMapper;
	}

	@Override
	public List<BankCarStaging> expCarStaging(BankCarStaging bankCarStaging) {
		return bankCarStagingMapper.expCarStaging(bankCarStaging);
	}

	@Override
	public List<BankCarStaging> selbankCarStagingPageList(
			BankCarStaging bankCarStaging) {
		List<BankCarStaging> bankCarStagings = bankCarStagingMapper
				.selbankCarStagingPageList(bankCarStaging);
		return bankCarStagings;
	}

	@Override
	public int selectStoreNoList(String carStagingCityCode) {
		return bankCarStagingMapper.selectStoreNoList(carStagingCityCode);
	}

	@Override
	public void deleteCarStaging(String carStagingCityCode) {
		bankCarStagingMapper.delCarStaging(carStagingCityCode);
	}

	@Override
	public void getJdbcCon(List<BankCarStaging> list) {
		DataSource datasource = (DataSource) Const.WEB_APP_CONTEXT.getBean(
				"dataSource", DataSource.class);
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = null;
		try {
			conn = datasource.getConnection();
			conn.setAutoCommit(false);
			sql = "INSERT INTO bank_car_staging "
					+ "(CAR_STAGING_CITY,CAR_STAGING_BRAND,MARKETING_NAME,CAR_STAGING_LOCATION,CAR_STAGING_PHONE,CAR_STAGING_CITY_CODE) "
					+ "values(?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			BankCarStaging store = new BankCarStaging();
			for (int i = 0; i < list.size(); i++) {

				store = list.get(i);
				Object[] params = { store.getCarStagingCity(),
						store.getCarStagingBrand(), store.getMarketingName(),
						store.getCarStagingLocation(),
						store.getCarStagingPhone(),
						store.getCarStagingCityCode() };
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
	public BankCarStaging getBankCarStagingId(BankCarStaging tmp) {
		return bankCarStagingMapper.getBankCarStagingId(tmp);
	}

	@Override
	public void deleteBankCarStagingById(int bankCarStaingId) {
		bankCarStagingMapper.deleteBankCarStagingById(bankCarStaingId);
	}

	@Override
	public int getBankCarStagingCityByCode(BankCarStaging bankCarStaging) {
		return bankCarStagingMapper.getBankCarStagingCityByCode(bankCarStaging);
	}

	@Override
	public int getBankCarStagingByCode(BankCarStaging tmp) {
		return bankCarStagingMapper.getBankCarStagingByCode(tmp);
	}

	@Override
	public void deleteBankCarStagingByCode(String carStagingCode) {
		bankCarStagingMapper.deleteBankCarStagingByCode(carStagingCode);
	}

	@Override
	public int getBankcarStagingNum(BankCarStaging sto) {
		return bankCarStagingMapper.getBankcarStagingNum(sto);
	}
	
	

}
