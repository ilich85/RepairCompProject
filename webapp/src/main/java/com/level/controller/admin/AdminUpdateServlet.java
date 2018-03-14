package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;


public class AdminUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final AdminUpdateServlet instance = new AdminUpdateServlet();

    public static AdminUpdateServlet getInstance() {
        return instance;
    }

    private AdminUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("current_admin", adminCookieArr(request.getCookies()));
        return AdminUpdateManager.getInstance().update(map);
    }
}