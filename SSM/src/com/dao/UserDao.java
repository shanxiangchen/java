package com.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.model.User;

@Repository
public interface UserDao {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int userId);

	public User queryUserByUserId(int userId);

	public User queryUserByUserName(String userName);

	public List<User> queryAllUsers();

}
