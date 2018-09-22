package com.app.service;

import java.util.List;

import com.app.entity.BankTemporaryQuota;

public interface BankTemporaryQuotaService {
	
	public List<BankTemporaryQuota> temporaryQuotaPageList(BankTemporaryQuota temporaryQuota);
	public List<BankTemporaryQuota> exportQuota(BankTemporaryQuota temporaryQuota);	

}
