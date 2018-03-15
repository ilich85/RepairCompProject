package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class UserAuthManager {
    private UserAuthManager() {
    }

    private static UserAuthManager instance = null;

    public static UserAuthManager getInstance() {
        if (instance == null) {
            instance = new UserAuthManager();
        }
        return instance;
    }

    public  JSONObject auth(Map<String, String[]> paramMap) {
        JSONObject jsonObject = new JSONObject();
        String result;
        User user = (User) Factory.getInstance().getUsersDao()
                .getAuthByName(paramMap.get("username")[0]);
        if (user != null) {
            String currPassword = user.getPassword();
            if (paramMap.get("password")[0].equals(currPassword)) {
                result = "complete";
            } else {
                result = "error";
            }
        } else {
            result = "not_exist";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}