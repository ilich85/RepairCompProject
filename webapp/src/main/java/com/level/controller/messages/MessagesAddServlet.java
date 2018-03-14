package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class MessagesAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesAddServlet instance = new MessagesAddServlet();


    public static MessagesAddServlet getInstance() {
        return instance;
    }

    private MessagesAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return MessagesAddManager.getInstance().add(map);
    }
}