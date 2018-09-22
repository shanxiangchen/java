package com.app.service;

import java.util.List;

import com.app.entity.BankTypePrompt;

public interface BankTypePromptService {

	/*
	 * 分页
	 */
	public List<BankTypePrompt> listPageBankTypePrompt(BankTypePrompt bankTypePrompt);

	
	/*
	 * 修改
	 * @param bankTypePrompt
	 * @return
	 */
	public void update(BankTypePrompt bankTypePrompt);
	
	/*
	 * 按ID查询
	 * @param happyPoollId
	 */
	public BankTypePrompt getBankTypePromptByid(String typeId);
}
