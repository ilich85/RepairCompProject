package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersForUserManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.userCookie;


public class OrdersForUserServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersForUserServlet instance = new OrdersForUserServlet();


    public static OrdersForUserServlet getInstance() {
        return instance;
    }

    private OrdersForUserServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersForUserManager.getInstance()
                .list(userCookie(request.getCookies()));
    }
}