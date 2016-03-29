package com.github.alexwolfgoncharov.termdata.dao;

import com.github.alexwolfgoncharov.termdata.interfaces.OnlineData;
import com.github.alexwolfgoncharov.termdata.util.HibernateMyUtil;
import com.github.alexwolfgoncharov.termdata.util.HibernateUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alexwolf on 10.03.16.
 */
public class OnlineDataDAOImpl implements  OnlineDataDAO
{


    private static final Logger log = Logger.getLogger(OnlineDataDAOImpl.class
            .getName());

    @Override
    public void add(OnlineData data) {
        Session session = null;
        try{
            session = HibernateMyUtil.getSessionFactory().getCurrentSession();
//            session =  HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(data);
            session.getTransaction().commit();


        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }
    }

    @Override
    public void modify(OnlineData data) {

        add(data);
    }

    @Override
    public void delete(OnlineData data) {

        Session session = null;
        try{
//            session =  HibernateUtil.getSessionFactory().getCurrentSession();
            session =  HibernateUtil.getSession();
            session.beginTransaction();
            session.delete(data);
            session.getTransaction().commit();


        } catch (Exception e) {
            HibernateUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }




    }

    @Override
    public List<OnlineData> getAll() {
        List<OnlineData> onlineDataList = null;

        Session session = null;
        try{
            session =  HibernateUtil.getSession();

            session.beginTransaction();
            onlineDataList  =  (List<OnlineData>)   session.createCriteria(OnlineData.class)
                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                    .addOrder(Order.asc("time"))
                    .list();

            session.getTransaction().commit();


        } catch (Exception e) {
            session
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }


        return onlineDataList;
    }

    @Override
    public OnlineData getLastByBase(String base) {
        OnlineData onlineData = null;

        Session session = null;
        try{
            session =  HibernateMyUtil.getSessionFactory().getCurrentSession();
//            session =  HibernateUtil.getSession();
            session.beginTransaction();
             List<OnlineData> onlineDatas =
                     session.createCriteria(OnlineData.class)
                             .add(Restrictions.eq("BaseID", base)).setMaxResults(1).list();

//                     (List<OnlineData> )   session.createCriteria(OnlineData.class)
//                    .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
//                    .add(Restrictions.eq("BASE_ID", base))
//                    .setMaxResults(1)
//                    .list();

            System.out.println(onlineDatas.size());
            onlineData  = onlineDatas.get(0);
            session.getTransaction().commit();


        } catch (Exception e) {
            HibernateMyUtil.getSessionFactory().getCurrentSession()
                    .getTransaction().rollback();
            log.severe(e.getMessage());
        }
        return onlineData;
    }
}
