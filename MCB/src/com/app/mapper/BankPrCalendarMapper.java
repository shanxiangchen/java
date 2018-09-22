package com.app.mapper;

import java.util.List;

import com.app.entity.BankPrCalendar;

public interface BankPrCalendarMapper {
	
	public List<BankPrCalendar> selectCalendarPageList(BankPrCalendar bankPrCalendar);
	public int insertBankPrCalendar(BankPrCalendar bankPrCalendar);
	public int selectCountExists(BankPrCalendar bankPrCalendar);
    public int delCalendar(String[] strs);
    public BankPrCalendar selectCalendarById(String calendarId);
    public int updateCalendar(BankPrCalendar bankPrCalendar);
}
