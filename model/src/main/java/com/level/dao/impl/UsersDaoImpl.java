package com.level.dao.impl;

import com.level.dao.entity.User;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class UsersDaoImpl extends EntityDaoImpl implements AuthDao {

    @Override
    public User getAuthByName(String name) {
        User user;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE username =:paramName");
            query.setParameter("paramName", name);
            user = (User) query.uniqueResult();
        }
        return user;
    }

    @Override
    public Map<Long, Object> listAll() {
        Set<User> userSet = new TreeSet<>();
        Map<Long, Object> map = new TreeMap<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            userSet.addAll(session.createQuery("FROM User").list());
        }
        for (User user : userSet) {
            map.put(user.getIdUser(), user);
        }
        return map;
    }

    @Override
    public Object getEntityByID(long id) {
        User user;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            user = session.get(User.class, id);
        }
        return user;
    }
}