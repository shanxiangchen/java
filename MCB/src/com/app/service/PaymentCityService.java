package com.app.service;

import java.util.List;

import com.app.entity.PaymentCity;

/**
 * 分期城市接口
 * create date 2015/12/28
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public interface PaymentCityService {
	
	List<PaymentCity> listPayment(PaymentCity paymentCity);
	
	/**
	 * 分期城市信息数据,实现分页查询
	 * @param paymentCity
	 * @return
	 */
	public List<PaymentCity> listPagePaymentCity(PaymentCity paymentCity);
	
	/**
	 * 实现添加城市信息
	 * @param paymentCity
	 */
	public boolean savePaymentCity(PaymentCity paymentCity);
	
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
	public void updatePaymentCity(PaymentCity paymentCity);
	
	/***
	 * 实现分期城市删除信息
	 * @param cityId
	 */
	public void deletePaymentCity(int cityId);
}