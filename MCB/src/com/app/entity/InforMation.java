package com.app.entity;

/**
 * 客户信息实体类 
 * @author zhuhao
 * @date 2016-3-15
 *
 */
public class InforMation {

	private String custId; //主键ID
	private String custName; //客户姓名
	private String custNameAbc; //姓名拼音
	private String custSex; //客户性别 1.男 2 女
	private String custEmail; //客户邮箱
	private String custIsmarry; //婚姻状况 1.未婚 2.已婚 3.其他
	private String custEducation; //教育程度 1.高中及以下 2.大专 3.本科 4.研究生 5.硕士 6.博士 7.其他
	private String custNowAddress; //当前住址
	private String custMoreAddress; //详细地址
	private String custZipCode; //住宅邮编
	private String custPhoneNum; //手机号码
	private String custCardId; //客户身份证号
	private String custWorkUnit; //工作单位
	private String custUnitPhone; //单位电话
	private String custUnitAddress; //单位地址
	private String custUnitMoreAddress; //单位详细地址
	private String custUnitZipcode; //单位邮编
	private String custWorkAge; //现工龄单位 1.6个月以内 2.6（含）-12个月 3.12（含）-24个月 4.24（含）-36个月 5.36（含）个月以上
	private double custYearManey; //年收入（万)
	private String custUnitType; //单位性质：1.政府机关、社会团体 2.教育、科研 3.公用事业单位 4.基层群众自治团体 5.电信/计算机/信息传输 6.商业贸易、批发零售 7.金融业 8.建筑业 9.服务业 10.旅游/酒店/餐饮 11.医疗卫生
	private String custUnitDept; //任职部门
	private String custUnitPost; //单位职务：1.高层主管 2.中层主管 3.基层主管 4.职员 5.一线工人 6.销售人员 7.编外人员 8.自由主业 9 服务人员 10.个体工商户 11.其他
	private String custCarNum;  //车牌号
	private String custCardIsok; //证件有效期 1.长期 2.有效期限
	private String custCardDate; //证件有效截止日
	private String custIsReco; //是否他人推荐 1.是 2.否
	private String custRecoId; //推荐人电话工号
	private String custIsOtherCard; //是否有他行卡 1.是 2.否
	private String custOtherCardDate; //持有时间 1.1年内 2.1至2年 3.2年以上
	private String custFamilyName; //配偶/直系亲属姓名
	private String custFamilyNexus; //与申请人关系 ：1.配偶 2.父母 3.兄弟姐妹
	private String custFamilyPhone; //联系人手机
	private String praiseOne; //预留字段
	private String praiseTwo; //预留字段
	private int PageNo;
	private int PageSize;
	
	
	
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustNameAbc() {
		return custNameAbc;
	}
	public void setCustNameAbc(String custNameAbc) {
		this.custNameAbc = custNameAbc;
	}
	public String getCustSex() {
		return custSex;
	}
	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustIsmarry() {
		return custIsmarry;
	}
	public void setCustIsmarry(String custIsmarry) {
		this.custIsmarry = custIsmarry;
	}
	public String getCustEducation() {
		return custEducation;
	}
	public void setCustEducation(String custEducation) {
		this.custEducation = custEducation;
	}
	public String getCustNowAddress() {
		return custNowAddress;
	}
	public void setCustNowAddress(String custNowAddress) {
		this.custNowAddress = custNowAddress;
	}
	public String getCustMoreAddress() {
		return custMoreAddress;
	}
	public void setCustMoreAddress(String custMoreAddress) {
		this.custMoreAddress = custMoreAddress;
	}
	public String getCustZipCode() {
		return custZipCode;
	}
	public void setCustZipCode(String custZipCode) {
		this.custZipCode = custZipCode;
	}
	public String getCustPhoneNum() {
		return custPhoneNum;
	}
	public void setCustPhoneNum(String custPhoneNum) {
		this.custPhoneNum = custPhoneNum;
	}
	public String getCustCardId() {
		return custCardId;
	}
	public void setCustCardId(String custCardId) {
		this.custCardId = custCardId;
	}
	public String getCustWorkUnit() {
		return custWorkUnit;
	}
	public void setCustWorkUnit(String custWorkUnit) {
		this.custWorkUnit = custWorkUnit;
	}
	public String getCustUnitPhone() {
		return custUnitPhone;
	}
	public void setCustUnitPhone(String custUnitPhone) {
		this.custUnitPhone = custUnitPhone;
	}
	public String getCustUnitAddress() {
		return custUnitAddress;
	}
	public void setCustUnitAddress(String custUnitAddress) {
		this.custUnitAddress = custUnitAddress;
	}
	public String getCustUnitMoreAddress() {
		return custUnitMoreAddress;
	}
	public void setCustUnitMoreAddress(String custUnitMoreAddress) {
		this.custUnitMoreAddress = custUnitMoreAddress;
	}
	public String getCustUnitZipcode() {
		return custUnitZipcode;
	}
	public void setCustUnitZipcode(String custUnitZipcode) {
		this.custUnitZipcode = custUnitZipcode;
	}
	public String getCustWorkAge() {
		return custWorkAge;
	}
	public void setCustWorkAge(String custWorkAge) {
		this.custWorkAge = custWorkAge;
	}
	public double getCustYearManey() {
		return custYearManey;
	}
	public void setCustYearManey(double custYearManey) {
		this.custYearManey = custYearManey;
	}
	public String getCustUnitType() {
		return custUnitType;
	}
	public void setCustUnitType(String custUnitType) {
		this.custUnitType = custUnitType;
	}
	public String getCustUnitDept() {
		return custUnitDept;
	}
	public void setCustUnitDept(String custUnitDept) {
		this.custUnitDept = custUnitDept;
	}
	public String getCustUnitPost() {
		return custUnitPost;
	}
	public void setCustUnitPost(String custUnitPost) {
		this.custUnitPost = custUnitPost;
	}
	public String getCustCarNum() {
		return custCarNum;
	}
	public void setCustCarNum(String custCarNum) {
		this.custCarNum = custCarNum;
	}
	public String getCustCardIsok() {
		return custCardIsok;
	}
	public void setCustCardIsok(String custCardIsok) {
		this.custCardIsok = custCardIsok;
	}
	public String getCustCardDate() {
		return custCardDate;
	}
	public void setCustCardDate(String custCardDate) {
		this.custCardDate = custCardDate;
	}
	public String getCustIsReco() {
		return custIsReco;
	}
	public void setCustIsReco(String custIsReco) {
		this.custIsReco = custIsReco;
	}
	public String getCustRecoId() {
		return custRecoId;
	}
	public void setCustRecoId(String custRecoId) {
		this.custRecoId = custRecoId;
	}
	public String getCustIsOtherCard() {
		return custIsOtherCard;
	}
	public void setCustIsOtherCard(String custIsOtherCard) {
		this.custIsOtherCard = custIsOtherCard;
	}
	public String getCustOtherCardDate() {
		return custOtherCardDate;
	}
	public void setCustOtherCardDate(String custOtherCardDate) {
		this.custOtherCardDate = custOtherCardDate;
	}
	public String getCustFamilyName() {
		return custFamilyName;
	}
	public void setCustFamilyName(String custFamilyName) {
		this.custFamilyName = custFamilyName;
	}
	public String getCustFamilyNexus() {
		return custFamilyNexus;
	}
	public void setCustFamilyNexus(String custFamilyNexus) {
		this.custFamilyNexus = custFamilyNexus;
	}
	public String getCustFamilyPhone() {
		return custFamilyPhone;
	}
	public void setCustFamilyPhone(String custFamilyPhone) {
		this.custFamilyPhone = custFamilyPhone;
	}
	public String getPraiseOne() {
		return praiseOne;
	}
	public void setPraiseOne(String praiseOne) {
		this.praiseOne = praiseOne;
	}
	public String getPraiseTwo() {
		return praiseTwo;
	}
	public void setPraiseTwo(String praiseTwo) {
		this.praiseTwo = praiseTwo;
	}
	public int getPageNo() {
		return PageNo;
	}
	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	
	
	
	
	
	
	
}
