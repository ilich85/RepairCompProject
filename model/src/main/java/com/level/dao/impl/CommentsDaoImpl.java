package com.level.dao.impl;

import com.level.dao.entity.Comments;
import com.level.hibernateFactory.HibernateSessionFactory;
import org.hibernate.Session;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class CommentsDaoImpl extends EntityDaoImpl {

    @Override
    public Map<Long, Object> listAll() {
        Set<Comments> commentsSet = new TreeSet<>();
        Map<Long, Object> map = new TreeMap<>();
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            commentsSet.addAll(session.createQuery("FROM Comments").list());
        }
        for (Comments comments : commentsSet) {
            map.put(comments.getIdComment(), comments);
        }
        return map;
    }

    @Override
    public Comments getEntityByID(long id) {
        Comments comments;
        try (Session session = HibernateSessionFactory.getSessionFactory().openSession()) {
            comments = session.get(Comments.class, id);
        }
        return comments;
    }
}