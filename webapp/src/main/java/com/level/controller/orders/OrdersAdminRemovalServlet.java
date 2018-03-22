package com.level.controller.orders;

import com.level.controller.APIHandlerServlet;
import com.level.managers.orders.OrdersAdminRemovalManager;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.HttpServletRequest;

import static com.level.controller.WorkWithCookies.adminCookie;

public class OrdersAdminRemovalServlet extends APIHandlerServlet.APIRequestHandler {
    private static final OrdersAdminRemovalServlet instance = new OrdersAdminRemovalServlet();


    public static OrdersAdminRemovalServlet getInstance() {
        return instance;
    }

    private OrdersAdminRemovalServlet() {
    }

    @Override
    protected JSONStreamAware processRequest(HttpServletRequest request) {
        return OrdersAdminRemovalManager.getInstance().delete(
                request.getParameterMap(), adminCookie(request.getCookies()));
    }
}