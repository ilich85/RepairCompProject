package com.level.managers.user;

import com.level.dao.entity.User;
import com.level.dao.interfaces.AuthDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserRegistrationManager {
    private UserRegistrationManager() {
    }

    private static UserRegistrationManager instance = null;

    public static UserRegistrationManager getInstance() {
        if (instance == null) {
            instance = new UserRegistrationManager();
        }
        return instance;
    }

    private static final Set<User> USER_SET = new HashSet<>();
    private static final Set<String> USER_NAME = new HashSet<>();
    private static final Set<String> USER_EMAIL = new HashSet<>();


    public JSONObject registration(Map<String, String[]> paramMap) {
        JSONObject jsonObject = new JSONObject();
        AuthDao userDao = Factory.getInstance().getUserDao();
        String result;
        String username = paramMap.get("username")[0].toLowerCase();
        String email = paramMap.get("email")[0];
        for (Object o : userDao.getAllEntities()) {
            USER_SET.add((User) o);
        }
        for (User user : USER_SET) {
            USER_NAME.add(user.getUsername());
            USER_EMAIL.add(user.getEmail());
        }
        if (USER_NAME.contains(username)) {
            result = "exists";
        } else if (USER_EMAIL.contains(email)) {
            result = "mail_exists";
        } else {
            userDao.add(new User(username, email,
                    paramMap.get("password")[0], paramMap.get("phone")[0]));
            USER_NAME.add(username);
            result = "complete";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}