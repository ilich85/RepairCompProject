package com.level.managers.user;

import com.level.dao.entity.Admin;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        Map<Long, User> users = new HashMap<>();
        JSONObject jsonUsers = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        Admin currAdmin = (Admin) inst.getAdminDao().getAuthByName(adminName);
        if (currAdmin != null) {
            Set<User> userSet = inst.getUserDao().getAllEntities();
            if (userSet != null) {
                for (User u : userSet) {
                    User user = (User) inst.getUserDao().getEntityByID(u.getIdUser());
                    users.put(user.getIdUser(), user);
                }
            }
        }
        for (Map.Entry<Long, User> entry : users.entrySet()) {
            jsonUsers.put(entry.getKey(), serializableUser(entry.getValue()));
            jsonObject.put("users", jsonUsers);
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