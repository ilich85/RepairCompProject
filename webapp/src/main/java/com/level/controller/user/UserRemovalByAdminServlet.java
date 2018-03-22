package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class UserRemovalByAdminServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserRemovalByAdminServlet instance = new UserRemovalByAdminServlet();


    public static UserRemovalByAdminServlet getInstance() {
        return instance;
    }

    private UserRemovalByAdminServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserAdminRemovalManager.getInstance().delete(
                request.getParameterMap(),adminCookie(request.getCookies()));
    }
}