package com.app.mapper;


import java.util.List;

import com.app.entity.BankTypePrompt;


public interface BankTypePromptMapper {

	
	public List<BankTypePrompt> listPageTypePrompt(BankTypePrompt typePrompt);
	public int getCount(BankTypePrompt bankTypePrompt);
	
	public void updateTypePrompt(BankTypePrompt bankTypePrompt);
	
	/**
	 * 根据ID查询单条数据信息
	 * @param 
	 * @return
	 */
	public BankTypePrompt getTypePromptByid(String typeId);
}
