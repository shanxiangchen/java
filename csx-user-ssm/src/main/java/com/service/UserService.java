package com.service;

import java.util.List;

import com.model.User;

public interface UserService {
	
	public void addUser(User user);

	public String login(String userName, String password);
	
	public List<User> getUserById(String userId);
	
	public User getUserByUserName(String userName);

	public List<User> listAllUsers();
	
	public String logout();
	
}
