package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersListManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;


public class OrdersListServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersListServlet instance = new OrdersListServlet();


    public static OrdersListServlet getInstance() {
        return instance;
    }

    private OrdersListServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersListManager.getInstance().list(adminCookie(request.getCookies()));
    }
}