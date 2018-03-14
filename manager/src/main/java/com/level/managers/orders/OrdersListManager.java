package com.level.managers.orders;

import com.level.dao.entity.Admin;
import com.level.dao.entity.Orders;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
        Map<Long, Orders> listOrders = new HashMap<>();
        Admin currentAdmin = (Admin) inst.getAdminDao().getAuthByName(adminName);
        Set<Orders> ordersSet;
        if (currentAdmin != null) {
            ordersSet = inst.getOrderDao().getAllEntities();
        } else {
            return null;
        }
        if (ordersSet != null) {
            for (Orders orders : ordersSet) {
                Orders order = (Orders) inst.getOrderDao().getEntityByID(orders.getIdOrder());
                listOrders.put(order.getIdOrder(), order);
            }
        }

        for (Map.Entry<Long, Orders> entry : listOrders.entrySet()) {
            jsonOrders.put(entry.getKey(), serializableOrder(entry.getValue()));
            jsonObject.put("orders", jsonOrders);

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