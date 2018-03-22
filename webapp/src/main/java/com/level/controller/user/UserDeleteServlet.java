package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class UserDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserDeleteServlet instance = new UserDeleteServlet();


    public static UserDeleteServlet getInstance() {
        return instance;
    }

    private UserDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserDeleteManager.getInstance().delete(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}