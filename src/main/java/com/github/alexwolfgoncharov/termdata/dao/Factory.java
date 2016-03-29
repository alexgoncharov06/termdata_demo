package com.github.alexwolfgoncharov.termdata.dao;

import com.github.alexwolfgoncharov.termdata.services.OnlineDataService;
import com.github.alexwolfgoncharov.termdata.services.OnlineDataServiceImpl;
import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;

public class Factory {
	private static Factory instance = null;
	private static TermDao termDAO = null;
	private static LoginDao loginDao = null;
	private static ErrorDao errorDao = null;
	private static DAOBaseId baseidDao = null;
	private static OnlineDataService dataService = null;
	private static SessionFactory sessionFactory = null;
	private static int lastIns;

	public static synchronized Factory getInstance() {
		if (sessionFactory == null) {
			instance = new Factory();
			sessionFactory = HibernateMyUtil.getSessionFactory();
		}

		return instance;
	}


	public   TermDao getTermDAO() {
		if (termDAO == null) {
			termDAO = new TermDao();
		}
		return termDAO;
	}

	public   LoginDao getLoginDAO() {
		if (loginDao == null) {
			loginDao = new LoginDao();
		}
		return loginDao;
	}

	public   ErrorDao getErrorDAO() {
		if (errorDao == null) {
			errorDao = new ErrorDao();
		}
		return errorDao;
	}

	public   DAOBaseId getBaseIdDAO() {
		if (baseidDao == null) {

			baseidDao = new DAOBaseId();
		}
		return baseidDao;
	}


	public OnlineDataService getDataService(){

		if (dataService == null)
			dataService = new OnlineDataServiceImpl();
		return dataService;
	}
	

}
