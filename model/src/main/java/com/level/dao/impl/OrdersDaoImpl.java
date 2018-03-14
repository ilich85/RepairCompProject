package com.level.dao.impl;

import com.level.dao.entity.Orders;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Set;
import java.util.TreeSet;

public class OrdersDaoImpl extends EntityDaoImpl {

    @Override
    public Set getAllEntities() {
        Set<Orders> orders = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            orders.addAll(session.createQuery("FROM Orders").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }
    @Override
    public Object getEntityByID(long id) {
        Orders orders;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            orders = session.get(Orders.class, id);
        }
        return orders;
    }
}