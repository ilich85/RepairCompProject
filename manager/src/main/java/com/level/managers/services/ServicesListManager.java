package com.level.managers.services;

import com.level.dao.entity.Services;
import com.level.dao.interfaces.EntityDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.*;


public class ServicesListManager {
    private static ServicesListManager instance = null;

    public static ServicesListManager getInstance() {
        if (instance == null) {
            instance = new ServicesListManager();
        }
        return instance;
    }

    public JSONObject list() {
        EntityDao serviceDao = Factory.getInstance().getServicesDao();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonServices = new JSONObject();
        for (Map.Entry<Long, Object> entry : new TreeMap<>(serviceDao.listAll()).entrySet()) {
            jsonServices.put(entry.getKey(), serializableService((Services) entry.getValue()));
            jsonObject.put("services", jsonServices);
        }
        return jsonObject;
    }

    private JSONObject serializableService(Services services) {
        JSONObject servicesJson = new JSONObject();
        servicesJson.put("id", services.getIdService());
        servicesJson.put("description", services.getDescription());
        servicesJson.put("price", services.getPrice());
        return servicesJson;
    }
}