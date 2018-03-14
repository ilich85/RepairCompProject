package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class OrdersAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersAddServlet instance = new OrdersAddServlet();


    public static OrdersAddServlet getInstance() {
        return instance;
    }

    private OrdersAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return OrdersAddManager.getInstance().add(map);
    }
}