package com.app.service;

   
import java.util.List;

import com.app.entity.BankEditorSave;
 
public interface BankEditorSaveService {
	 
	public List<BankEditorSave> selectBankEditorList();
	
	public int saveEditor(BankEditorSave bankEditorSave);
	
	public int updateEditor(BankEditorSave bankEditorSave);
	
	public BankEditorSave selectBankEditorInfoById(String editorNum);
	 
	public String printHtml(String strs,String path,String fileName,String name);
	
	 
}
