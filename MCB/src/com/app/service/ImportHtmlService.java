package com.app.service;

  
import java.util.List;

import com.app.entity.ImportHtml;
public interface ImportHtmlService {
	 
	public List<ImportHtml> selectNeswPushListPage(ImportHtml importHtml);
	
	public void saveImportHtml(ImportHtml importHtml);
}
