package com.app.service.impl;


 
import java.util.List;
import java.util.Map;

import com.app.entity.BankCodeInfo;
import com.app.entity.CardAddInfo;
import com.app.mapper.CardAddInfoMapper;
import com.app.service.CardAddInfoService;

 
public class CardAddInfoServiceImpl implements CardAddInfoService{
	private CardAddInfoMapper  cardAddInfoMapper;
	  
	public CardAddInfoMapper getCardAddInfoMapper() {
		return cardAddInfoMapper;
	}
 
	public void setCardAddInfoMapper(CardAddInfoMapper cardAddInfoMapper) {
		this.cardAddInfoMapper = cardAddInfoMapper;
	}

	
	
	
	@Override
	public List<CardAddInfo> cardAddInfoPageList(CardAddInfo cardAddInfo) {
		List<CardAddInfo> cardAddInfos = cardAddInfoMapper.cardAddInfoPageList(cardAddInfo);
		return cardAddInfos;
	}

	@Override
	public List<BankCodeInfo> listCardOfType() {
		 
		return cardAddInfoMapper.listCardOfType();
	}

	@Override
	public List<BankCodeInfo> listCardAddType() {
		 
		return cardAddInfoMapper.listCardAddType();
	}

	@Override
	public List<BankCodeInfo> listCardForAge() {
		 
		return cardAddInfoMapper.listCardForAge();
	}

	@Override
	public List<BankCodeInfo> listCardForLike() {
		 
		return cardAddInfoMapper.listCardForLike();
	}

	@Override
	public int insertCardAddInfo(CardAddInfo cardAddInfo) {
		 
		return cardAddInfoMapper.insertCardAddInfo(cardAddInfo);
	}
	
	@Override
	public int getCardId(){
		return cardAddInfoMapper.getCardId();
	};

	@Override
	public void insertBankCardLike(Map<String,Object> map) {
		 
		cardAddInfoMapper.insertBankCardLike(map);
		
	}

	@Override
	public void deleteCodeAddInfo(String[] strs) {
		cardAddInfoMapper.deleteCodeAddInfo(strs);
		
	}

	@Override
	public void deleteBankCardLike(String[] strs) {
		cardAddInfoMapper.deleteBankCardLike(strs);
	}

	@Override
	public CardAddInfo getBankCardInfoById(String cardId) {
		 
		return cardAddInfoMapper.getBankCardInfoById(cardId);
	}

	@Override
	public List<CardAddInfo> selectBankCardLikeById(String cardId) {
		
		return cardAddInfoMapper.selectBankCardLikeById(cardId);
		 
	}

	@Override
	public int updateBankCardInfoById(CardAddInfo cardAddInfo) {
		
		return cardAddInfoMapper.updateBankCardInfoById(cardAddInfo);
	}

	@Override
	public List<String> selectBankCardAddInfoPics(String[] strs) {
		 
		return cardAddInfoMapper.selectBankCardAddInfoPics(strs);
	}

	@Override
	public int selectBankCardAddInfoByNum(String cardNum) {
		 
		return cardAddInfoMapper.selectBankCardAddInfoByNum(cardNum);
	}

	@Override
	public boolean deleteBankCardLikes(String cardId) {
		cardAddInfoMapper.deleteBankCardLikes(cardId);
		return true;
	}

	

	 
	
	 
	 

	 
	 
}
