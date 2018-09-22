package com.app.mapper;

import java.util.List;

import com.app.entity.ErrorCode;

public interface ErrorCodeMapper {
	/**
	 * 查询所有错误码信息数据
	 * @param errorCode
	 * @return
	 */
	public List<ErrorCode> listPageErrorCode(ErrorCode errorCode);
	 
	
	/**
	 * 添加错误码信息数据
	 * @param errorCode
	 */
	public void insertErrorCode(ErrorCode errorCode);
	/**
	 * 删除错误码信息数据
	 * @param errorCodeId
	 */
	public void deleteErrorCode(int errorCodeId);
	/**
	 * 查询单个信息数据
	 * @param errorCodeId
	 * @return
	 */
	public ErrorCode getErrorCodeById(int errorCodeId);
	/**
	 * 修改错误码信息数据
	 * @param errorCode
	 */
	public void updaterrorCodeInfo(ErrorCode errorCode);
}
