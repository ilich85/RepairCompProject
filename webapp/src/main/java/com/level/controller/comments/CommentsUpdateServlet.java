package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class CommentsUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsUpdateServlet instance = new CommentsUpdateServlet();

    public static CommentsUpdateServlet getInstance() {
        return instance;
    }

    private CommentsUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return CommentsUpdateManager.getInstance().update(map);
    }
}