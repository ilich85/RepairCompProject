package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class CommentsDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsDeleteServlet instance = new CommentsDeleteServlet();


    public static CommentsDeleteServlet getInstance() {
        return instance;
    }

    private CommentsDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return CommentsDeleteManager.getInstance().delete(map);
    }
}