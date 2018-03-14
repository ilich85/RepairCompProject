package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class MessagesDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesDeleteServlet instance = new MessagesDeleteServlet();


    public static MessagesDeleteServlet getInstance() {
        return instance;
    }

    private MessagesDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return MessagesDeleteManager.getInstance().delete(map);
    }
}