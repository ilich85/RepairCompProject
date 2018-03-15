package com.level.dao.impl;

import com.level.dao.entity.Messages;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MessagesDaoImpl extends EntityDaoImpl {

    @Override
    public Map<Long, Object> listAll() {
        Set<Messages> messagesSet = new TreeSet<>();
        Map<Long, Object> map = new TreeMap<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            messagesSet.addAll(session.createQuery("FROM Messages").list());
        }
        for (Messages messages : messagesSet) {
            map.put(messages.getIdMessage(), messages);
        }
        return map;
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