package com.level.hibernateFactory;

import com.level.dao.impl.*;
import com.level.dao.interfaces.*;


public class Factory {

    private static EntityDao commentsDao = null;
    private static EntityDao messagesDao = null;
    private static EntityDao ordersDao = null;
    private static EntityDao servicesDao = null;
    private static AuthDao adminsDao = null;
    private static AuthDao usersDao = null;

    private static Factory instance = null;

    public static synchronized Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    public AuthDao getAdminDao() {
        if (adminsDao == null) {
            adminsDao = new AdminsDaoImpl();
        }
        return adminsDao;
    }

    public AuthDao getUserDao() {
        if (usersDao == null) {
            usersDao = new UsersDaoImpl();
        }
        return usersDao;
    }

    public EntityDao getOrderDao() {
        if (ordersDao == null) {
            ordersDao = new OrdersDaoImpl();
        }
        return ordersDao;
    }

    public EntityDao getCommentDAO() {
        if (commentsDao == null) {
            commentsDao = new CommentsDaoImpl();
        }
        return commentsDao;
    }

    public EntityDao getServiceDao() {
        if (servicesDao == null) {
            servicesDao = new ServicesDaoImpl();
        }
        return servicesDao;
    }

    public EntityDao getMessagesDAO() {
        if (messagesDao == null) {
            messagesDao = new MessagesDaoImpl();
        }
        return messagesDao;
    }
}