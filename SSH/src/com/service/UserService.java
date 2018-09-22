package com.service;

import java.util.List;

import com.entity.User;

public interface UserService {

	public String login(String userName, String password);

	public String logout();

	public List<User> listAllUsers();

}
