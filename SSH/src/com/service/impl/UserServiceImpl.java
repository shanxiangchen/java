package com.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserDao;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public String login(String userName, String password) {
		logger.debug("开始登陆1！");
		logger.error("开始登陆2！");
		String result = "";
		if (userName != null && password != null) {
			User user = userDao.queryUserByUserName(userName);
			if (user != null) {
				if (password.equals(user.getPassword())) {
					user.setFailTimes(0);
					user.setLoginTime(new Date());
					userDao.updateUser(user);
					ActionContext actionContext = ActionContext.getContext();
					Map<String, Object> session = actionContext.getSession();
					session.put("user.name", userName);
					result = "menu";
				} else {
					user.setFailTimes(user.getFailTimes() + 1);
					userDao.updateUser(user);
					result = "login";
				}
			} else {
				result = "login";
			}
		} else {
			result = "login";
		}
		return result;
	}

	@Override
	public String logout() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put("user.name", null);
		return "login";
	}

	@Override
	public List<User> listAllUsers() {
		List<User> userList = userDao.queryAllUsers();
		return userList;
	}

}
