package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminsListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;


public class AdminsListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final AdminsListServlet instance = new AdminsListServlet();


    public static AdminsListServlet getInstance() {
        return instance;
    }

    private AdminsListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return AdminsListManager.getInstance()
                .list(adminCookie(request.getCookies()));
    }
}