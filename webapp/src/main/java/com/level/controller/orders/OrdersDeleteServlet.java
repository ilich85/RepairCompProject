package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class OrdersDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersDeleteServlet instance = new OrdersDeleteServlet();


    public static OrdersDeleteServlet getInstance() {
        return instance;
    }

    private OrdersDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return OrdersDeleteManager.getInstance().delete(map);
    }
}