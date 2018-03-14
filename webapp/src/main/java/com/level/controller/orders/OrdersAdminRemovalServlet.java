package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.adminCookieArr;

public class OrdersAdminRemovalServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersAdminRemovalServlet instance = new OrdersAdminRemovalServlet();


    public static OrdersAdminRemovalServlet getInstance() {
        return instance;
    }

    private OrdersAdminRemovalServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("admin_name", adminCookieArr(request.getCookies()));
        return OrdersAdminRemovalManager.getInstance().delete(map);
    }
}