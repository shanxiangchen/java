package com.app.mapper;

import java.util.List;

import com.app.entity.BankTemporaryQuota;
 
public interface BankTemporaryQuotaMapper {
	 
	public List<BankTemporaryQuota> temporaryQuotaPageList(BankTemporaryQuota temporaryQuota);	
	
	public List<BankTemporaryQuota> exportQuota(BankTemporaryQuota temporaryQuota);	
}
