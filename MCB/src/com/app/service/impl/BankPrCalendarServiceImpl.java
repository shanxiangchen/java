package com.app.service.impl;

import java.util.List;

import com.app.entity.BankPrCalendar;
import com.app.mapper.BankPrCalendarMapper;
import com.app.service.BankPrCalendarService;
 
public class BankPrCalendarServiceImpl implements BankPrCalendarService{
	
	private BankPrCalendarMapper  bankPrCalendarMapper;
	 
	public BankPrCalendarMapper getBankPrCalendarMapper() {
		return bankPrCalendarMapper;
	}
 
	public void setBankPrCalendarMapper(BankPrCalendarMapper bankPrCalendarMapper) {
		this.bankPrCalendarMapper = bankPrCalendarMapper;
	}




	@Override
	public List<BankPrCalendar> selectCalendarPageList(
			BankPrCalendar bankPrCalendar) {
		 
		return bankPrCalendarMapper.selectCalendarPageList(bankPrCalendar);
	}

	@Override
	public int insertBankPrCalendar(BankPrCalendar bankPrCalendar) {
		 
		return bankPrCalendarMapper.insertBankPrCalendar(bankPrCalendar);
	}

	@Override
	public int selectCountExists(BankPrCalendar bankPrCalendar) {
		 
		return bankPrCalendarMapper.selectCountExists(bankPrCalendar);
	}

	@Override
	public int deleteCalendar(String[] strs) {
		 
		return bankPrCalendarMapper.delCalendar(strs);
	}

	@Override
	public BankPrCalendar selectCalendarById(String calendarId) {
		 
		return bankPrCalendarMapper.selectCalendarById(calendarId);
	}

	@Override
	public int updateCalendar(BankPrCalendar bankPrCalendar) {
		 
		return bankPrCalendarMapper.updateCalendar(bankPrCalendar);
	}
	
	

	 
	
	 


	 
	 
}
