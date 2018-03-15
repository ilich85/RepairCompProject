package com.level.dao.impl;

import com.level.dao.entity.Services;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ServicesDaoImpl extends EntityDaoImpl {

    @Override
    public Map<Long, Object> listAll() {
        Set<Services> servicesSet = new TreeSet<>();
        Map<Long, Object> map = new TreeMap<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            servicesSet.addAll(session.createQuery("FROM Services").list());
        }
        for (Services services : servicesSet) {
            map.put(services.getIdService(), services);
        }
        return map;
    }

    @Override
    public Object getEntityByID(long id) {
        Services services;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            services = session.get(Services.class, id);
        }
        return services;
    }
}