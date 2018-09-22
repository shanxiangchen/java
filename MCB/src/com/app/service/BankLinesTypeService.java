package com.app.service;

import java.util.List;

import com.app.entity.BankLinesType;

public interface BankLinesTypeService {

	
	public List<BankLinesType> bankLinesTypePageList(BankLinesType linesType);
	
	public BankLinesType getLinesTypebyid(String linesTypeId);
	
	
	int deleteLinesType(String linesTypeId);

	void updateLinesTypes(BankLinesType linesType);
	public void updateLinesMaxValue(BankLinesType linesType);
	
	public void updateLinesValue(BankLinesType linesType);
}
