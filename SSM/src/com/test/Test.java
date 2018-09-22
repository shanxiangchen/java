package com.test;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.dao.UserDao;
import com.dao.impl.UserDaoImpl;
import com.factory.RedisFactoryUtils;
import com.factory.SqlSessionFactoryUtils;
import com.model.User;

import redis.clients.jedis.Jedis;

public class Test {

	public static void main(String[] args) {
		redisTest();
		// mybatisTest();
	}

	public static void redisTest() {
		Jedis jedis = RedisFactoryUtils.getJedis();
		int i = 0;
		try {
			long start = System.currentTimeMillis();
			while (true) {
				long end = System.currentTimeMillis();
				if (end - start >= 1000) {
					break;
				}
				jedis.set("test" + i, i + "test");
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			jedis.close();
		}
		System.out.println("Ã¿Ãë²Ù×÷Êý£º" + i);
	}

	public static void mybatisTest() {
		UserDao userDao = new UserDaoImpl();
		List<User> userList = userDao.queryAllUsers();
		for (User user : userList) {
			System.out.println("UserName:" + user.getUserName() + " NameCn:" + user.getNameCn());
		}
		SqlSession session = SqlSessionFactoryUtils.getSession();
		try {
			userList = session.selectList("queryAllUsers");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		for (User user : userList) {
			System.out.println("UserName:" + user.getUserName() + " NameCn:" + user.getNameCn());
		}
	}

}
