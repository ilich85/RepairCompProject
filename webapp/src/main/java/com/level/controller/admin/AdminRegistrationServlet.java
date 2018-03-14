package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminRegistrationManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;


public class AdminRegistrationServlet extends APIHandlerServlet.APIRequestHandler {

    private static final AdminRegistrationServlet instance = new AdminRegistrationServlet();


    public static AdminRegistrationServlet getInstance() {
        return instance;
    }

    private AdminRegistrationServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("current_admin", adminCookieArr(request.getCookies()));
        return AdminRegistrationManager.getInstance().registration(map);
    }
}