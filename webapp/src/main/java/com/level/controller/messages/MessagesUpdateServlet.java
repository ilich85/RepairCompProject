package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class MessagesUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesUpdateServlet instance = new MessagesUpdateServlet();


    public static MessagesUpdateServlet getInstance() {
        return instance;
    }

    private MessagesUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return MessagesUpdateManager.getInstance().update(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}