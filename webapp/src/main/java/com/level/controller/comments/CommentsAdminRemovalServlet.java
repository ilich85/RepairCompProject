package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class CommentsAdminRemovalServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsAdminRemovalServlet instance = new CommentsAdminRemovalServlet();


    public static CommentsAdminRemovalServlet getInstance() {
        return instance;
    }

    private CommentsAdminRemovalServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return CommentsAdminRemovalManager.getInstance().delete(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}