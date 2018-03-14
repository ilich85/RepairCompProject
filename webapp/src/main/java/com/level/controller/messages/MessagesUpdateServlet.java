package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class MessagesUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesUpdateServlet instance = new MessagesUpdateServlet();


    public static MessagesUpdateServlet getInstance() {
        return instance;
    }

    private MessagesUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return MessagesUpdateManager.getInstance().update(map);
    }
}