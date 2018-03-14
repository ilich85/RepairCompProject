package com.level.dao.impl;


import com.level.dao.entity.Messages;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Set;
import java.util.TreeSet;

public class MessagesDaoImpl extends EntityDaoImpl {

    @Override
    public Set getAllEntities() {
        Set<Messages> messages = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            messages.addAll(session.createQuery("FROM Messages").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return messages;
    }
    @Override
    public Object getEntityByID(long id) {
        Messages messages;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            messages = session.get(Messages.class, id);
        }
        return messages;
    }
}