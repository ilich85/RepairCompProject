package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class ServicesAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesAddServlet instance = new ServicesAddServlet();


    public static ServicesAddServlet getInstance() {
        return instance;
    }

    private ServicesAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return ServicesAddManager.getInstance().add(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}