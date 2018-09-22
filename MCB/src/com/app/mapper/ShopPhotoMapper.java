package com.app.mapper;

import java.util.List;

import com.app.entity.ShopPhoto;

public interface ShopPhotoMapper {
	public List<ShopPhoto> findAllShopPhoto(ShopPhoto shopphoto);
	int insertShopPhoto(ShopPhoto shopphoto);
	String findAllShop();
	 
	int deleteshopshoto(String oddsshopid);
	int updateShopPhoto(ShopPhoto shopphoto);
	/**
	 * 查询ShopPhotob表中图片
	 * @param oddsShopId
	 * @return
	 */
	public List<ShopPhoto> oddsShopIdByid(String oddsShopId);
	/***
	 * 
	 * @param photoalbumid
	 * @return
	 */
	public List<ShopPhoto> selectAlbumbId(String photoalbumid);
	/***
	 * 
	 * @param photoalbumid
	 */
	public void delAlbumbId(String photoalbumid);
	/***
	 * 实现通过其他图片表中Id来查询对应的图片信息
	 */
	public ShopPhoto photoalbumidByid(int photoalbumid);
	/**
	 * 实现按图片表的Id删除相对应的图片信息
	 */
	public void deleteShopPhoto(String photoalbumid);
}
