package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class CommentsDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsDeleteServlet instance = new CommentsDeleteServlet();


    public static CommentsDeleteServlet getInstance() {
        return instance;
    }

    private CommentsDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return CommentsDeleteManager.getInstance().delete(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}