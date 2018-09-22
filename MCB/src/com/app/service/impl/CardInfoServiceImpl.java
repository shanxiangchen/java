package com.app.service.impl;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.CardInfo;
import com.app.mapper.CardInfoMapper;
import com.app.service.CardInfoService;

/**
 * 卡产品信息服务实现类
 * create date 2016/3/16
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class CardInfoServiceImpl implements  CardInfoService {
	private CardInfoMapper cardInfoMapper;

	public CardInfoMapper getCardInfoMapper() {
		return cardInfoMapper;
	}

	public void setCardInfoMapper(CardInfoMapper cardInfoMapper) {
		this.cardInfoMapper = cardInfoMapper;
	}
	
	
	
	
	
	
	@Override
	public List<CardInfo> cardInfoPageList(CardInfo cardInfo) {
		// TODO Auto-generated method stub
		List<CardInfo> cardInfos = cardInfoMapper.cardInfoPageList(cardInfo);
		return cardInfos;
	}

	/**
	 * 数据字典信息
	 */
	@Override
	public List<BankCodeInfo> listBankCodeInfo() {
		// TODO Auto-generated method stub
		return cardInfoMapper.listBankCodeInfo();
	}
	/**
	 * 添加卡产品信息
	 */
	@Override
	public void insertCardInfo(CardInfo cardInfo) {
		// TODO Auto-generated method stub
		cardInfoMapper.insertCardInfo(cardInfo);
	}
	/**
	 * 查询卡产品单条信息
	 */
	@Override
	public CardInfo listCardInfoById(int cardId) {
		// TODO Auto-generated method stub
		return cardInfoMapper.listCardInfoById(cardId);
	}
	
	/**
	 * 查询所有的图片名称
	 */
	@Override
	public List<CardInfo> listCardInfoByIds(int cardId) {
		// TODO Auto-generated method stub
		return cardInfoMapper.listCardInfoByIds(cardId);
	}

	/**
	 * 带有图片的修改
	 */
	@Override
	public void editCardInfo(CardInfo cardInfo) {
		// TODO Auto-generated method stub
		cardInfoMapper.editCardInfo(cardInfo);
	}
	/**
	 *不带有图片的修改
	 */
	@Override
	public void editCardInfos(CardInfo cardInfo) {
		// TODO Auto-generated method stub
		cardInfoMapper.editCardInfos(cardInfo);
	}
	/**
	 * 删除卡产品信息
	 */
	@Override
	public void delCardInfo(int cardId) {
		// TODO Auto-generated method stub
		cardInfoMapper.delCardInfo(cardId);
	}
	
	
}
