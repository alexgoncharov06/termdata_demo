package com.github.alexwolfgoncharov.termdata.dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;

public class TermDao {
	private static final Logger log = Logger.getLogger(ThermData.class
			.getName());

	public TermDao() {
	}

	public long add(ThermData termData) throws SQLException {
		Session session = null;
		long id = 0l;
		// termData.setID(getNextId());
		java.util.Date today = new java.util.Date();
		long ltoday = today.getTime();
		termData.setTime(new Timestamp(ltoday));
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			id = (Long) session.save(termData);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return id;
	}

	public void update(ThermData termData) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(termData);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public ThermData getById(int id) throws SQLException {
		ThermData termData = null;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			termData = (ThermData) session.get(ThermData.class, id);
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return termData;
	}

	@SuppressWarnings("unchecked")
	public List<ThermData> getAll() throws SQLException {
		Session session = null;
		List<ThermData> poses = new ArrayList<ThermData>();
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			poses = session.createCriteria(ThermData.class).list();
		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		return poses;
	}

	@SuppressWarnings("unchecked")
	public List<String> getAllBase() throws SQLException {
		Session session = null;
		List<String> poses = new ArrayList<String>();

		try {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			poses = HibernateMyUtil.getSessionFactory().getCurrentSession()
					.createSQLQuery("select DISTINCT BASE_ID from temperature")
					.list();

			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();

		} catch (Exception e) {
			log.severe(e.getMessage());
		}
		return poses;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public List<ThermData> getAllBaseWithData() throws SQLException {
		Session session = null;
		List<ThermData> poses = new ArrayList<ThermData>();

		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			poses = session
					.createSQLQuery(
							"SELECT * FROM temperature t1 WHERE (t1.time = (SELECT MAX(t2.time) FROM temperature t2 WHERE t2.BASE_ID = t1.BASE_ID))")
					.addEntity("temperature", ThermData.class).list();

		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return poses;
	}

	public List<ThermData> getAllByBase(String base) throws SQLException {
		// Session session = null;
		List<ThermData> poses = new ArrayList<ThermData>();
		try {
			// session = HibernateMyUtil.getSessionFactory().openSession();
			// poses = session.createCriteria(ThermData.class)
			// .add(Restrictions.eq("BaseID", base))
			// .addOrder(Order.desc("ID")).list();

			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			poses = HibernateMyUtil.getSessionFactory().getCurrentSession()
					.createCriteria(ThermData.class)
					.add(Restrictions.eq("BaseID", base))
					.addOrder(Order.desc("ID")).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();

		} catch (Exception e) {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
			.getTransaction().rollback();
			log.severe(e.getMessage());
		}
		// finally {
		// if (session != null && session.isOpen()) {
		// session.close();
		// }
		// }
		return poses;
	}

	public List<ThermData> getAllByBaseLimit(String base, int limit)
			throws SQLException {
		// Session session = null;
		List<ThermData> poses = new ArrayList<ThermData>();
		try {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			// session = HibernateMyUtil.getSessionFactory().openSession();
			// poses = session.createCriteria(ThermData.class)
			poses = (List<ThermData>) HibernateMyUtil.getSessionFactory()
					.getCurrentSession().createCriteria(ThermData.class)
					.add(Restrictions.eq("BaseID", base))
					.addOrder(Order.desc("ID")).setMaxResults(limit).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();
		} catch (Exception e) {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			log.severe(e.getMessage());
		}
		// finally {
		// if (session != null && session.isOpen()) {
		// session.close();
		// }
		// }
		return poses;
	}

	public List<ThermData> getAllByBaseFromToDate(String base, Date from,
			Date to) throws SQLException {
		// Session session = null;

		java.sql.Date fromSql = new java.sql.Date(from.getTime());

		java.sql.Date toSql = new java.sql.Date(to.getTime());

		List<ThermData> termList = new ArrayList<ThermData>();
		try {
			// session = HibernateMyUtil.getSessionFactory().openSession();

			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			termList = (List<ThermData>) HibernateMyUtil.getSessionFactory()
					.getCurrentSession().createCriteria(ThermData.class)
					.add(Restrictions.between("time", fromSql, toSql))
					.add(Restrictions.eq("BaseID", base))
					.addOrder(Order.asc("ID")).list();

			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();
		} catch (Exception e) {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			log.severe(e.getMessage());
		}
		// finally {
		// if (session != null && session.isOpen()) {
		// session.close();
		// }
		// }
		return termList;
	}

	public ThermData getLastByBase(String base) throws SQLException {
		Session session = null;
		ThermData term = new ThermData();
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			List<ThermData> ls = session.createCriteria(ThermData.class)
					.add(Restrictions.eq("BaseID", base))
					.addOrder(Order.desc("ID")).setMaxResults(1).list();
			term = ls.get(0);
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return term;
	}

	public void delete(ThermData termData) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(termData);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public long getNextId() throws SQLException {

		long ret = 0;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			List rez = session
					.createSQLQuery("select max(ID) from temperature").list();
			if (rez != null) {
				ret = Long.valueOf(rez.get(0).toString());
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

	public long getLastId(ThermData BaseID) throws SQLException {

		long ret = 0;
		Session session = null;
		ThermData term = new ThermData();
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			List<ThermData> ls = session.createCriteria(ThermData.class)
					.add(Restrictions.eq("BaseID", BaseID.getBaseID()))
					.addOrder(Order.desc("ID")).setMaxResults(1).list();
			term = ls.get(0);
			ret = term.getID();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}

		return ret;
	}

}
