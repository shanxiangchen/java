package com.app.mapper;

import java.util.List;

import com.app.entity.Express;

public interface ExpressMapper {
	/**
	 * 查询快递所有信息
	 * @param express
	 * @return
	 */
	public List<Express> listPageExpress (Express express);
	 
	
	/**
	 *  新增快递信息
	 */
	public void insertExpress(Express express);
	
	/**
	 * 删除快递信息
	 * @param cardId
	 */
	public void deleteExpress(int expressServiceNameId);
	
	/**
	 * 查询快递单个值信息数据
	 * @param expressServiceNameId
	 * @return
	 */
	public Express getExpressById(int expressServiceNameId);
	
	/**
	 * 修改快递信息
	 * @param express
	 */
	public void updatexpressInfo(Express express);
}
