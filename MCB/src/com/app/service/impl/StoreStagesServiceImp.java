package com.app.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import com.app.entity.StoreStages;
import com.app.listener.Const;
import com.app.mapper.StoreStagesMapper;
import com.app.service.StoreStagesService;
@SuppressWarnings({"rawtypes","unchecked"})
public class StoreStagesServiceImp implements StoreStagesService{

	private StoreStagesMapper  storeStagesMapper;
	public StoreStagesMapper getStoreStagesMapper() {
		return storeStagesMapper;
	}

	public void setStoreStagesMapper(StoreStagesMapper storeStagesMapper) {
		this.storeStagesMapper = storeStagesMapper;
	}

	@Override
	/**
	 * 查询商店信息
	 */
	public List<StoreStages> selectStoreStagesListPage(StoreStages store) {
		 
		List<StoreStages> list=storeStagesMapper.selectStoreStagesListPage(store);
		return list;
	}

	
	@Override
	public boolean savestorestages(StoreStages store) {
		// TODO Auto-generated method stub
//		int count = typeMapper.getCountByName(type.getOddsshoptype());
//		System.out.println(count+"00000000000000");
//		if(count>0){
//			System.out.println("++++++++++++++");
//			return false;
//		}else{
		storeStagesMapper.insertStoreStages(store);
//			System.out.println("---------------");
			return true;
		}
//		return false;


	@Override
	public StoreStages getStoreStagesById(String storeStagesId) {
		return storeStagesMapper.getStoreStagesById(storeStagesId);
	}

	@Override
	public void saverStoreStages(StoreStages store) {
		storeStagesMapper.updateStoreStages(store);
	}

	@Override
	public int deleteStoreStages(String storeStagesId) {
		return storeStagesMapper.deleteStoreStages(storeStagesId);
	}
	@Override
	public int deleteStoreStagesAll() {
		return storeStagesMapper.deleteStoreStagesAll();
	}

	 


	@Override
	public int deleteActShop(String[] storeStagesId) {
		 
		return storeStagesMapper.deleteActShop(storeStagesId);
	}


	

	@Override
	public int insertStoreList(List list){
		return storeStagesMapper.insertStoreList(list);
	}
	
	public List<String> selectStoreNoList(){
		return storeStagesMapper.selectStoreNoList();
	}
	
	public void getJdbcCon(List<StoreStages> list){
		//ApplicationContext ct=new ClassPathXmlApplicationContext("spring/ApplicationContext.xml");
		//DriverManagerDataSource  datasource=(DriverManagerDataSource)ct.getBean("dataSource");
		//DataSource datasource=ct.getBean("dataSource",DataSource.class);
		DataSource datasource=(DataSource)Const.WEB_APP_CONTEXT.getBean("dataSource",DataSource.class);
		Connection conn=null;
		PreparedStatement ps=null;
		String sql=null;
		try {
			conn=datasource.getConnection();
			conn.setAutoCommit(false);
			sql="insert into BANK_STORE_STAGES"+
		         "(STORE_NO,STORE_NAME,STORE_ADDR,FEE3,FEE6,FEE12,FEE24,CITYNO,TRADE_NAME)"+
				 "values(?,?,?,?,?,?,?,?,?)";
			ps=conn.prepareStatement(sql);
			StoreStages store=new StoreStages();
			for(int i=0;i<list.size();i++){
				
				store=list.get(i);
				Object[] params={store.getStoreNo(),store.getStoreName(),store.getStoreAddr(),store.getFee3(),store.getFee6(),store.getFee12(),store.getFee24(),store.getCityNo(),store.getTradeName()};
			    //填充占位符
				for(int j=0;j<params.length;j++){
					ps.setObject(j+1,params[j]);
				}
				//执行操作
				//ps.executeUpdate();
				//积攒
				ps.addBatch();
				//当积攒到20条时，就执行一次，并清空先前积攒的SQL
 			    if((i+1)%20==0){
 			    	ps.executeBatch();
 					ps.clearBatch();
 				}
			}
			//若总数不为执行的整数倍时，再执行一次
  			if(list.size()%20!=0){
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
		}finally{
			try {
				conn.commit();
				if(ps!=null){
					ps.close();
				}
				if(conn!=null){
					conn.close();
				}
			} catch (SQLException e) {
				 e.printStackTrace();
			}
			
		}
	}

}

