package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class MessagesDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesDeleteServlet instance = new MessagesDeleteServlet();


    public static MessagesDeleteServlet getInstance() {
        return instance;
    }

    private MessagesDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return MessagesDeleteManager.getInstance().delete(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}