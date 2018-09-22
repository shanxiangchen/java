package com.app.service;

import java.util.List;

import com.app.entity.ShopPhoto;

public interface ShopPhotoService {
	public List<ShopPhoto> findAllShopPhoto(ShopPhoto shopphoto);
	int insertShopPhoto(ShopPhoto shopphoto);
	String findAllShop();
	 
	int deleteshopshoto(String oddsshopid);
	void saverShopPhoto(ShopPhoto shopphoto);
	/**
	 * 查询ShopPhotob表中链接地址
	 * @param oddsShopId
	 * @return
	 */
	public List<ShopPhoto> oddsShopIdByid(String oddsShopId);
	
	public List<ShopPhoto> selectAlbumbId(String photoalbumid);
	void delAlbumbId(String photoalbumid);
	
	/***
	 * 实现通过其他图片表中Id来查询对应的图片信息
	 */
	public ShopPhoto photoalbumidByid(int photoalbumid);
	/**
	 * 实现按图片表的Id删除相对应的图片信息
	 */
	public void deleteShopPhoto(String photoalbumid);
}
