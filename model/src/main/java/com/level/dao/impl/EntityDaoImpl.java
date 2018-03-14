package com.level.dao.impl;

import com.level.hibernateFactory.HibernateSessionFactory;
import com.level.dao.interfaces.EntityDao;
import org.hibernate.Session;

import java.util.Set;

public abstract class EntityDaoImpl implements EntityDao {
    @Override
    public void add(Object object) {
         workWithEntity(object, "add");
    }

    @Override
    public void update(Object object) {
         workWithEntity(object, "update");
    }

    @Override
    public void delete(Object object) {
         workWithEntity(object, "delete");
    }

    private void workWithEntity(Object object, String string) {
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            session.beginTransaction();
            switch (string) {
                case "add":
                    session.save((object));
                    break;
                case "update":
                    session.update(object);
                    break;
                case "delete":
                    session.delete((object));
                    break;
            }
            session.getTransaction().commit();
        }catch (Exception e){

        }
    }

    @Override
    public abstract Object getEntityByID(long id);

    @Override
    public abstract Set getAllEntities();
}