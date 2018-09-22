package com.app.service;

import java.util.List;

import com.app.entity.User;


public interface UserService {
	User getUserById(Integer userId);
	
	boolean insertUser(User user);

	void updateUserBaseInfo(User user);
	
	void updateUser(User user);
	
	User getUserByNameAndPwd(String username,String password);
	
	List<User> userSela(User user);
	void updateUserRights(User user);
	
	void deleteUser(int userId);
	
	List<User> listUser(User user);
	
	void updateLastLogin(User user);
	
	User getUserAndRoleById(Integer userId);
	
	List<User> listAllUser();
	public void updateUsers(User user);
	/**
	 * 查询全部子级及以下用户信息--不包含自身
	 * @param userId
	 * @return
	 */
	public List<User> userSel(int userId);

}
