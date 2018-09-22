package com.dao;

import java.util.List;

import com.entity.User;

public interface UserDao {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int userId);

	public User queryUserByUserId(int userId);

	public User queryUserByUserName(String userName);

	public List<User> queryAllUsers();

}
