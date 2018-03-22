package com.level.managers.services;

import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class ServicesDeleteManager {
    private ServicesDeleteManager() {
    }

    private static ServicesDeleteManager instance = null;

    public static ServicesDeleteManager getInstance() {
        if (instance == null) {
            instance = new ServicesDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam,String adminName) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        String result;
        if (inst.getAdminsDao().getAuthByName(adminName) != null) {
            inst.getServicesDao().delete(inst.getServicesDao()
                    .getEntityByID(parseInt(mapParam.get("id_service")[0])));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}