package com.app.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.app.entity.Role;
import com.app.entity.User;
import com.app.mapper.RoleMapper;
import com.app.service.RoleService;
 
public class RoleServiceImpl implements RoleService{

	private RoleMapper roleMapper;
	
	
	
	private void getRoleByRoles(List<Role> roles){
		if(!roles.isEmpty()){
			for(int i=0,b=roles.size();i<b;i++){
				//获取子级列表
				List<Role> childrenRoles = roleMapper.getRolesByRoleTwo(roles.get(i));
				if(!childrenRoles.isEmpty()){
					//递归调用
					getRoleByRoles(childrenRoles);
					//添加到到roles
					for(int j=0,c=childrenRoles.size();j<c;j++){
						roles.add(childrenRoles.get(j));
					}
				}
			}
		}
	}
	/**
	 * 查询角色
	 * @author HuangCheng
	 * 
	 */
	@Override
	public List<Role> allRolesPageList(Role role) {
		//获取所有角色
		List<Role> rolesBySearch = new ArrayList<Role>();
		List<Role> roles = roleMapper.allRolesPageList(role);
		getRoleByRoles(roles);
		String searchRoleName = role.getRoleName();
		if(null != searchRoleName &&"null" != searchRoleName && ""!= searchRoleName ){
			for(int i=0,b=roles.size();i<b;i++){
				if(roles.get(i).getRoleName().indexOf(searchRoleName)>=0){
					rolesBySearch.add(roles.get(i));
				}
			}
			return rolesBySearch;
		}else{
			return roles;
		}
	}
	
	@Override
	public List<Role> listAllRoless(Role role) {
		 
		return roleMapper.listAllRoless(role);
	}
	@Override
	public List<Role> listAllRolesSel(String roleTwo) {
		 
		return roleMapper.listAllRolesSel(roleTwo);
	}
	
	
	public void deleteRoleById(int roleId) {
		 
		roleMapper.deleteRoleById(roleId);
	}

	public Role getRoleById(int roleId) {
		 
		return roleMapper.getRoleById(roleId);
	}

	public boolean insertRole(Role role) {
		 
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.insertRole(role);
			return true;
		}
	}

	public boolean updateRoleBaseInfo(Role role) {
	 
		if(roleMapper.getCountByName(role)>0)
			return false;
		else{
			roleMapper.updateRoleBaseInfo(role);
			return true;
		}
	}
	
	public void updateRoleRights(Role role) {
	 
		roleMapper.updateRoleRights(role);
	}

	public RoleMapper getRoleMapper() {
		return roleMapper;
	}

	public void setRoleMapper(RoleMapper roleMapper) {
		this.roleMapper = roleMapper;
	}
	@Override
	public String getListAllRoless(String roleId) {
		 
		return roleMapper.getListAllRoless(roleId);
	}
	@Override
	public List<Role> listAllRolesSels(String roleId) {
		 
		return roleMapper.listAllRolesSels(roleId);
	}
	@Override
	public List<Role> listAllRoleByAdmin() {
		 
		return roleMapper.listAllRoleByAdmin();
	}
	@Override
	public int getUserNumById(int roleId) {
		return roleMapper.getUserNumById(roleId);
	}
	@Override
	public List<Role> listAllChildRole(List<User> userList,Role role) {
		Map<String,Object> reqMap = new HashMap<String, Object>();
		reqMap.put("userList", userList);
		reqMap.put("role",role);
		List<Role> roles = roleMapper.getAllChildRole(reqMap);
		return roles;
	}
	
	
}
