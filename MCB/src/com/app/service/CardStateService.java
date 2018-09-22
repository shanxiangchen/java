package com.app.service;

import java.util.List;

import com.app.entity.BankUploadFtp;
import com.app.entity.CardState;
 
/**
 * 卡片申请状态服务接口
 * create date 2016/3/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface CardStateService {
	public List<CardState> listCardState(CardState cardState);
	public List<BankUploadFtp> selectBankUploadFtpList(String insertSysDate);
	public List<BankUploadFtp> selectFtpPageList(BankUploadFtp bankUploadFtp);
}
