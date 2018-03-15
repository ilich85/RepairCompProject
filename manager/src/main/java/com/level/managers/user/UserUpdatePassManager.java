package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class UserUpdatePassManager {
    private UserUpdatePassManager() {
    }

    private static UserUpdatePassManager instance = null;

    public static UserUpdatePassManager getInstance() {
        if (instance == null) {
            instance = new UserUpdatePassManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        AuthDao inst = Factory.getInstance().getUsersDao();
        String result;
        User currentUser = (User) inst.getAuthByName(mapParam.get("username")[0]);
        String newPass = mapParam.get("new_pass")[0];
        if (currentUser.getPassword().equals(mapParam.get("old_pass")[0])) {
            if (newPass.equals(mapParam.get("repeat_pass")[0])) {
                currentUser.setPassword(newPass);
                inst.update(currentUser);
                result = "complete";
            } else {
                result = "wrong";
            }
        } else {
            result = "error";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}