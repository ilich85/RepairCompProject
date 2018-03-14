package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class AdminRegistrationManager {
    private AdminRegistrationManager() {
    }

    private static AdminRegistrationManager instance = null;

    public static AdminRegistrationManager getInstance() {
        if (instance == null) {
            instance = new AdminRegistrationManager();
        }
        return instance;
    }

    public JSONObject registration(Map<String, String[]> paramMap) {
        String result;
        JSONObject jsonObject = new JSONObject();
        AuthDao adminDao = Factory.getInstance().getAdminDao();
        Admin currentAdmin = (Admin) adminDao.getAuthByName(paramMap.get("current_admin")[0]);
        if (currentAdmin != null) {
            if (currentAdmin.getIdAdmin() == 1) {
                adminDao.add(new Admin((paramMap.get("new_admin_name")[0]),
                        (paramMap.get("new_email")[0]), (paramMap.get("new_password")[0])));
                result = "complete";
            } else {
                result = "error";
            }
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}