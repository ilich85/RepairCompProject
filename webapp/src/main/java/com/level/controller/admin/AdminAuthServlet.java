package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminAuthManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

public class AdminAuthServlet extends APIHandlerServlet.APIRequestHandler {

    private static final AdminAuthServlet instance = new AdminAuthServlet();

    public static AdminAuthServlet getInstance() {
        return instance;
    }

    private AdminAuthServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return AdminAuthManager.getInstance().auth(request.getParameterMap());
    }
}