package com.app.mapper;

import java.util.List;

import com.app.entity.InforMation;

public interface InforMationMapper {

	/**
	 * 查询
	 * @param inforMation
	 * @return
	 */
	public List<InforMation> listPageInforMation(InforMation inforMation);
	public int getCount(InforMation inforMation);
	
	
	
	/**
	 * 根据ID查询
	 * @param happyPoollId
	 * @return
	 */
	public InforMation getInforMationByid(String custId);
}
