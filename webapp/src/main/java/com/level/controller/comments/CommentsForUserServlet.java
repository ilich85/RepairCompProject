package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsForUserManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;


public class CommentsForUserServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsForUserServlet instance = new CommentsForUserServlet();


    public static CommentsForUserServlet getInstance() {
        return instance;
    }

    private CommentsForUserServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return CommentsForUserManager.getInstance()
                .usersComments(userCookie(request.getCookies()));
    }
}