package com.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class User  implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String loginname;
	private String username;
	private String password;
	private String rights;
	private String status;
	private Integer roleId;
	private Date lastLogin;
	private String manageno;  //管理员编号
	private String managedescribe;  //管理员描述
	private String permissionsCategory;
	private List<Integer> roleIdList;
	private List<Integer> userIdList;
	private Page page;
	private Integer createId;
	 
	public User(){}


	public Integer getCreateId() {
		return createId;
	}


	public void setCreateId(Integer createId) {
		this.createId = createId;
	}


	public List<Integer> getUserIdList() {
		return userIdList;
	}
	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}
	public List<Integer> getRoleIdList() {
		return roleIdList;
	}
	public void setRoleIdList(List<Integer> roleIdList) {
		this.roleIdList = roleIdList;
	}


	private Role role;
	 
	public String getManageno() {
		return manageno;
	}
	public void setManageno(String manageno) {
		this.manageno = manageno;
	}

	public String getManagedescribe() {
		return managedescribe;
	}
	public void setManagedescribe(String managedescribe) {
		this.managedescribe = managedescribe;
	}

	
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	public String getLoginname() {
		return loginname;
	}
	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Date getLastLogin() {
		Date lastLogins=lastLogin;
		return lastLogins;
	}
	public void setLastLogin(Date lastLogin) {
		if(lastLogin != null){
			this.lastLogin = (Date) lastLogin.clone();
			}else{
			this.lastLogin = null;
			}
	}


	public String getPermissionsCategory() {
		return permissionsCategory;
	}


	public void setPermissionsCategory(String permissionsCategory) {
		this.permissionsCategory = permissionsCategory;
	}


	public Page getPage() {
		if(page==null)
			page = new Page();
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	
	
}
