package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;


public class ServicesListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesListServlet instance = new ServicesListServlet();


    public static ServicesListServlet getInstance() {
        return instance;
    }

    private ServicesListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return ServicesListManager.getInstance().list();
    }
}