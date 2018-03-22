package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class ServicesUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesUpdateServlet instance = new ServicesUpdateServlet();


    public static ServicesUpdateServlet getInstance() {
        return instance;
    }

    private ServicesUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return ServicesUpdateManager.getInstance().update(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}