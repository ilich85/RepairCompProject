package com.level.managers.orders;

import com.level.dao.entity.Orders;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class OrdersForUserManager {
    private static OrdersForUserManager instance = null;

    public static OrdersForUserManager getInstance() {
        if (instance == null) {
            instance = new OrdersForUserManager();
        }
        return instance;
    }

    public JSONObject list(String username) {
        Factory inst = Factory.getInstance();
        Set<Orders> ordersSet;
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonOrders = new JSONObject();
        Map<Long, Orders> userOrders = new HashMap<>();
        User currentUser = (User) inst.getUserDao().getAuthByName(username);
        ordersSet = inst.getOrderDao().getAllEntities();
        if (ordersSet != null) {
            for (Orders orders : ordersSet) {
                Orders order = (Orders) inst.getOrderDao().getEntityByID(orders.getIdOrder());
                if (currentUser != null && order.getUser().getUsername().equals(currentUser.getUsername())) {
                    userOrders.put(order.getIdOrder(), order);
                }
            }
        }

        for (Map.Entry<Long, Orders> entry : userOrders.entrySet()) {
            jsonOrders.put(entry.getKey(), serializableOrder(entry.getValue()));
            jsonObject.put("users_orders", jsonOrders);
        }
        return jsonObject;
    }

    private JSONObject serializableOrder(Orders orders) {
        JSONObject orderJson = new JSONObject();
        orderJson.put("id", orders.getIdOrder());
        orderJson.put("date", orders.getDate());
        orderJson.put("text", orders.getText());
        return orderJson;
    }
}