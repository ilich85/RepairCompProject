package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class ServicesUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesUpdateServlet instance = new ServicesUpdateServlet();


    public static ServicesUpdateServlet getInstance() {
        return instance;
    }

    private ServicesUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return ServicesUpdateManager.getInstance().update(map);
    }
}