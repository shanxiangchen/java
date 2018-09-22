package com.app.entity;
/**
 * 支付方式实体类
 * create data 2015/11/16
 * @author shiguangting@tansun.com.cn
 *
 */
public class Paytype {
 private Integer paytpeId;//
 private String  paytypeCode;
 private String  paytypeNmae;
 private String  remark;
 private String  hold1;
 private String  hold2;
 
 
public Integer getPaytpeId() {
	return paytpeId;
}
public void setPaytpeId(Integer paytpeId) {
	this.paytpeId = paytpeId;
}
public String getPaytypeCode() {
	return paytypeCode;
}
public void setPaytypeCode(String paytypeCode) {
	this.paytypeCode = paytypeCode;
}
public String getPaytypeNmae() {
	return paytypeNmae;
}
public void setPaytypeNmae(String paytypeNmae) {
	this.paytypeNmae = paytypeNmae;
}
public String getRemark() {
	return remark;
}
public void setRemark(String remark) {
	this.remark = remark;
}
public String getHold1() {
	return hold1;
}
public void setHold1(String hold1) {
	this.hold1 = hold1;
}
public String getHold2() {
	return hold2;
}
public void setHold2(String hold2) {
	this.hold2 = hold2;
}
 
 
 
}
