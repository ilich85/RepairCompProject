package com.level.managers.orders;

import com.level.dao.entity.Orders;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.parseLong;

public class OrdersDeleteManager {
    private OrdersDeleteManager() {
    }

    private static OrdersDeleteManager instance = null;

    public static OrdersDeleteManager getInstance() {
        if (instance == null) {
            instance = new OrdersDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam) {
        String result = null;
        JSONObject jsonObject = new JSONObject();
        Orders order = (Orders) Factory.getInstance().getOrdersDao()
                .getEntityByID(parseLong(mapParam.get("id_order")[0]));
        User currentUser = (User) Factory.getInstance().getUsersDao()
                .getAuthByName(mapParam.get("username")[0]);
        if (order != null && currentUser != null) {
            if (order.getUser().getIdUser() == currentUser.getIdUser()) {
                Factory.getInstance().getOrdersDao().delete(order);
                result = "complete";
            } else {
                result = "wrong";
            }
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}