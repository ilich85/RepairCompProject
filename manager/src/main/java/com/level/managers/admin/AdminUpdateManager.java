package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class AdminUpdateManager {
    private AdminUpdateManager() {
    }

    private static AdminUpdateManager instance = null;

    public static AdminUpdateManager getInstance() {
        if (instance == null) {
            instance = new AdminUpdateManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam, String adminName) {
        AuthDao adminDao = Factory.getInstance().getAdminsDao();
        JSONObject jsonObject = new JSONObject();
        String result;
        Admin currentAdmin = (Admin) adminDao.getAuthByName(adminName);
        if (currentAdmin != null) {
            if (currentAdmin.getIdAdmin() == 1) {
                Admin adminForUpdate = (Admin) adminDao
                        .getEntityByID(Integer.parseInt(mapParam.get("id")[0]));
                adminForUpdate.setAdminName(mapParam.get("admin_name")[0]);
                adminForUpdate.setEmail(mapParam.get("email")[0]);
                adminForUpdate.setPassword(mapParam.get("password")[0]);
                adminDao.update(adminForUpdate);
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