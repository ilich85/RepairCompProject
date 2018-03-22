package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersAddManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class OrdersAddServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersAddServlet instance = new OrdersAddServlet();


    public static OrdersAddServlet getInstance() {
        return instance;
    }

    private OrdersAddServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersAddManager.getInstance().add(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}