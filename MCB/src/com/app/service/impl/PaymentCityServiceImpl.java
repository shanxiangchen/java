package com.app.service.impl;

import java.util.List;

import com.app.entity.PaymentCity;
import com.app.mapper.PaymentCityMapper;
import com.app.service.PaymentCityService;
/**
 * 分期城市接口实现类
 * create date 2015/12/28
 * <br/>
 * @author shiguangting@tansun.com.cn
 *
 */
public class PaymentCityServiceImpl implements PaymentCityService  {
	private PaymentCityMapper paymentCityMapper;
	
	public PaymentCityMapper getPaymentCityMapper() {
		return paymentCityMapper;
	}

	public void setPaymentCityMapper(PaymentCityMapper paymentCityMapper) {
		this.paymentCityMapper = paymentCityMapper;
	}
	//实现分页查询方法,分期城市
	@Override
	public List<PaymentCity> listPagePaymentCity(PaymentCity paymentCity) {
		 
		List<PaymentCity> list=paymentCityMapper.listPagePaymentCity(paymentCity);
		return list;
	}
	//实现添加城市信息
	@Override
	public boolean savePaymentCity(PaymentCity paymentCity) {
		int i=paymentCityMapper.getCountByName(paymentCity.getCityCode());
		if(i>0){
			return false;
		}else{
			paymentCityMapper.addPaymentCity(paymentCity);
		}
		return true;
	}
	//实现查询分期城市单条信息
	@Override
	public PaymentCity getPamentCityByid(int cityId) {
		return paymentCityMapper.getPamentCityByid(cityId);
	}
	//实现城市编辑信息
	@Override
	public void updatePaymentCity(PaymentCity paymentCity) {
		paymentCityMapper.deitPaymentCity(paymentCity);
	}
	// 实现分期城市删除信息
	@Override
	public void deletePaymentCity(int cityId) {
		 
		paymentCityMapper.delPaymentCity(cityId);
	}
	@Override
	public List<PaymentCity> listPayment(PaymentCity paymentCity) {
		 
		return paymentCityMapper.listPayment(paymentCity);
	}
}
