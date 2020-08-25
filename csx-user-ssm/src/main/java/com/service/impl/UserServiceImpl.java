package com.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.mapper.UserDao;
import com.model.User;
import com.service.UserService;
import com.task.DemoTask;

@Service
public class UserServiceImpl implements UserService {

	private static Logger logger = LoggerFactory.getLogger(DemoTask.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	RedisTemplate redisTemplate;

	public void addUser(User user) {
		userDao.addUser(user);
	}

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
			logger.info("UserName:" + users.getUserName() + " NameCn:" + users.getNameCn());
		}
		long size = redisTemplate.opsForList().size("userList");
		logger.info("Redis缓存数量：" + size);
		List<User> valueList = redisTemplate.opsForList().range("userList", 0, size);
		logger.info("Redis缓存用户：" + valueList);
		for (int i = 0; i < valueList.size(); i++) {
			User user = (User) redisTemplate.opsForList().leftPop("userList");
			logger.info("UserName:" + user.getUserName() + " NameCn:" + user.getNameCn());
			logger.info("Redis缓存数量：" + redisTemplate.opsForList().size("userList"));
		}
		return userList;
	}

	public String logout() {
		return null;
	}

}
