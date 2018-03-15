package com.level.managers.orders;

import com.level.dao.entity.Admin;
import com.level.dao.entity.Orders;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class OrdersListManager {
    private static OrdersListManager instance = null;

    public static OrdersListManager getInstance() {
        if (instance == null) {
            instance = new OrdersListManager();
        }
        return instance;
    }

    public JSONObject list(String adminName) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonOrders = new JSONObject();
        if (inst.getAdminsDao().getAuthByName(adminName) != null) {
            for (Map.Entry<Long, Object> entry : new TreeMap<>(inst.getOrdersDao()
                    .listAll()).entrySet()) {
                jsonOrders.put(entry.getKey(), serializableOrder((Orders) entry.getValue()));
                jsonObject.put("orders", jsonOrders);
            }
        } else {
            return null;
        }
        return jsonObject;
    }

    private JSONObject serializableOrder(Orders orders) {
        JSONObject ordersJson = new JSONObject();
        ordersJson.put("id", orders.getIdOrder());
        ordersJson.put("date", orders.getDate());
        ordersJson.put("text", orders.getText());
        ordersJson.put("user", orders.getUser().getUsername());
        ordersJson.put("phone", orders.getUser().getPhone());
        return ordersJson;
    }
}