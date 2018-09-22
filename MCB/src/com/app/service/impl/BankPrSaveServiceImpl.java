package com.app.service.impl;


 
 
 
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.entity.BankPrSave;
import com.app.entity.PageInfo;
import com.app.mapper.BankPrSaveMapper;
import com.app.service.BankPrSaveService;
import com.app.util.PageBean;
 
 

 
public class BankPrSaveServiceImpl implements BankPrSaveService{
	@Autowired
	private SqlSessionTemplate sqlSession;
	private BankPrSaveMapper bankPrSaveMapper;
	
	public BankPrSaveMapper getBankPrSaveMapper() {
		return bankPrSaveMapper;
	}
	public void setBankPrSaveMapper(BankPrSaveMapper bankPrSaveMapper) {
		this.bankPrSaveMapper = bankPrSaveMapper;
	}


	@Override
	public PageBean<BankPrSave> selectBankPrSaveList(BankPrSave bankPrSave,
			PageInfo pageInfo) {
		
		PageBean<BankPrSave> pageBean=new PageBean<BankPrSave>();
		pageBean.setPageNo(pageInfo.getPageNo());
		pageBean.setPageSize(pageInfo.getPageSize());
		int skipResults = (pageInfo.getPageNo()-1)*pageInfo.getPageSize();; // 忽略的记录数
		int totalRecordes=bankPrSaveMapper.getCount(bankPrSave);
		pageBean.setTotalRecordes(totalRecordes);
		List<BankPrSave> list=(List<BankPrSave>) this.sqlSession.selectList
				("com.app.mapper.BankPrSaveMapper.selectBankPrSaveList",bankPrSave,
						new RowBounds(skipResults,pageInfo.getPageSize()));
		pageBean.setList(list);  
		return pageBean;
	}
	@Override
	public Map<String, String> infoBankPrSaveById(String prSaveId) {
		 
		return bankPrSaveMapper.infoBankPrSaveById(prSaveId);
	}
	@Override
	public List<Map<String,String>> selectFiledNameById(String typeId) {
		 
		return bankPrSaveMapper.selectFiledNameById(typeId);
	}
	@Override
	public int delBankPrSave(String[] strs) {
		 
		return bankPrSaveMapper.delBankPrSave(strs);
	}
	
	  
	 
	 
	 
}
