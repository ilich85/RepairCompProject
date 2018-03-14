package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class AdminAuthManager {
    private AdminAuthManager() {
    }

    private static AdminAuthManager instance = null;

    public static AdminAuthManager getInstance() {
        if (instance == null) {
            instance = new AdminAuthManager();
        }
        return instance;
    }

    public JSONObject auth(Map<String, String[]> paramMap) {
        JSONObject jsonObject = new JSONObject();
        String result;
        Admin currentAdmin = (Admin) Factory.getInstance().getAdminDao()
                .getAuthByName(paramMap.get("admin_name")[0]);
        if (currentAdmin != null) {
            String currPassword = currentAdmin.getPassword();
            if (paramMap.get("password")[0].equals(currPassword)) {
                if (currentAdmin.getIdAdmin() == 1) {
                    result = "super";
                } else {
                    result = "complete";
                }
            } else {
                result = "wrong_pass";
            }
        } else {
            result = "error";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}