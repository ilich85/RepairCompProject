package com.level.dao.impl;

import com.level.dao.entity.User;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Set;
import java.util.TreeSet;


public class UsersDaoImpl extends EntityDaoImpl implements AuthDao {

    @Override
    public User getAuthByName(String name) {
        User user;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM User WHERE username =:paramName");
            query.setParameter("paramName", name);
            user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            return null;
        }
        return user;
    }

    @Override
    public Set getAllEntities() {
        Set<User> users = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            users.addAll(session.createQuery("FROM User").list());
        }
        return users;
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