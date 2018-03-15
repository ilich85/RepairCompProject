package com.level.dao.impl;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AdminsDaoImpl extends EntityDaoImpl implements AuthDao {

    @Override
    public Admin getAuthByName(String name) {
        Admin admin;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Admin WHERE adminName =:paramName");
            query.setParameter("paramName", name);
            admin = (Admin) query.uniqueResult();
        }
        return admin;
    }

    @Override
    public Object getEntityByID(long id) {
        Admin admin;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            admin = session.get(Admin.class, id);
        }
        return admin;
    }

    @Override
    public Map<Long, Object> listAll() {
        Map<Long, Object> map = new TreeMap<>();
        Set<Admin> adminSet = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            adminSet.addAll(session.createQuery("FROM Admin").list());
        }
        for (Admin admin : adminSet) {
            map.put(admin.getIdAdmin(), admin);
        }
        return map;
    }
}