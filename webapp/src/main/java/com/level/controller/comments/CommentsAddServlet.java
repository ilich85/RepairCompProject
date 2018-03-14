package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class CommentsAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsAddServlet instance = new CommentsAddServlet();


    public static CommentsAddServlet getInstance() {
        return instance;
    }

    private CommentsAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return CommentsAddManager.getInstance().add(map);
    }
}