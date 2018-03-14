package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserUpdatePassManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class UserUpdatePassServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserUpdatePassServlet instance = new UserUpdatePassServlet();


    public static UserUpdatePassServlet getInstance() {
        return instance;
    }

    private UserUpdatePassServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return UserUpdatePassManager.getInstance().update(map);
    }
}