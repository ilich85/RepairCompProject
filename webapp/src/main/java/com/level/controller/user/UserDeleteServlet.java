package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class UserDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserDeleteServlet instance = new UserDeleteServlet();


    public static UserDeleteServlet getInstance() {
        return instance;
    }

    private UserDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return UserDeleteManager.getInstance().delete(map);
    }
}