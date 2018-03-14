package com.level.controller.comments;

import com.level.controller.APIHandlerServlet;
import com.level.managers.comments.CommentsAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class CommentsAdminRemovalServlet extends APIHandlerServlet.APIRequestHandler {
    private static final CommentsAdminRemovalServlet instance = new CommentsAdminRemovalServlet();


    public static CommentsAdminRemovalServlet getInstance() {
        return instance;
    }

    private CommentsAdminRemovalServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return CommentsAdminRemovalManager.getInstance().delete(map);
    }
}