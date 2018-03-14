package com.level.controller.user;

import com.level.controller.APIHandlerServlet;
import com.level.managers.user.UserAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class UserAdminRemovalServlet extends APIHandlerServlet.APIRequestHandler {
    private static final UserAdminRemovalServlet instance = new UserAdminRemovalServlet();


    public static UserAdminRemovalServlet getInstance() {
        return instance;
    }

    private UserAdminRemovalServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return UserAdminRemovalManager.getInstance().deleteUser(map);
    }
}