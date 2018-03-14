package com.level.dao.impl;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.Set;
import java.util.TreeSet;

public class AdminsDaoImpl extends EntityDaoImpl implements AuthDao {

    @Override
    public Admin getAuthByName(String name) {
        Admin admin;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            Query query = session.createQuery("FROM Admin WHERE adminName =:paramName");
            query.setParameter("paramName", name);
            admin = (Admin) query.uniqueResult();
        } catch (Exception e) {
            return null;
        }
        return admin;
    }

    @Override
    public Object getEntityByID(long id) {
        Admin admin;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            admin = session.get(Admin.class, id);
        } catch (HibernateException e) {
            return null;
        }
        return admin;
    }

    @Override
    public Set getAllEntities() {
        Set<Admin> admins = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            admins.addAll(session.createQuery("FROM Admin").list());
        } catch (HibernateException e) {
            return null;
        }
        return admins;
    }
}