package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class UserUpdateInfoManager {
    private UserUpdateInfoManager() {
    }

    private static UserUpdateInfoManager instance = null;

    public static UserUpdateInfoManager getInstance() {
        if (instance == null) {
            instance = new UserUpdateInfoManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam) {
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        User currentUser = (User) inst.getUsersDao().
                getAuthByName(mapParam.get("username")[0]);
        currentUser.setFirstName(mapParam.get("first_name")[0]);
        currentUser.setLastName(mapParam.get("last_name")[0]);
        currentUser.setPhone(mapParam.get("phone")[0]);
        inst.getUsersDao().update(currentUser);
        jsonObject.put("result", "complete");
        return jsonObject;
    }
}