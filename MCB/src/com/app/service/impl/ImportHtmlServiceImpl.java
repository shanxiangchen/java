package com.app.service.impl;


import java.util.List;

import com.app.entity.ImportHtml;
import com.app.mapper.ImportHtmlMapper;
import com.app.service.ImportHtmlService;
 
public class ImportHtmlServiceImpl implements ImportHtmlService{
	
	private ImportHtmlMapper  importHtmlMapper;
     

	public ImportHtmlMapper getImportHtmlMapper() {
		return importHtmlMapper;
	}
 
	public void setImportHtmlMapper(ImportHtmlMapper importHtmlMapper) {
		this.importHtmlMapper = importHtmlMapper;
	}



	@Override
	public List<ImportHtml> selectNeswPushListPage(ImportHtml importHtml) {
		 
		List<ImportHtml> list=importHtmlMapper.selectImportListPage(importHtml);
		return list;
	}

	@Override
	public void saveImportHtml(ImportHtml importHtml) {
		importHtmlMapper.saveImportHtml(importHtml);
	}

	 

	 
}
