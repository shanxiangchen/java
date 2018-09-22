package com.factory;

import java.io.Reader;
import java.io.Serializable;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class SqlSessionFactoryUtils implements Serializable {

	private static final long serialVersionUID = 647378391843028578L;
	private static SqlSessionFactory sessionFactory;

	private SqlSessionFactoryUtils() {
	}

	static {
		try {
			// ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�
			Reader reader = Resources.getResourceAsReader("com/mapper/mybatis.cfg.xml");
			// ����sqlSession�Ĺ���
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// ������ִ��ӳ���ļ���sql��sqlSession
	public static SqlSession getSession() {
		if (sessionFactory == null) {
			rbuildSessionFactory();
		}
		return sessionFactory.openSession();
	}

	public static synchronized void rbuildSessionFactory() {
		try {
			// ʹ��MyBatis�ṩ��Resources�����mybatis�������ļ�
			Reader reader = Resources.getResourceAsReader("com/mapping/mybatis.cfg.xml");
			// ����sqlSession�Ĺ���
			sessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (Exception e) {
			System.err.println("%%%% Error Creating SessionFactory %%%%");
			e.printStackTrace();
		}
	}

	public static void closeSession(SqlSession session) {
		if (session != null) {
			session.close();
		}
	}

}
