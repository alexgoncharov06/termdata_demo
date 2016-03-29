package com.github.alexwolfgoncharov.termdata.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
//import org.hibernate.service.ServiceRegistryBuilder;

public class HibernateMyUtil {

	   private static  SessionFactory sessionFactory = buildSessionFactory();
	    private static ServiceRegistry serviceRegistry;
	 
	    private static SessionFactory buildSessionFactory() {
			if (sessionFactory == null) {
				sessionFactory = new Configuration().configure().buildSessionFactory();

			}

			return sessionFactory;
		}
	 
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
	 
	    public static void shutdown() {
	        // Чистит кеш и закрывает соединение с БД
	        getSessionFactory().close();
	    }
	    
}
