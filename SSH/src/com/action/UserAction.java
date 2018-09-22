package com.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

@Controller
public class UserAction extends ActionSupport{
	
	private static final long serialVersionUID = 5101154058509571526L;
	private String userName;
	private String password;
	private List<User> userList;
	private UserService userService;
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	public String execute(){
		String result = userService.login(userName,password);
		if("menu".equals(result)){
			userList = userService.listAllUsers();
		}
		return result;
	}

}
