package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class CommentsUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsUpdateServlet instance = new CommentsUpdateServlet();

    public static CommentsUpdateServlet getInstance() {
        return instance;
    }

    private CommentsUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {

        return CommentsUpdateManager.getInstance().update(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}