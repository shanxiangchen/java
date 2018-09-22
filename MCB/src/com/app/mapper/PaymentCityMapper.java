package com.app.mapper;

import java.util.List;

import com.app.entity.PaymentCity;

/**
 * 分期城市配置类
 * @create date 2015/12/28
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface PaymentCityMapper {
	/**
	 * 分期城市信息数据,实现分页查询
	 * @param paymentCity
	 * @return
	 */
	public List<PaymentCity> listPagePaymentCity(PaymentCity paymentCity);
	/**
	 * 查询分期城市总条数
	 * @param paymentCity
	 * @return
	 */
	public int getCount(PaymentCity paymentCity);
	/**
	 * 实现添加城市信息
	 * @param paymentCity
	 */
	public void addPaymentCity(PaymentCity paymentCity);
	/**
	 * 实现判断城市编码是否唯一
	 * @param paymentCity
	 * @return
	 */
	public int getCountByName(String cityCode);
	/**
	 * 实现查询分期城市单条信息
	 * @param cityId
	 * @return
	 */
	public PaymentCity getPamentCityByid(int cityId);
	/**
	 * 实现分期城市编辑信息
	 * @param paymentCity
	 */
	public void deitPaymentCity(PaymentCity paymentCity);

	/***
	 * 实现分期城市删除信息
	 * @param cityId
	 */
	public void delPaymentCity(int cityId);

	
	
	public List<PaymentCity> listPayment(PaymentCity paymentCity);
	

}
