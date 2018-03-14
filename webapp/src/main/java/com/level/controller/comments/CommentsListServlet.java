package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;


public class CommentsListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsListServlet instance = new CommentsListServlet();

    public static CommentsListServlet getInstance() {
        return instance;
    }

    private CommentsListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return CommentsListManager.getInstance().list();
    }
}