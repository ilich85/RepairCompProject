package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserUpdatePassManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class UserUpdatePassServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserUpdatePassServlet instance = new UserUpdatePassServlet();


    public static UserUpdatePassServlet getInstance() {
        return instance;
    }

    private UserUpdatePassServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserUpdatePassManager.getInstance().update(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}