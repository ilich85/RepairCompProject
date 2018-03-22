package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminRegistrationManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;


public class AdminRegistrationServlet extends APIHandlerServlet.APIRequestHandler {

    private static final AdminRegistrationServlet instance = new AdminRegistrationServlet();


    public static AdminRegistrationServlet getInstance() {
        return instance;
    }

    private AdminRegistrationServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return AdminRegistrationManager.getInstance().registration(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}