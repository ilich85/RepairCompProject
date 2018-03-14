package com.level.controller.messages;

import com.level.controller.APIHandlerServlet;
import com.level.managers.messages.MessagesListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;


public class MessagesListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final MessagesListServlet instance = new MessagesListServlet();


    public static MessagesListServlet getInstance() {
        return instance;
    }

    private MessagesListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return MessagesListManager.getInstance().list();
    }
}