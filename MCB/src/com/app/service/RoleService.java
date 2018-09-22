package com.app.service;


import java.util.List;

import com.app.entity.Role;
import com.app.entity.User;
public interface RoleService {
	List<Role> allRolesPageList(Role role);
	List<Role> listAllRoless(Role role);
	Role getRoleById(int roleId);
	boolean insertRole(Role role);
	boolean updateRoleBaseInfo(Role role);
	void deleteRoleById(int roleId);
	void updateRoleRights(Role role);
	public String getListAllRoless(String roleId);
	List<Role>listAllRolesSel(String roleTwo);
	List<Role>listAllRolesSels(String roleId);
	List<Role> listAllRoleByAdmin();
	int getUserNumById(int roleId);
	/**
	 * 获取全部子级角色
	 * @param roleTwo
	 * @return
	 */
	List<Role> listAllChildRole(List<User> userList,Role role);
}
