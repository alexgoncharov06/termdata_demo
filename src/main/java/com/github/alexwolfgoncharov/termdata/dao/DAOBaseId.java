package com.github.alexwolfgoncharov.termdata.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.github.alexwolfgoncharov.termdata.interfaces.BaseID;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;

import com.github.alexwolfgoncharov.termdata.interfaces.ThermData;

public class DAOBaseId {
	private static final Logger log = Logger.getLogger(ThermData.class
			.getName());

	public DAOBaseId() {
	}

	public static void add(BaseID baseId) throws SQLException {
		// Session session = null;
		try {
			// session = HibernateMyUtil.getSessionFactory().openSession();
			// session.beginTransaction();
			// session.save(baseId);
			// session.getTransaction().commit();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.saveOrUpdate(baseId);
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

	}

	public static void update(BaseID baseId) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.update(baseId);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static void seveOrUpdate(BaseID baseId) throws SQLException {
		// Session session = null;
		try {
			// session = HibernateMyUtil.getSessionFactory().openSession();
			// session.beginTransaction();
			// session.saveOrUpdate(baseId);
			// session.getTransaction().commit();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.saveOrUpdate(baseId);
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
	}

	public static void modifyError(BaseID baseId) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.save(baseId);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static BaseID getById(int id) throws SQLException {
		BaseID error = null;
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			error = (BaseID) session.get(BaseID.class, id);
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
		return error;
	}

	public static BaseID getByBaseID(String baseid) throws SQLException {
		BaseID rez = null;
		HibernateMyUtil.getSessionFactory().getCurrentSession()
				.beginTransaction();

		rez = (BaseID) HibernateMyUtil.getSessionFactory().getCurrentSession()
				.createCriteria(BaseID.class)
				.add(Restrictions.eq("BaseID", baseid)).uniqueResult();

		HibernateMyUtil.getSessionFactory().getCurrentSession()
				.getTransaction().commit();
		// return (BaseID)
		// HibernateMyUtil.getSessionFactory().openSession().createCriteria(
		// BaseID.class ).
		// add( Restrictions.eq("BaseID", baseid) ).
		// uniqueResult();
		return rez;
	}

	@SuppressWarnings("unchecked")
	public List<BaseID> getAll() throws SQLException {
		Session session = null;
		List<BaseID> baseIds = null;
		try {
			//
			// session = HibernateMyUtil.getSessionFactory().openSession();
			// baseIds = session.createCriteria(BaseID.class)
			// .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			baseIds = HibernateMyUtil.getSessionFactory().getCurrentSession()
					.createCriteria(BaseID.class)
					.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
			.getTransaction().commit();
		} catch (Exception e) {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
			.getTransaction().rollback();
			log.severe(e.getMessage());
		} 
//		finally {
//			if (session != null && session.isOpen()) {
//				session.close();
//			}
//		}
		return baseIds;
	}

	public static void delete(BaseID baseId) throws SQLException {
		Session session = null;
		try {
			session = HibernateMyUtil.getSessionFactory().openSession();
			session.beginTransaction();
			session.delete(baseId);
			session.getTransaction().commit();
		} catch (Exception e) {
			log.severe(e.getMessage());
		} finally {
			if (session != null && session.isOpen()) {
				session.close();
			}
		}
	}

	public static List<String> getUserBaseIDStringList(String login){


		List<String> baseIds = null;
		String query = "SELECT base.BASE_ID from ((base_id base RIGHT JOIN login_baseid loginsBase ON base.ID = loginsBase.base_id) RIGHT JOIN auth logins ON loginsBase.login_id = logins.ID) WHERE logins.LOGIN = \""+login +"\"";
		try {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.beginTransaction();

			baseIds = (List<String>) HibernateMyUtil.getSessionFactory().getCurrentSession()
					.createSQLQuery(query).list();
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().commit();
		} catch (Exception e) {
			HibernateMyUtil.getSessionFactory().getCurrentSession()
					.getTransaction().rollback();
			log.severe(e.getMessage());
		}

		return baseIds;


	}

}
