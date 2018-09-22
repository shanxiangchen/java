package com.app.mapper;

import java.util.List;

import com.app.entity.BankInnerLink;

/**
 * 积分配置XML接口
 * create Date 2016/4/19
 * <br/>
 * @author huangcheng
 *
 */

public interface BankInnerLinkMapper {
	
	public List<BankInnerLink> selectBankInnerLinkPageList(BankInnerLink bankInnerLink);
	public int selectBankInnerLinkNum(BankInnerLink bankInnerLink);
	public void insertBankInnerLink(BankInnerLink bankInnerLink);
	public BankInnerLink selectBankInnerLink(BankInnerLink bankInnerLink);
	public int deleteBankInnerLink(String linkNo);
	public void updateSaveBankInnerLink(BankInnerLink bankInnerLink);
	public BankInnerLink getBankInnerLinkByNo(String linkNo);
	public BankInnerLink selectBankInnerLinkByLinkNo(BankInnerLink bankInnerLink);
}
