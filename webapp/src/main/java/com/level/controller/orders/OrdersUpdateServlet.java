package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersUpdateManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class OrdersUpdateServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersUpdateServlet instance = new OrdersUpdateServlet();


    public static OrdersUpdateServlet getInstance() {
        return instance;
    }

    private OrdersUpdateServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersUpdateManager.getInstance().update(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}