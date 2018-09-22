package com.app.service.impl;

import java.util.List;

import com.app.entity.BankUploadFtp;
import com.app.entity.CardState;
import com.app.mapper.CardStateMapper;
import com.app.service.CardStateService;
 

/**
 * 卡片申请状态服务接口实现类
 * create date 2016/3/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class CardStateServiceImpl implements CardStateService {
	private CardStateMapper cardStateMapper;

	public CardStateMapper getCardStateMapper() {
		return cardStateMapper;
	}

	public void setCardStateMapper(CardStateMapper cardStateMapper) {
		this.cardStateMapper = cardStateMapper;
	}

	@Override
	public List<CardState> listCardState(CardState cardState) {
		  
		return cardStateMapper.cardStatePageList(cardState);  
	}

	@Override
	public List<BankUploadFtp> selectBankUploadFtpList(String insertSysDate) {
		 
		return cardStateMapper.selectBankUploadFtpList(insertSysDate);
	}

	@Override
	public List<BankUploadFtp> selectFtpPageList(BankUploadFtp bankUploadFtp) {
		 
		return cardStateMapper.selectFtpPageList(bankUploadFtp);
	}
}
