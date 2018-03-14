package com.level.managers.orders;

import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.parseLong;


public class OrdersAdminRemovalManager {
    private static OrdersAdminRemovalManager instance = null;

    public static OrdersAdminRemovalManager getInstance() {
        if (instance == null) {
            instance = new OrdersAdminRemovalManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        String result;
        if (inst.getAdminDao().getAuthByName(mapParam.get("admin_name")[0]) != null) {
            inst.getOrderDao().delete(inst.getOrderDao()
                    .getEntityByID(parseLong(mapParam.get("order_id")[0])));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}