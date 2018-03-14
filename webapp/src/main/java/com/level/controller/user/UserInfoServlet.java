package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserInfoManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;


public class UserInfoServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserInfoServlet instance = new UserInfoServlet();


    public static UserInfoServlet getInstance() {
        return instance;
    }

    private UserInfoServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return UserInfoManager.getInstance()
                .getUserInfo(userCookie(request.getCookies()));
    }
}