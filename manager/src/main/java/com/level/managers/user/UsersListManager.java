package com.level.managers.user;

import com.level.dao.entity.Admin;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.TreeMap;

public class UsersListManager {
    private static UsersListManager instance = null;

    public static UsersListManager getInstance() {
        if (instance == null) {
            instance = new UsersListManager();
        }
        return instance;
    }

    public JSONObject list(String adminName) {
        Factory inst = Factory.getInstance();
        JSONObject jsonUsers = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        Admin currentAdmin = (Admin) inst.getAdminsDao().getAuthByName(adminName);
        if (currentAdmin.getIdAdmin() == 1) {
            for (Map.Entry<Long, Object> entry : new TreeMap<>(inst.getUsersDao()
                    .listAll()).entrySet()) {
                jsonUsers.put(entry.getKey(), serializableUser((User) entry.getValue()));
                jsonObject.put("users", jsonUsers);
            }
        } else {
            return null;
        }
        return jsonObject;
    }

    private JSONObject serializableUser(User user) {
        JSONObject usersJson = new JSONObject();
        usersJson.put("id", user.getIdUser());
        usersJson.put("username", user.getUsername());
        usersJson.put("email", user.getEmail());
        usersJson.put("phone", user.getPhone());
        usersJson.put("first_name", user.getFirstName() != null ? user.getFirstName() : "");
        usersJson.put("last_name", user.getLastName() != null ? user.getLastName() : "");
        return usersJson;
    }
}