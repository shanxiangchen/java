package com.app.mapper;

import java.util.List;

import com.app.entity.Template;

public interface TemplateMapper {
	public List<Template> TemplatePageList(Template template);
	 
	public void TemplateAdd (Template template);
	
	public int getCountByName(String infoTemplateEncoding);
	
	public Template TemplateById(int infoTenplateId);
	
	public void TemplateEdit(Template template);
	
	public void TemplatetDel(int infoTenplateId);
	
}
