package com.level.managers.user;

import com.level.dao.entity.Admin;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.parseLong;


public class UserAdminRemovalManager {
    private static UserAdminRemovalManager instance = null;

    public static UserAdminRemovalManager getInstance() {
        if (instance == null) {
            instance = new UserAdminRemovalManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam, String adminName) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        String result;
        Admin currAdmin = (Admin) inst.getAdminsDao().getAuthByName(adminName);
        if (currAdmin.getIdAdmin() == 1) {
            inst.getUsersDao().delete(inst.getUsersDao()
                    .getEntityByID(parseLong(mapParam.get("id_user")[0])));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}