package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class UserDeleteManager {
    private UserDeleteManager() {
    }

    private static UserDeleteManager instance = null;

    public static UserDeleteManager getInstance() {
        if (instance == null) {
            instance = new UserDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> paramMap) {
        String result;
        JSONObject jsonObject = new JSONObject();

        Factory inst = Factory.getInstance();
        User currentUser = (User) inst.getUsersDao().getAuthByName(paramMap.get("username")[0]);
        if (currentUser.getPassword().equals(paramMap.get("pass_del")[0])) {
            inst.getUsersDao().delete(currentUser);
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}