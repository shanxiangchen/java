package com.app.entity;

import java.io.Serializable;

public class Role implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer roleId;
	private String roleName;
	private String rights;
	private String permissionsCategory;
	private String roleTwo;
	private Page page;
	private int PageNo;
	private int pageSize;
	private Integer createId;

	public Role() {
	}
	
	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRights() {
		return rights;
	}

	public void setRights(String rights) {
		this.rights = rights;
	}
	
	public int getPageNo() {
		return PageNo;
	}

	public void setPageNo(int pageNo) {
		PageNo = pageNo;
	}

	public String getPermissionsCategory() {
		return permissionsCategory;
	}

	public void setPermissionsCategory(String permissionsCategory) {
		this.permissionsCategory = permissionsCategory;
	}

	public String getRoleTwo() {
		return roleTwo;
	}

	public void setRoleTwo(String roleTwo) {
		this.roleTwo = roleTwo;
	}

	public Page getPage() {
		if (page == null)
			page = new Page();
		return page;
	}
	
	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	/**
	 * @return the createId
	 */
	public Integer getCreateId() {
		return createId;
	}


	/**
	 * @param createId the createId to set
	 */
	public void setCreateId(Integer createId) {
		this.createId = createId;
	}

}
