package com.app.util;

import java.util.ArrayList;
import java.util.List;

public class PageBean<T> {
	private int pageNo; 
	private int pageSize; 
	private int totalRecordes;
	@SuppressWarnings("unused")
	private int totalPages;
	private List<T> list = new ArrayList<T>();

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getTotalPages() {
		if(totalRecordes ==0){
			return 0;
		}else{
			return totalRecordes%pageSize==0?totalRecordes/pageSize:totalRecordes/pageSize+1;
		}
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecordes() {
		return totalRecordes;
	}

	public void setTotalRecordes(int totalRecordes) {
		this.totalRecordes = totalRecordes;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}


	public int getPageTotalPages() {
		return (this.getTotalRecordes() + this.pageSize - 1) / this.pageSize;
	}


	public int getUpprPage() {
		if (this.pageNo == 1) {
			return 1;
		}
		return this.pageNo - 1;
	}


	public int getDownPage() {
		if (this.pageNo == this.getEndPage()) {
			return this.getEndPage();
		}
		return this.pageNo + 1;
	}


	public int getHomePage() {
		return 1;
	}


	public int getEndPage() {
		return this.getPageTotalPages();
	}

}
