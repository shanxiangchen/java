package com.app.mapper;

import java.util.List;
import java.util.Map;

import com.app.entity.Role;
public interface RoleMapper {
	List<Role> allRolesPageList(Role role);
	List<Role> listAllRoless(Role role);
	int getCount(Role role);
	Role getRoleById(int roleId);
	void insertRole(Role role);
	void updateRoleBaseInfo(Role role);
	void deleteRoleById(int roleId);
	int getCountByName(Role role);
	void updateRoleRights(Role role);
	public String getListAllRoless(String roleId);
	List<Role> listAllRolesSel(String roleTwo);
	List<Role> listAllRolesSels(String roleId);
	List<Role> listAllRoleByAdmin();
	List<Role> getRolesList(Role role);
	List<Role> getRolesListByNull(Role role);
	List<Role> getRolesByRoleTwo(Role role);
	int getUserNumById(int roleId);
	List<Role> getAllChildRole(Map<String,Object> reqMap);
}
