package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class ServicesDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesDeleteServlet instance = new ServicesDeleteServlet();


    public static ServicesDeleteServlet getInstance() {
        return instance;
    }

    private ServicesDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return ServicesDeleteManager.getInstance().delete(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}