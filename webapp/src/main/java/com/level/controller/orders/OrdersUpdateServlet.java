package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.TreeMap;

import static com.level.controller.WorkWithCookies.userCookieArr;

public class OrdersUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersUpdateServlet instance = new OrdersUpdateServlet();


    public static OrdersUpdateServlet getInstance() {
        return instance;
    }

    private OrdersUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        Map<String, String[]> map = new TreeMap<>(request.getParameterMap());
        map.put("username", userCookieArr(request.getCookies()));
        return OrdersUpdateManager.getInstance().update(map);
    }
}