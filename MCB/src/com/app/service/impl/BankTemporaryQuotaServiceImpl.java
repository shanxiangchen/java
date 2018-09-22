package com.app.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.app.entity.BankTemporaryQuota;
import com.app.mapper.BankTemporaryQuotaMapper;
import com.app.service.BankTemporaryQuotaService;

public class BankTemporaryQuotaServiceImpl implements BankTemporaryQuotaService {

    private BankTemporaryQuotaMapper bankTemporaryQuotaMapper;

    public BankTemporaryQuotaMapper getBankTemporaryQuotaMapper() {
        return bankTemporaryQuotaMapper;
    }

    public void setBankTemporaryQuotaMapper(BankTemporaryQuotaMapper bankTemporaryQuotaMapper) {
        this.bankTemporaryQuotaMapper = bankTemporaryQuotaMapper;
    }

    @Override
    public List<BankTemporaryQuota> temporaryQuotaPageList(BankTemporaryQuota temporaryQuota) {
        String beginDate = temporaryQuota.getBeginDate();
        String endDate = temporaryQuota.getEndDate();
		if (StringUtils.isBlank(beginDate) && StringUtils.isBlank(endDate)
				&& StringUtils.isBlank(temporaryQuota.getSearchAll())) {
            SimpleDateFormat s = new SimpleDateFormat("yyyyMMdd");
            Date now = new Date();
            String nowStr = s.format(now);
            temporaryQuota.setBeginDate(nowStr);
            temporaryQuota.setEndDate(nowStr);
        }
        
        return bankTemporaryQuotaMapper.temporaryQuotaPageList(temporaryQuota);
    }

    @Override
    public List<BankTemporaryQuota> exportQuota(BankTemporaryQuota temporaryQuota) {

        return bankTemporaryQuotaMapper.exportQuota(temporaryQuota);
    }

}
