package com.app.mapper;

import java.util.List;

import com.app.entity.BankEditorSave;

 
public interface BankEditorSaveMapper {
	  
	public List<BankEditorSave> selectBankEditorList();
	
	public int saveEditor(BankEditorSave bankEditorSave);
	
	public int updateEditor(BankEditorSave bankEditorSave);
	
	public BankEditorSave selectBankEditorInfoById(String editorNum);
	
	 

	 

	
	 
	 
}
