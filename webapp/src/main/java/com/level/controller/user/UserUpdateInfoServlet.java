package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserUpdateInfoManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class UserUpdateInfoServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserUpdateInfoServlet instance = new UserUpdateInfoServlet();


    public static UserUpdateInfoServlet getInstance() {
        return instance;
    }

    private UserUpdateInfoServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserUpdateInfoManager.getInstance().update(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}