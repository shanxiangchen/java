package com.app.service;

import com.app.entity.InforMation;
import com.app.util.PageBean;

public interface InforMationService {
	
	/**
	 * 分页
	 * @param inforMation
	 * @return
	 */
	public PageBean<InforMation> listPageinformation(InforMation inforMation);
	
	 
	
	/**
	 * 根据ID查询
	 * @param happyPoollId
	 * @return
	 */
	public InforMation getInforMationByid(String custId);
	
}
