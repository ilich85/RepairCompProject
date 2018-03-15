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

    public AuthDao getAdminsDao() {
        if (adminsDao == null) {
            adminsDao = new AdminsDaoImpl();
        }
        return adminsDao;
    }

    public AuthDao getUsersDao() {
        if (usersDao == null) {
            usersDao = new UsersDaoImpl();
        }
        return usersDao;
    }

    public EntityDao getOrdersDao() {
        if (ordersDao == null) {
            ordersDao = new OrdersDaoImpl();
        }
        return ordersDao;
    }

    public EntityDao getCommentsDao() {
        if (commentsDao == null) {
            commentsDao = new CommentsDaoImpl();
        }
        return commentsDao;
    }

    public EntityDao getServicesDao() {
        if (servicesDao == null) {
            servicesDao = new ServicesDaoImpl();
        }
        return servicesDao;
    }

    public EntityDao getMessagesDao() {
        if (messagesDao == null) {
            messagesDao = new MessagesDaoImpl();
        }
        return messagesDao;
    }
}