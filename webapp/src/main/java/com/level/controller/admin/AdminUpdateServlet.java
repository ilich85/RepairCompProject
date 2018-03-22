package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;


public class AdminUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final AdminUpdateServlet instance = new AdminUpdateServlet();

    public static AdminUpdateServlet getInstance() {
        return instance;
    }

    private AdminUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return AdminUpdateManager.getInstance().update(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}