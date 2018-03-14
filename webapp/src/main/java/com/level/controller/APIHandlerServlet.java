package com.level.controller;

import com.level.controller.admin.*;
import com.level.controller.comments.*;
import com.level.controller.messages.MessagesListServlet;
import com.level.controller.messages.MessagesAddServlet;
import com.level.controller.messages.MessagesDeleteServlet;
import com.level.controller.messages.MessagesUpdateServlet;
import com.level.controller.orders.*;
import com.level.controller.services.ServicesListServlet;
import com.level.controller.services.ServicesAddServlet;
import com.level.controller.services.ServicesDeleteServlet;
import com.level.controller.services.ServicesUpdateServlet;
import com.level.controller.user.*;
import com.level.utils.JSON;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.level.utils.JSONResponses.ERROR_INCORRECT_REQUEST;

public class APIHandlerServlet extends HttpServlet {
    public abstract static class APIRequestHandler {
        protected abstract JSONStreamAware processRequest(HttpServletRequest request);
    }

    private static Map<String, APIRequestHandler> apiRequestHandlers = new HashMap<>();

    static {
        Map<String, APIRequestHandler> map = new HashMap<>();

        map.put("userAuth", UserAuthServlet.getInstance());
        map.put("userReg", UserRegistrationServlet.getInstance());
        map.put("userInfo", UserInfoServlet.getInstance());
        map.put("userUpdatePass", UserUpdatePassServlet.getInstance());
        map.put("userUpdateInfo", UserUpdateInfoServlet.getInstance());
        map.put("userDelete", UserDeleteServlet.getInstance());
        map.put("deletingAnUserByAnAdmin", UserAdminRemovalServlet.getInstance());
        map.put("usersList", UsersListServlet.getInstance());

        map.put("adminAuth", AdminAuthServlet.getInstance());
        map.put("adminReg", AdminRegistrationServlet.getInstance());
        map.put("adminUpdate", AdminUpdateServlet.getInstance());
        map.put("adminDelete", AdminDeleteServlet.getInstance());
        map.put("adminsList", AdminsListServlet.getInstance());

        map.put("commentsAdd", CommentsAddServlet.getInstance());
        map.put("commentsUpdate", CommentsUpdateServlet.getInstance());
        map.put("commentsDelete", CommentsDeleteServlet.getInstance());
        map.put("commentsForUser", CommentsForUserServlet.getInstance());
        map.put("deletingAnCommentByAnAdmin", CommentsAdminRemovalServlet.getInstance());
        map.put("commentsListAll", CommentsListServlet.getInstance());
        map.put("commentsListForAdmin", CommentsListServlet.getInstance());

        map.put("messagesAdd", MessagesAddServlet.getInstance());
        map.put("messagesUpdate", MessagesUpdateServlet.getInstance());
        map.put("messagesDelete", MessagesDeleteServlet.getInstance());
        map.put("messagesList", MessagesListServlet.getInstance());
        map.put("messagesListForAdmin", MessagesListServlet.getInstance());

        map.put("ordersAdd", OrdersAddServlet.getInstance());
        map.put("ordersUpdate", OrdersUpdateServlet.getInstance());
        map.put("ordersDelete", OrdersDeleteServlet.getInstance());
        map.put("ordersForUser", OrdersForUserServlet.getInstance());
        map.put("deletingAnOrderByAnAdmin", OrdersAdminRemovalServlet.getInstance());
        map.put("ordersListForAdmin", OrdersListServlet.getInstance());

        map.put("servicesAdd", ServicesAddServlet.getInstance());
        map.put("servicesUpdate", ServicesUpdateServlet.getInstance());
        map.put("servicesDelete", ServicesDeleteServlet.getInstance());
        map.put("servicesListAll", ServicesListServlet.getInstance());
        map.put("servicesList", ServicesListServlet.getInstance());

        apiRequestHandlers = Collections.unmodifiableMap(map);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        switch (req.getParameter("requestType")) {
            case "userAuth":
                resp.addCookie(new Cookie("username", req.getParameterMap().get("username")[0]));
                break;
            case "adminAuth":
                resp.addCookie(new Cookie("admin_name", req.getParameterMap().get("admin_name")[0]));
                break;
        }
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        switch (req.getParameter("requestType")) {
            case "userLogout":
                resp.addCookie(new Cookie("username", ""));
                break;
            case "adminLogout":
                resp.addCookie(new Cookie("admin_name", ""));
                break;
        }
        process(req, resp);
    }

    private static Map<String, APIRequestHandler> getApiRequestHandlers() {
        return apiRequestHandlers;
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) {
        resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate, private");
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setDateHeader("Expires", 0);
        JSONStreamAware response = JSON.emptyJSON;
        try {
            long startTime = System.currentTimeMillis();
            String requestType = req.getParameter("requestType");

            if (requestType == null) {
                response = ERROR_INCORRECT_REQUEST;
                return;
            }
            APIRequestHandler apiRequestHandler = getApiRequestHandlers().get(requestType);
            if (apiRequestHandler == null) {
                response = ERROR_INCORRECT_REQUEST;
                return;
            }

            response = apiRequestHandler.processRequest(req);
            if (response instanceof JSONObject) {
                ((JSONObject) response).put("requestProcessingTime", System.currentTimeMillis() - startTime);
            }
        } finally {
            resp.setContentType("text/plain; charset=UTF-8");
            try (Writer writer = resp.getWriter()) {
                response.writeJSONString(writer);
            } catch (IOException e) {
                return;
            }
        }
    }
}