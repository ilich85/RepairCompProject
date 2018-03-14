package com.level.managers.services;

import com.level.dao.entity.Services;
import com.level.dao.interfaces.EntityDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class ServicesListManager {
    private static ServicesListManager instance = null;

    public static ServicesListManager getInstance() {
        if (instance == null) {
            instance = new ServicesListManager();
        }
        return instance;
    }

    public JSONObject list() {
        EntityDao serviceDao = Factory.getInstance().getServiceDao();
        Map<Long, Services> listServices = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonServices = new JSONObject();
        Set<Services> servicesList = serviceDao.getAllEntities();
        if (servicesList != null) {
            for (Services services : servicesList) {
                Services service = (Services) serviceDao.getEntityByID(services.getIdService());
                listServices.put(service.getIdService(), service);
            }
        }
        Iterator<Map.Entry<Long, Services>> entries = listServices.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<Long, Services> entry = entries.next();
            jsonServices.put(entry.getKey(), serializableService(entry.getValue()));
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