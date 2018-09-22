package com.app.service;

   
import java.util.List;

import com.app.entity.BankPrCalendar;
  
 
public interface BankPrCalendarService {
	 
	public List<BankPrCalendar> selectCalendarPageList(BankPrCalendar bankPrCalendar);
	public int insertBankPrCalendar(BankPrCalendar bankPrCalendar);
	public int selectCountExists(BankPrCalendar bankPrCalendar);
	public int deleteCalendar(String[] strs); 
	public BankPrCalendar selectCalendarById(String calendarId);
	public int updateCalendar(BankPrCalendar bankPrCalendar);
	 
}
