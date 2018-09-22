package com.dao.impl;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.session.SqlSession;

import com.dao.UserDao;
import com.factory.SqlSessionFactoryUtils;
import com.model.User;

public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		try {
			mapper.addUser(user);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public void updateUser(User user) {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		try {
			mapper.updateUser(user);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	// 可以用注解方式定义映射器，如果同时有xml方式，这xml方式会覆盖掉租界方式
	@Delete("delete from user_info where user_id=#{userId}")
	public void deleteUser(int userId) {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		try {
			mapper.deleteUser(1);
			session.commit();
		} catch (Exception e) {
			session.rollback();
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
	}

	public User queryUserByUserId(int userId) {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		User user = null;
		try {
			user = mapper.queryUserByUserId(userId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	public User queryUserByUserName(String userName) {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		User user = null;
		try {
			user = mapper.queryUserByUserName(userName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return user;
	}

	public List<User> queryAllUsers() {
		SqlSession session = SqlSessionFactoryUtils.getSession();
		UserDao mapper = session.getMapper(UserDao.class);
		List<User> userList = null;
		try {
			userList = mapper.queryAllUsers();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (session != null) {
				session.close();
			}
		}
		return userList;
	}

}
