package com.level.managers.services;

import com.level.dao.entity.Services;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class ServicesUpdateManager {
    private ServicesUpdateManager() {
    }

    private static ServicesUpdateManager instance = null;

    public static ServicesUpdateManager getInstance() {
        if (instance == null) {
            instance = new ServicesUpdateManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam, String adminName) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        String result;
        if (inst.getAdminsDao().getAuthByName(adminName) != null) {
            Services service = (Services) inst.getServicesDao()
                    .getEntityByID(parseInt(mapParam.get("id_service")[0]));
            service.setDescription(mapParam.get("new_description")[0]);
            service.setPrice(parseInt(mapParam.get("new_price")[0]));
            inst.getServicesDao().update(service);
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}