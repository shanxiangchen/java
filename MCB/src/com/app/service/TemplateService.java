package com.app.service;
import java.util.List;

import com.app.entity.Template;
 

public interface TemplateService {
	public List<Template> TemplateList(Template template);
	
	public boolean saveTemplate (Template template);
	
	public Template TemplateById(int infoTenplateId);
	
	public void updateTemplate(Template template);
	
	public void deleteTemplate(int infoTenplateId);
}
