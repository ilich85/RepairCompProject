package com.level.managers.services;

import com.level.dao.entity.Services;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class ServicesAddManager {
    private ServicesAddManager() {
    }

    private static ServicesAddManager instance = null;

    public static ServicesAddManager getInstance() {
        if (instance == null) {
            instance = new ServicesAddManager();
        }
        return instance;
    }

    public JSONObject add(Map<String, String[]> mapParam, String adminName) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        if (inst.getAdminsDao().getAuthByName(adminName) != null) {
            inst.getServicesDao().add(new Services(mapParam.get("description")[0],
                    parseInt(mapParam.get("price")[0])));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}