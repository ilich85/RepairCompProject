package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class AdminDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final AdminDeleteServlet instance = new AdminDeleteServlet();


    public static AdminDeleteServlet getInstance() {
        return instance;
    }

    private AdminDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return AdminDeleteManager.getInstance().delete(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}