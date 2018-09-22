package com.app.mapper;

import java.util.List;

import com.app.entity.ImportHtml;

public interface ImportHtmlMapper {
	/**
	 * 分页查询
	 * @param state
	 * @return
	 */
	public List<ImportHtml> selectImportListPage(ImportHtml importHtml);
    public void saveImportHtml(ImportHtml importHtml);
	
	 
	 
	 
}
