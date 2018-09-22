package com.app.mapper;

import java.util.List;

import com.app.entity.BankLinesType;

public interface BankLinesTypeMapper {

	public List<BankLinesType> bankLinesTypePageList(BankLinesType linesType);
	
	/**
	 * 根据ID查询
	 * @param beauidealExperienceId
	 * @return
	 */
	public BankLinesType getLinesTypebyid(String linesTypeId);
	
	
	public int deleteLinesType(String linesTypeId);

	
	public void updateLinesTypes(BankLinesType linesType);
	public void updateLinesMaxValue(BankLinesType linesType);
	public void updateLinesValue(BankLinesType linesType);
	
}
