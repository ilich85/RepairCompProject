package com.level.dao.impl;


import com.level.dao.entity.Comments;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Set;
import java.util.TreeSet;

public class CommentsDaoImpl extends EntityDaoImpl {

    @Override
    public Set getAllEntities() {
        Set<Comments> comments = new TreeSet<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            comments.addAll(session.createQuery("FROM Comments").list());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comments getEntityByID(long id) {
        Comments comments = null;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            comments = session.get(Comments.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comments;
    }
}