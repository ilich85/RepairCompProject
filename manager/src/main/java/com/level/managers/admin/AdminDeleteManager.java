package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.*;

public class AdminDeleteManager {
    private AdminDeleteManager() {
    }

    private static AdminDeleteManager instance = null;

    public static AdminDeleteManager getInstance() {
        if (instance == null) {
            instance = new AdminDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> paramMap) {
        AuthDao adminDao = Factory.getInstance().getAdminDao();
        Admin currentAdmin = (Admin) adminDao.getAuthByName(paramMap.get("admin_name")[0]);
        Admin adminOnRemoval = (Admin) adminDao.getEntityByID(parseLong(paramMap.get("id")[0]));
        JSONObject jsonObject = new JSONObject();
        String result;
        if (currentAdmin != null) {
            if (currentAdmin.getIdAdmin() == 1) {
                if (currentAdmin.getAdminName().equals(adminOnRemoval.getAdminName())) {
                    result = "forbidden";
                } else {
                    adminDao.delete(adminOnRemoval);
                    result = "complete";
                }
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