package com.app.service;
import java.util.List;

import com.app.entity.HappyPooll;

public interface HappyPoollService {
	/**
		* 分页
		* @param hp
		* @return
	*/
	public List<HappyPooll> listPageHappyPooll(HappyPooll happyPooll);
	
/*	*//**
	 * 保存
	 * @param hs
	 * @return
	 *//*
	public void saveAappypooll(HappyPooll hs);*/
		
	/**
	 * 删除
	 * @param de
	 */
	public void delete(String happyPoollId);
	
	/**
	 * 修改
	 * @param hu
	 * @return
	 **/
	public boolean update(HappyPooll hu);
	
	/**
	 * 按ID查询
	 * @param happyPoollId
	 */
	public HappyPooll getHappyPoollByid(String happyPoollId);
	
/**
 * 保存唯一标识
 * 
 * @param happyPoollorder
 * @return
 */
	public boolean saveHappyPoolllorder(HappyPooll happyPoollorder);
}
