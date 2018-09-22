package com.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "user_info")
public class User implements Serializable {

	private static final long serialVersionUID = -556669020757040507L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PKGenerator")
	@SequenceGenerator(name = "PKGenerator", sequenceName = "user_info_seq")
	@Column(name = "user_id", length = 16, nullable = false)
	public int userId;
	@Column(name = "user_name", length = 32, nullable = false)
	public String userName;
	@Column(name = "password", length = 32, nullable = false)
	public String password;
	@Column(name = "name_cn", length = 64, nullable = false)
	public String nameCn;
	@Column(name = "sex", length = 4)
	public String sex;
	@Column(name = "phone", length = 16)
	public String phone;
	@Column(name = "email", length = 64)
	public String email;
	@Column(name = "birthday")
	public Date birthday;
	@Column(name = "login_date")
	public Date loginTime;
	@Column(name = "fail_times", length = 2)
	public int failTimes;
	@Column(name = "used_flag", length = 2)
	public String useFlag;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNameCn() {
		return nameCn;
	}

	public void setNameCn(String nameCn) {
		this.nameCn = nameCn;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}

	public int getFailTimes() {
		return failTimes;
	}

	public void setFailTimes(int failTimes) {
		this.failTimes = failTimes;
	}

	public String getUseFlag() {
		return useFlag;
	}

	public void setUseFlag(String useFlag) {
		this.useFlag = useFlag;
	}

}
