package com.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.model.User;

@Mapper
public interface UserDao {

	public void addUser(User user);

	public void updateUser(User user);

	public void deleteUser(int userId);

	public User queryUserByUserId(int userId);

	public User queryUserByUserName(String userName);

	public List<User> queryAllUsers();

}
