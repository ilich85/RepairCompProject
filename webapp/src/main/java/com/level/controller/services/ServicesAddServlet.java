package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class ServicesAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesAddServlet instance = new ServicesAddServlet();


    public static ServicesAddServlet getInstance() {
        return instance;
    }

    private ServicesAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return ServicesAddManager.getInstance().add(map);
    }
}