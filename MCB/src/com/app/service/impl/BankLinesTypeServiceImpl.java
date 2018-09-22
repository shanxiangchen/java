package com.app.service.impl;

import java.util.List;

import com.app.entity.BankLinesType;
import com.app.mapper.BankLinesTypeMapper;
import com.app.service.BankLinesTypeService;
 

public class BankLinesTypeServiceImpl implements BankLinesTypeService{

		private BankLinesTypeMapper bankLinesTypeMapper;
		
		
	
	public BankLinesTypeMapper getBankLinesTypeMapper() {
			return bankLinesTypeMapper;
		}



		public void setBankLinesTypeMapper(BankLinesTypeMapper bankLinesTypeMapper) {
			this.bankLinesTypeMapper = bankLinesTypeMapper;
		}

 
		
	@Override
		public List<BankLinesType> bankLinesTypePageList(BankLinesType linesType) {
			List<BankLinesType> bankLinesTypes = bankLinesTypeMapper.bankLinesTypePageList(linesType);
		return bankLinesTypes;
		}



	@Override
	public BankLinesType getLinesTypebyid(String linesTypeId) {
			return bankLinesTypeMapper.getLinesTypebyid(linesTypeId);
	}



	@Override
	public int deleteLinesType(String linesTypeId) {
		return bankLinesTypeMapper.deleteLinesType(linesTypeId);
	}

	@Override
	public void updateLinesTypes(BankLinesType linesType) {
		bankLinesTypeMapper.updateLinesTypes(linesType);
	}



	@Override
	public void updateLinesMaxValue(BankLinesType linesType) {
		bankLinesTypeMapper.updateLinesMaxValue(linesType);
	}



	@Override
	public void updateLinesValue(BankLinesType linesType) {
		bankLinesTypeMapper.updateLinesValue(linesType);
	}

}
