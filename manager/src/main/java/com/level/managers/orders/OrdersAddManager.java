package com.level.managers.orders;

import com.level.dao.entity.Orders;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class OrdersAddManager {
    private OrdersAddManager() {
    }

    private static OrdersAddManager instance = null;

    public static OrdersAddManager getInstance() {
        if (instance == null) {
            instance = new OrdersAddManager();
        }
        return instance;
    }

    public JSONObject add(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        String result;
        User currentUser = (User) Factory.getInstance().getUsersDao()
                .getAuthByName(mapParam.get("username")[0]);
        if (currentUser != null) {
            Factory.getInstance().getOrdersDao().add(new Orders(mapParam.get("order_text")[0],
                    new SimpleDateFormat().format(new Date()), currentUser));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}