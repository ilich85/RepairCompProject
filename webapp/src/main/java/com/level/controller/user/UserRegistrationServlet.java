package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserRegistrationManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;


public class UserRegistrationServlet extends APIHandlerServlet.APIRequestHandler {

    private static final UserRegistrationServlet instance = new UserRegistrationServlet();


    public static UserRegistrationServlet getInstance() {
        return instance;
    }

    private UserRegistrationServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserRegistrationManager.getInstance().registration(request.getParameterMap());
    }
}