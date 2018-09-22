package com.dao.impl.spring;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.UserDao;
import com.entity.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	
    private SessionFactory sessionFactory;
    @Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession()
    {
        return sessionFactory.getCurrentSession();
    }

	public void addUser(User user) {
		Session session = this.getCurrentSession();
		session.save(user);
	}

	public void updateUser(User user) {
		Session session = this.getCurrentSession();
		User usern = (User) session.get(User.class, user.getUserId());
		usern.setPassword(user.getPassword());
		usern.setLoginTime(user.getLoginTime());
		session.update(usern);
	}

	public void deleteUser(int userId) {
		Session session = this.getCurrentSession();
		User user = (User) session.get(User.class, userId);
		session.delete(user);
	}

	public User queryUserByUserId(int userId) {
		Session session = this.getCurrentSession();
		User user = (User) session.get(User.class, userId);
		return user;
	}

	public User queryUserByUserName(String userName) {
		Session session = this.getCurrentSession();
		String hql = "from User where userName=:userName";
		Query hqlQuery = session.createQuery(hql).setParameter("userName", userName);
		User user = (User) hqlQuery.uniqueResult();
		return user;
	}

	public List<User> queryAllUsers() {
		Session session = this.getCurrentSession();
		String hql = "from User";
		Query hqlQuery = session.createQuery(hql);
		List<User> list = hqlQuery.list();
		return list;
	}

}
