package com.app.service;
import java.util.List;

import com.app.entity.BankInnerLink;
public interface BankInnerLinkService {

	public List<BankInnerLink> selectBankInnerLinkPageList(BankInnerLink bankInnerLink);
	public int selectBankInnerLinkNum(BankInnerLink bankInnerLink);
	public void insertBankInnerLink(BankInnerLink bankInnerLink);
	public BankInnerLink selectBankInnerLink(BankInnerLink bankInnerLink);
	public int deleteBankInnerLink(String linkNo);
	public void updateSaveBankInnerLink(BankInnerLink bankInnerLink);
	public BankInnerLink getBankInnerLinkByNo(String linkNo);
	public BankInnerLink selectBankInnerLinkByLinkNo(BankInnerLink bankInnerLink);
}
