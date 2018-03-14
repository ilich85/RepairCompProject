package com.level.controller.services;

import com.level.controller.APIHandlerServlet;
import com.level.managers.services.ServicesDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class ServicesDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final ServicesDeleteServlet instance = new ServicesDeleteServlet();


    public static ServicesDeleteServlet getInstance() {
        return instance;
    }

    private ServicesDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return ServicesDeleteManager.getInstance().delete(map);
    }
}