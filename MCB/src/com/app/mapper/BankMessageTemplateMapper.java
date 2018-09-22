package com.app.mapper;

import java.util.List;

import com.app.entity.BankCodeInfo;
import com.app.entity.BankMessageParameterConf;
import com.app.entity.BankMessageSmallType;
import com.app.entity.BankMessageTemplate;

/**
 * 消息模板配置xml接口
 * create date 2016/4/14
 * <br/>
 * @author shiguangting@tansun.cn.com
 *
 */
public interface BankMessageTemplateMapper {
	/**
	 * 查询信息模板，分页
	 * @param bankMessageTemplate
	 * @return
	 */
	public List<BankMessageTemplate>bankMessageTemplatePageList(BankMessageTemplate bankMessageTemplate);
	/**
	 * 数据字典
	 * @return
	 */
	public	List<BankCodeInfo>  listBankCodeInfo();
	/**
	 * 添加消息模板信息
	 * @param bankMessageTemplate
	 */
	public void saveBankMessageTemplate(BankMessageTemplate bankMessageTemplate);
	/**
	 * 查询消息模板单条信息
	 * @param messageTemplateId
	 * @return
	 */
	public BankMessageTemplate getMessageTemplateById(int messageTemplateId);
	/**
	 * 编辑消息模板信息
	 * @param bankMessageTemplate
	 */
	public void editMessageTemplate(BankMessageTemplate bankMessageTemplate);
	/**
	 * 删除消息模板信息
	 * @param messageTemplateId
	 */
	public void delMessageTemplate(int messageTemplateId);
	
	/**
	 * 查询消息盒子
	 * @return
	 */
	public List<BankMessageParameterConf> bankMessageParameterConfList();
	/**
	 * 查询具体类型
	 * @param typNo
	 * @return
	 */
	public List<BankMessageSmallType>bankMessageSmallTypeList(String typeNo);
}
