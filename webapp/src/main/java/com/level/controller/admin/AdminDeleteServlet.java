package com.level.controller.admin;

import com.level.controller.APIHandlerServlet;
import com.level.managers.admin.AdminDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class AdminDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final AdminDeleteServlet instance = new AdminDeleteServlet();


    public static AdminDeleteServlet getInstance() {
        return instance;
    }

    private AdminDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> paramMap = new TreeMap<>(request.getParameterMap());
        paramMap.put("admin_name", adminCookieArr(request.getCookies()));
        return AdminDeleteManager.getInstance().delete(paramMap);
    }
}