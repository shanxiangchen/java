package com.app.service.impl;

import java.util.List;

import com.app.entity.Template;
import com.app.mapper.TemplateMapper;
import com.app.service.TemplateService;
 

public class TemplateServiceImpl implements TemplateService{

	private TemplateMapper templateMapper;
	public void setTemplateMapper(TemplateMapper templateMapper) {
		this.templateMapper = templateMapper;
	}
	@Override
	public List<Template> TemplateList(Template template) {
		 
		List<Template> list=templateMapper.TemplatePageList(template);
		return list;
	}
	@Override
	public boolean saveTemplate(Template template) {
		 
		int count=templateMapper.getCountByName(template.getInfoTemplateEncoding());
		if(count>0){
			return false;
		}
		else{
		templateMapper.TemplateAdd(template);
		return true;
		}
	}
	@Override
	public Template TemplateById(int infoTenplateId) {
		// TODO Auto-generated method stub
		return templateMapper.TemplateById(infoTenplateId);
	}
	@Override
	public void updateTemplate(Template template) {
		// TODO Auto-generated method stub
		templateMapper.TemplateEdit(template);
	}
	@Override
	public void deleteTemplate(int infoTenplateId) {
		// TODO Auto-generated method stub
		templateMapper.TemplatetDel(infoTenplateId);
	}
	
	
}
