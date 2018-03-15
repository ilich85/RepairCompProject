package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;


public class UserInfoManager {
    private static UserInfoManager instance = null;

    public static UserInfoManager getInstance() {
        if (instance == null) {
            instance = new UserInfoManager();
        }
        return instance;
    }

    public JSONObject getUserInfo(String username) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user_info", serializableUser((User) Factory.getInstance()
                .getUsersDao().getAuthByName(username)));
        return jsonObject;
    }

    private JSONObject serializableUser(User user) {
        JSONObject usersJson = new JSONObject();
        usersJson.put("user_id", user.getIdUser());
        usersJson.put("username", user.getUsername());
        usersJson.put("email", user.getEmail());
        usersJson.put("phone", user.getPhone());
        usersJson.put("first_name", user.getFirstName() != null ? user.getFirstName() : "");
        usersJson.put("last_name", user.getLastName() != null ? user.getLastName() : "");
        return usersJson;
    }
}
