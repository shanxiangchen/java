package com.database;

import java.sql.Date;

public class OutInputInfo {

	public String policy_no;
	public String doc_type;
	public Double status;
	public Date sys_date;
	public String account_select;
	
	public Double getStatus() {
		return status;
	}

	public void setStatus(Double status) {
		this.status = status;
	}

	public String getPolicy_no() {
		return policy_no;
	}

	public void setPolicy_no(String policy_no) {
		this.policy_no = policy_no;
	}

	public String getDoc_type() {
		return doc_type;
	}

	public void setDoc_type(String doc_type) {
		this.doc_type = doc_type;
	}

	public Date getSys_date() {
		return sys_date;
	}

	public void setSys_date(Date sys_date) {
		this.sys_date = sys_date;
	}

	public String getAccount_select() {
		return account_select;
	}

	public void setAccount_select(String account_select) {
		this.account_select = account_select;
	}

}
