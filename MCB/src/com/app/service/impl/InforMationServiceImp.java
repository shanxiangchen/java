package com.app.service.impl;

import java.util.List;

import com.app.entity.InforMation;
import com.app.mapper.InforMationMapper;
import com.app.service.InforMationService;
import com.app.util.PageBean;

public class InforMationServiceImp implements InforMationService {

	private InforMationMapper inforMationMapper;
		
	public InforMationMapper getInforMationMapper() {
			return inforMationMapper;
		}

		public void setInforMationMapper(InforMationMapper inforMationMapper) {
			this.inforMationMapper = inforMationMapper;
		}

		
	@Override
	public PageBean<InforMation> listPageinformation(InforMation inforMation) {
		PageBean<InforMation> pageBean = new PageBean<InforMation>();
		pageBean.setPageNo(inforMation.getPageNo());
		pageBean.setPageSize(inforMation.getPageSize());
		int totalRecordes = inforMationMapper.getCount(inforMation);
		pageBean.setTotalRecordes(totalRecordes);
		List<InforMation> list = inforMationMapper.listPageInforMation(inforMation);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public InforMation getInforMationByid(String custId) {
		return inforMationMapper.getInforMationByid(custId); 
	}

	 

}
