package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class MessagesAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesAddServlet instance = new MessagesAddServlet();


    public static MessagesAddServlet getInstance() {
        return instance;
    }

    private MessagesAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return MessagesAddManager.getInstance().add(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}