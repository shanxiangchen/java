package com.app.mapper;

import java.util.List;

import com.app.entity.BankUploadFtp;
import com.app.entity.CardState;

/**
 * 卡片申请状态配置xml接口
 * create date 2016/3/18
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface CardStateMapper {
	public List<CardState> cardStatePageList(CardState cardState);
	public List<BankUploadFtp> selectBankUploadFtpList(String insertSysDate);
	public List<BankUploadFtp> selectFtpPageList(BankUploadFtp bankUploadFtp);
}
