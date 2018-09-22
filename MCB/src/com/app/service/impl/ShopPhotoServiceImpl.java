package com.app.service.impl;

import java.util.List;

import com.app.entity.ShopPhoto;
import com.app.mapper.ShopPhotoMapper;
import com.app.service.ShopPhotoService;

public class ShopPhotoServiceImpl implements ShopPhotoService{
		private ShopPhotoMapper shopPhotoMapper;
	
		public ShopPhotoMapper getShopPhotoMapper() {
			return shopPhotoMapper;
		}
	
		public void setShopPhotoMapper(ShopPhotoMapper shopPhotoMapper) {
			this.shopPhotoMapper = shopPhotoMapper;
		}
	
		@Override
		public List<ShopPhoto> findAllShopPhoto(ShopPhoto shopphoto) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.findAllShopPhoto(shopphoto);
		}
	
		@Override
		public int insertShopPhoto(ShopPhoto shopphoto) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.insertShopPhoto(shopphoto);
		}
	
		@Override
		public String findAllShop() {
			// TODO Auto-generated method stub
			return shopPhotoMapper.findAllShop();
		}
	
		 
		@Override
		public int deleteshopshoto(String oddsshopid) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.deleteshopshoto(oddsshopid);
		}
	
		@Override
		public void saverShopPhoto(ShopPhoto shopphoto) {
			// TODO Auto-generated method stub
			shopPhotoMapper.updateShopPhoto(shopphoto);
		}
	
		@Override
		public List<ShopPhoto> oddsShopIdByid(String oddsShopId) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.oddsShopIdByid(oddsShopId);
		}
	
		@Override
		public List<ShopPhoto> selectAlbumbId(String photoalbumid) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.selectAlbumbId(photoalbumid);
		}
		@Override
		public void delAlbumbId(String photoalbumid) {
			// TODO Auto-generated method stub
			shopPhotoMapper.delAlbumbId(photoalbumid);
		}
		/***
		 * 实现通过其他图片表中Id来查询对应的图片信息
		 */
		@Override
		public ShopPhoto photoalbumidByid(int photoalbumid) {
			// TODO Auto-generated method stub
			return shopPhotoMapper.photoalbumidByid(photoalbumid);
		}
		/**
		 *  实现按图片表的Id删除相对应的图片信息
		 */
		@Override
		public void deleteShopPhoto(String photoalbumid) {
			// TODO Auto-generated method stub
			shopPhotoMapper.deleteShopPhoto(photoalbumid);
		}
	}




	

	


	

	



	

	


