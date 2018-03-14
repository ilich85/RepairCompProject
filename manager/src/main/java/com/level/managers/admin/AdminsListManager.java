package com.level.managers.admin;

import com.level.dao.entity.Admin;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class AdminsListManager {
    private static AdminsListManager instance = null;

    public static AdminsListManager getInstance() {
        if (instance == null) {
            instance = new AdminsListManager();
        }
        return instance;
    }

    public JSONObject list(String adminName) {
        Factory inst = Factory.getInstance();
        AuthDao adminDao = Factory.getInstance().getAdminDao();
        Admin currentAdmin = (Admin) adminDao.getAuthByName(adminName);
        Map<Long, Admin> listAdmins = new TreeMap<>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonAdmins = new JSONObject();
        if (currentAdmin.getIdAdmin() == 1) {
            Set<Admin> adminsSet = inst.getAdminDao().getAllEntities();
            if (adminsSet != null) {
                for (Admin admins : adminsSet) {
                    Admin admin = (Admin) adminDao.getEntityByID(admins.getIdAdmin());
                    listAdmins.put(admin.getIdAdmin(), admin);
                }
            }
            for (Map.Entry<Long, Admin> entry : listAdmins.entrySet()) {
                jsonAdmins.put(entry.getKey(), serializableAdmin(entry.getValue()));
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