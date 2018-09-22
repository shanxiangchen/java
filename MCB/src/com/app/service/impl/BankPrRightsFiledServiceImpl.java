package com.app.service.impl;


 
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.BankPrRightsFiled;
import com.app.entity.PageInfo;
import com.app.mapper.BankPrRightsFiledMapper;
import com.app.service.BankPrRightsFiledService;
import com.app.util.PageBean;
 
 

 
public class BankPrRightsFiledServiceImpl implements BankPrRightsFiledService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private BankPrRightsFiledMapper bankPrRightsFiledMapper;
	 
	   
	public BankPrRightsFiledMapper getBankPrRightsFiledMapper() {
		return bankPrRightsFiledMapper;
	}
	public void setBankPrRightsFiledMapper(
			BankPrRightsFiledMapper bankPrRightsFiledMapper) {
		this.bankPrRightsFiledMapper = bankPrRightsFiledMapper;
	}




	@Override
	public PageBean<BankPrRightsFiled> selectBankPrRightsFiledList(
			BankPrRightsFiled bankPrRightsFiled, PageInfo pageInfo) {
		
		PageBean<BankPrRightsFiled> pageBean=new PageBean<BankPrRightsFiled>();
		pageBean.setPageNo(pageInfo.getPageNo());
		pageBean.setPageSize(pageInfo.getPageSize());
		int skipResults = (pageInfo.getPageNo()-1)*pageInfo.getPageSize();; // 忽略的记录数
		int totalRecordes=bankPrRightsFiledMapper.getCount(bankPrRightsFiled);
		pageBean.setTotalRecordes(totalRecordes);
		List<BankPrRightsFiled> list=(List<BankPrRightsFiled>) this.sqlSession.selectList
				("com.app.mapper.BankPrRightsFiledMapper.selectBankPrRightsFiledList",bankPrRightsFiled,
						new RowBounds(skipResults,pageInfo.getPageSize()));
		pageBean.setList(list);  
		return pageBean;
	}
	@Override
	public int saveRightsFiled(BankPrRightsFiled bankPrRightsFiled) {
		 
		return bankPrRightsFiledMapper.saveRightsFiled(bankPrRightsFiled);
	}
	@Override
	public int delRightsFiled(String[] strs) {
		 
		return bankPrRightsFiledMapper.delRightsFiled(strs);
	}
	@Override
	public BankPrRightsFiled selectRightsFiledById(String filedId) {
		 
		return bankPrRightsFiledMapper.selectRightsFiledById(filedId);
	}
	@Override
	public int updateRightsFiled(BankPrRightsFiled bankPrRightsFiled) {
		 
		return bankPrRightsFiledMapper.updateRightsFiled(bankPrRightsFiled);
	}
	
	
	  
	 
	 
}
