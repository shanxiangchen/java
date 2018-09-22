package com.app.service.impl;

import java.util.List;

import com.app.entity.Patch;
import com.app.mapper.PatchMapper;
import com.app.service.PatchService;

public class PatchServiceImpl implements PatchService {
  private PatchMapper patchMapper;

	public PatchMapper getPatchMapper() {
		return patchMapper;
	}
	
	public void setPatchMapper(PatchMapper patchMapper) {
		this.patchMapper = patchMapper;
	}
	
	@Override
	public List<Patch> patchPageList(Patch patch) {	
		List<Patch> patchs = patchMapper.patchPageList(patch);
		return patchs;
	}

	//实现删除补件类型数据，实现方法
	@Override
	public void delPatch(int patchTypeId) {
		patchMapper.delPatch(patchTypeId);
	}
	//实现添加补件类型
	@Override
	public boolean insertPatch(Patch patch) {
		int countNewNo=patchMapper.getCountNewNo(patch.getPatchTypeCode());
		if(countNewNo>0){
			return false;
		}
		patchMapper.insertPatch(patch);
		return true;
	}
}
