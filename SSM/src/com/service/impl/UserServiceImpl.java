package com.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.UserDao;
import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	@SuppressWarnings("rawtypes")
	RedisTemplate redisTemplate;

	public void addUser(User user) {
		userDao.addUser(user);
	}

	@Transactional
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
					result = "success";
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

	public List<User> getUserById(String userId) {
		return null;
	}

	public User getUserByUserName(String userName) {
		User user = userDao.queryUserByUserName(userName);
		return user;

	}

	@SuppressWarnings("unchecked")
	public List<User> listAllUsers() {
		List<User> userList = userDao.queryAllUsers();
		for (int i = 0; i < userList.size(); i++) {
			User user = userList.get(i);
			redisTemplate.opsForList().rightPush("userList", user);
			User users = (User) redisTemplate.opsForList().index("userList", i);
			logger.debug("UserName:" + users.getUserName() + " NameCn:" + users.getNameCn());
		}
		long size = redisTemplate.opsForList().size("userList");
		logger.debug("Redis缓存数量：" + size);
		List<User> valueList = redisTemplate.opsForList().range("userList", 0, size);
		logger.debug("Redis缓存用户：" + valueList);
		for (int i = 0; i < valueList.size(); i++) {
			User user = (User) redisTemplate.opsForList().leftPop("userList");
			logger.debug("UserName:" + user.getUserName() + " NameCn:" + user.getNameCn());
			logger.debug("Redis缓存数量：" + redisTemplate.opsForList().size("userList"));
		}
		return userList;
	}

	public String logout() {
		return null;
	}

}
