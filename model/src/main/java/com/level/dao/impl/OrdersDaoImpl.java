package com.level.dao.impl;

import com.level.dao.entity.Orders;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class OrdersDaoImpl extends EntityDaoImpl {

    @Override
    public Map<Long, Object> listAll() {
        Set<Orders> ordersSet = new TreeSet<>();
        Map<Long, Object> map = new TreeMap<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            ordersSet.addAll(session.createQuery("FROM Orders").list());
        }
        for (Orders orders : ordersSet) {
            map.put(orders.getIdOrder(), orders);
        }
        return map;
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