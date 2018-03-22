package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class CommentsAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsAddServlet instance = new CommentsAddServlet();


    public static CommentsAddServlet getInstance() {
        return instance;
    }

    private CommentsAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return CommentsAddManager.getInstance().add(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}