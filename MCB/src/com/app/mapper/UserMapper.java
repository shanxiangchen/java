package com.app.mapper;

import java.util.List;

import com.app.entity.User;

public interface UserMapper  {
	List<User> listAllUser();
	User getUserById(Integer userId);
	
	void insertUser(User user);
	void updateUserBaseInfo(User user);

	void updateUser(User user);
	
	User getUserInfo(User user);
	
	
	void updateUserRights(User user);
	
	int getCountByName(String loginname);
	
	void deleteUser(int userId);
	
	List<User> userSela(User user);
	
	List<User> listPageUser(User user);
	List<User> listUserByUser(User user);
	int getCount(User user);
	User getUserAndRoleById(Integer userId);
	//更新
	void updateLastLogin(User user);
	public void updateUsers(User user);
	public List<User> userSel(int userId);
	
	public User getUserByName(String userName);
}
