package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserAuthManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;


public class UserAuthServlet extends APIHandlerServlet.APIRequestHandler {

    private static final UserAuthServlet instance = new UserAuthServlet();


    public static UserAuthServlet getInstance() {
        return instance;
    }

    private UserAuthServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserAuthManager.getInstance().auth(request.getParameterMap());
    }
}