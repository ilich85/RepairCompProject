package com.level.managers.orders;

import com.level.dao.entity.Orders;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


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
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonOrders = new JSONObject();
        User currUser = (User) inst.getUsersDao().getAuthByName(username);
        for (Map.Entry<Long, Object> entry : new TreeMap<>(inst.getOrdersDao().listAll()).entrySet()) {
            Orders orders = (Orders) entry.getValue();
            if (orders.getUser().equals(currUser)) {
                jsonOrders.put(entry.getKey(), serializableOrder((Orders) entry.getValue()));
                jsonObject.put("users_orders", jsonOrders);
            }
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