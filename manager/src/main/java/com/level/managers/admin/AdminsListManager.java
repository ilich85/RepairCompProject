package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.TreeMap;


public class AdminsListManager {
    private static AdminsListManager instance = null;

    public static AdminsListManager getInstance() {
        if (instance == null) {
            instance = new AdminsListManager();
        }
        return instance;
    }

    public JSONObject list(String currAdmin) {
        AuthDao adminDao = Factory.getInstance().getAdminsDao();
        Admin currentAdmin = (Admin) adminDao.getAuthByName(currAdmin);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonAdmins = new JSONObject();
        if (currentAdmin.getIdAdmin() == 1) {
            for (Map.Entry<Long, Object> entry : new TreeMap<>(Factory.getInstance()
                    .getAdminsDao().listAll()).entrySet()) {
                jsonAdmins.put(entry.getKey(), serializableAdmin((Admin) entry.getValue()));
                jsonObject.put("admins", jsonAdmins);
            }
        }
        return jsonObject;
    }

    private JSONObject serializableAdmin(Admin admin) {
        JSONObject adminsJson = new JSONObject();
        adminsJson.put("id", admin.getIdAdmin());
        adminsJson.put("admin_name", admin.getAdminName());
        adminsJson.put("email", admin.getEmail());
        adminsJson.put("password", admin.getPassword());
        return adminsJson;
    }
}