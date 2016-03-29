package com.github.alexwolfgoncharov.termdata.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.github.alexwolfgoncharov.termdata.interfaces.ErrorCodes;
import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;

import com.github.alexwolfgoncharov.termdata.interfaces.ErrorHistory;

public class ErrorDao {
	private static final Logger log = Logger.getLogger(ThermData.class
			.getName());

	public ErrorDao() {
	}

	public static void add(ErrorCodes code) throws SQLException {
		Session session = null;
//		code.setID(getNextId());
		java.util.Date today = new java.util.Date();
		long ltoday = today.getTime();
		code.setTime(new Timestamp(ltoday));
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(code);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

	}

	public static void update(ErrorCodes login) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(login);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static void modifyError(ErrorHistory history) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(history);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static ErrorCodes getById(int id) throws SQLException {
		ErrorCodes error = null;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			error = (ErrorCodes) session.get(ErrorCodes.class, id);
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return error;
	}

	public static List<ErrorCodes> getByBaseId(String searchLog)
			throws SQLException {
		List<ErrorCodes> errors = new ArrayList<ErrorCodes>();
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			errors = session.createCriteria(ErrorCodes.class)
					.add(Restrictions.eq("baseId", searchLog))
					.addOrder(Order.desc("ID")).list();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return errors;
	}

	@SuppressWarnings("unchecked")
	public List<ErrorCodes> getAll() throws SQLException {
		Session session = null;
		List<ErrorCodes> poses = new ArrayList<ErrorCodes>();
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			poses = session.createCriteria(ErrorCodes.class).list();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return poses;
	}

	
	public List<ThermData> getAllThermByError(int id) throws SQLException {
		Session session = null;
		List<ThermData> termList = new ArrayList<ThermData>();
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			termList = session.createCriteria(ErrorCodes.class).add(Restrictions.eq("ID", id)).list();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return termList;
	}
	
	
	public static void delete(ErrorCodes login) throws SQLException {
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

	public static int getNextId() throws SQLException {

		int ret = 1;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			List rez = session.createSQLQuery("select max(ID) from errorcodes")
					.list();
			if (rez != null) {
				ret = Integer.valueOf(rez.get(0).toString());
				ret = ret + 1;
			} else return 1;
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		System.out.println(ret);
		return ret;
	}

}
