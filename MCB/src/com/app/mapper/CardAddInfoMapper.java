package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.BankCodeInfo;
import com.app.entity.CardAddInfo;

 
public interface CardAddInfoMapper {
	 
	public List<CardAddInfo> cardAddInfoPageList(CardAddInfo cardAddInfo);	
	public List<BankCodeInfo> listCardOfType();
	public List<BankCodeInfo> listCardAddType();
	public List<BankCodeInfo> listCardForAge();
	public List<BankCodeInfo> listCardForLike();
	
	public int insertCardAddInfo(CardAddInfo cardAddInfo);
	
	public void insertBankCardLike(Map<String,Object> map);
	
	public void deleteCodeAddInfo(String[] strs);
	
	public void deleteBankCardLike(String[] strs);
	
	public CardAddInfo getBankCardInfoById(String cardId);
	
	public List<CardAddInfo> selectBankCardLikeById(String cardId); 
	public int updateBankCardInfoById(CardAddInfo cardAddInfo);
	
	public List<String> selectBankCardAddInfoPics(String[] strs);
	
	public int selectBankCardAddInfoByNum(String cardNum);
	
	 public int getCardId();
	 
//	 public List<String> getCardFOrLikesByCardId(String cardId);
	 
	 public void deleteBankCardLikes(String cardId);
	 
	 public void deleteBankCardInfo(String cardId);
	 
}
