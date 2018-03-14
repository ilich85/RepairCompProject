package com.level.managers.orders;

import com.level.dao.entity.Orders;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.parseLong;

public class OrdersUpdateManager {
    private OrdersUpdateManager() {
    }

    private static OrdersUpdateManager instance = null;

    public static OrdersUpdateManager getInstance() {
        if (instance == null) {
            instance = new OrdersUpdateManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        User currentUser = (User) inst.getUserDao()
                .getAuthByName(mapParam.get("username")[0]);
        Orders order = (Orders) inst.getOrderDao()
                .getEntityByID(parseLong(mapParam.get("id_order")[0]));
        order.setText(mapParam.get("new_text")[0]);
        if (currentUser != null) {
            if (order.getUser().getIdUser() == currentUser.getIdUser()) {
                inst.getOrderDao().update(order);
                result = "complete";
            } else {
                result = "wrong";
            }
        } else {
            result = "error";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}