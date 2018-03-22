package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersDeleteManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;

public class OrdersDeleteServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersDeleteServlet instance = new OrdersDeleteServlet();


    public static OrdersDeleteServlet getInstance() {
        return instance;
    }

    private OrdersDeleteServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersDeleteManager.getInstance().delete(
                request.getParameterMap(), userCookie(request.getCookies()));
    }
}