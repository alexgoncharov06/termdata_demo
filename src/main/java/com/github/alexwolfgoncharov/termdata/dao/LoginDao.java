package com.github.alexwolfgoncharov.termdata.dao;



import com.github.alexwolfgoncharov.termdata.interfaces.User;
import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;



public class LoginDao {
	private static final Logger log = Logger.getLogger(LoginDao.class
			.getName());

	public LoginDao() {
	}

	public void add(User login) throws SQLException {
		if (!loginIsTrue(login.getLogin())){
			Session session = null;
			login.setId(getNextId());
			java.util.Date today = new java.util.Date();
			long ltoday = today.getTime();
			login.setTime(new Timestamp(ltoday));
			try {
				session = HibernateMyUtil.getSessionFactory().openSession();
				session.beginTransaction();
				session.saveOrUpdate(login);
				session.getTransaction().commit();
			} catch (Exception e) {
				log.severe(e.getMessage());
			} finally {
				if (session != null && session.isOpen()) {
					session.close();
				}
			}
		} else {
			log.severe("User='"+ login.getLogin() +"' already exists!");
		}
	}

	public void update(User login) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.saveOrUpdate(login);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public User getById(int id) throws SQLException {
		User login = null;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			login = (User) session.get(User.class, id);
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return login;
	}

	public User getByLogin(String searchLog) throws SQLException {
		User login = null;
		Session session = null;
		int id = 0;
		try {

			session = HibernateMyUtil.getSessionFactory().openSession();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			login = (User) HibernateMyUtil.getSessionFactory().getCurrentSession()
					.createCriteria(User.class)
					.add(Restrictions.eq("login", searchLog)).uniqueResult();

			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();

		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		return login;
	}


	public List<User> getAll() throws SQLException {
//		Session session = null;
//		Set<User> poses = new HashSet<User>();
		List<User> usersList = null;
		try {
			 HibernateMyUtil.getSessionFactory().openSession();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			usersList = HibernateMyUtil.getSessionFactory().getCurrentSession().createCriteria(User.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();
		} catch (Exception e) {
//			log.severe(e.getMessage());
			System.out.println(e.getMessage());
		}


//		for(User us : usersList){
//			poses.add(us);
//		}
		return usersList;
	}

	public void delete(User login) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(login);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public int getNextId() throws SQLException {

		int ret = 0;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			List<?> rez = session.createSQLQuery("select max(ID) from auth")
					.list();
			if (rez != null) {
				ret = Integer.valueOf(rez.get(0).toString());
				ret = ret + 1;
			}
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ret;
	}

	public boolean loginIsTrue(String login) {
		boolean rez = false;

		int ret = 0;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			String sql = "select count(ID) from auth where LOGIN ='"
					+ login + "'";
			List<?> rezList = session.createSQLQuery(sql).list();
			if (rezList != null) {
				ret = Integer.valueOf(rezList.get(0).toString());
				System.out.println(ret);
			}
			if (ret != 0) {
				rez = true;
			}

		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return rez;
	}


	public int idFromUser(String login) {
		int rez = 0;

		int ret = 0;
		Session session = null;
		try {

			session = HibernateMyUtil.getSessionFactory().openSession();
			String sql = "select ID from auth where LOGIN ='"
					+ login + "'";
			List<?> rezList = session.createSQLQuery(sql).list();
			if (rezList != null) {
				ret = Integer.valueOf(rezList.get(0).toString());
				System.out.println(ret);
			}
			if (ret != 0) {
				rez = ret;
			}

		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return rez;
	}

}