package com.dao.impl.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.entity.User;
import com.factory.SessionFactoryUtils;

@Repository
public class UserDaoImpl implements UserDao {

	public void addUser(User user) {
		Session session = SessionFactoryUtils.getSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		SessionFactoryUtils.closeSession();
	}

	public void updateUser(User user) {
		Session session = SessionFactoryUtils.getSession();
		Transaction tx = session.beginTransaction();
		User usern = (User) session.get(User.class, user.getUserId());
		usern.setPassword(user.getPassword());
		usern.setLoginTime(user.getLoginTime());
		session.update(usern);
		tx.commit();
		SessionFactoryUtils.closeSession();
	}

	public void deleteUser(int userId) {
		Session session = SessionFactoryUtils.getSession();
		User user = (User) session.get(User.class, userId);
		Transaction tx = session.beginTransaction();
		session.delete(user);
		tx.commit();
		SessionFactoryUtils.closeSession();
	}

	public User queryUserByUserId(int userId) {
		Session session = SessionFactoryUtils.getSession();
		User user = (User) session.get(User.class, userId);
		SessionFactoryUtils.closeSession();
		return user;
	}

	public User queryUserByUserName(String userName) {
		Session session = SessionFactoryUtils.getSession();
		String hql = "from User where userName=:userName";
		Query hqlQuery = session.createQuery(hql).setParameter("userName", userName);
		User user = (User) hqlQuery.uniqueResult();
		SessionFactoryUtils.closeSession();
		return user;
	}

	public List<User> queryAllUsers() {
		Session session = SessionFactoryUtils.getSession();
		String hql = "from User";
		Query hqlQuery = session.createQuery(hql);
		List<User> list = hqlQuery.list();
		SessionFactoryUtils.closeSession();
		return list;
	}

}
