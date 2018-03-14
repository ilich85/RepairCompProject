package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserUpdateInfoManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class UserUpdateInfoServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserUpdateInfoServlet instance = new UserUpdateInfoServlet();


    public static UserUpdateInfoServlet getInstance() {
        return instance;
    }

    private UserUpdateInfoServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return UserUpdateInfoManager.getInstance().update(map);
    }
}