package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UsersListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;


public class UsersListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UsersListServlet instance = new UsersListServlet();


    public static UsersListServlet getInstance() {
        return instance;
    }

    private UsersListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UsersListManager.getInstance()
                .list(adminCookie(request.getCookies()));
    }
}