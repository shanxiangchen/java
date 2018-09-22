package com.app.mapper;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.CardInfo;

/**
 * 卡产品信息表,配置接口
 * create date 2016/3/15
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface CardInfoMapper {
	/**
	 * 分页，查询数据
	 * @param cardInfo
	 * @return
	 */
	public List<CardInfo> cardInfoPageList(CardInfo cardInfo);
	/**
	 * 数据字典信息
	 * @return
	 */
	public List<BankCodeInfo> listBankCodeInfo();
	/**
	 * 添加卡产品
	 * @param cardInfo
	 */
	public void insertCardInfo(CardInfo cardInfo);
	/**
	 * 根据卡产品Id查询单条数据
	 * @param CardId
	 * @return
	 */
	public CardInfo listCardInfoById(int CardId);
	/**
	 * 查询所有的图片名称
	 * @param CardId
	 * @return
	 */
	public List<CardInfo> listCardInfoByIds(int cardId);
	/**
	 * 带有图片的修改
	 * @param cardInfo
	 */
	public void editCardInfo(CardInfo cardInfo);
	/**
	 * 不带有图片的修改
	 * @param cardInfo
	 */
	public void editCardInfos(CardInfo cardInfo);
	
	/**
	 * 删除卡产品信息
	 * @param cardId
	 */
	public void delCardInfo(int cardId);
}
