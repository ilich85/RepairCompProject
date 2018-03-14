package com.level.dao.impl;

import com.level.dao.entity.Services;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.Set;
import java.util.TreeSet;

public class ServicesDaoImpl extends EntityDaoImpl {

    @Override
    public Set getAllEntities() {
        Set<Services> services = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            services.addAll(session.createQuery("FROM Services").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return services;
    }
    @Override
    public Object getEntityByID(long id) {
        Services services;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            services = session.get(Services.class, id);
        } catch (HibernateException e) {
            return null;
        }
        return services;
    }
}