package com.level.managers.messages;

import com.level.dao.entity.Admin;
import com.level.dao.entity.Messages;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class MessagesAddManager {
    private MessagesAddManager() {
    }

    private static MessagesAddManager instance = null;

    public static MessagesAddManager getInstance() {
        if (instance == null) {
            instance = new MessagesAddManager();
        }
        return instance;
    }

    public JSONObject add(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        String result;
        Admin currAdmin = (Admin) Factory.getInstance().getAdminsDao()
                .getAuthByName(mapParam.get("admin_name")[0]);
        if (currAdmin != null) {
            Factory.getInstance().getMessagesDao().add(new Messages(new SimpleDateFormat()
                    .format(new Date()), mapParam.get("message_text")[0]));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}