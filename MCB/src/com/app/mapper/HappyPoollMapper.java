package com.app.mapper;

import java.util.List;

import com.app.entity.HappyPooll;

public interface HappyPoollMapper {
	/**
	 * 查询
	 * @param hc
	 * @return
	 */
	public List<HappyPooll> listPageHappyPooll(HappyPooll happyPooll);
	public int getCount(HappyPooll happyPooll);

	/**
	 * 增加
	 * @param sa
	 * @return
	 */
	public int savehp(HappyPooll sa);
	
	/**
	 * 删除
	 * @param happyPoollId
	 */
	public void delete(String  happyPoollId);
	
	/**
	 * 修改
	 * @param hu
	 */
	public void update(HappyPooll hu);
	
	/**
	 * 根据ID查询单条数据信息
	 * @param happyPoollId
	 * @return
	 */
	public HappyPooll getHappyPoollByid(String happyPoollId);
	
	/**
	 * 唯一标识
	 * @param wy
	 * @return
	 */
	public int getHappyPoolllorder(HappyPooll happyPooll);
}
