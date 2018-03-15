package com.level.managers.messages;

import com.level.dao.entity.Admin;
import com.level.dao.entity.Messages;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static java.lang.Long.*;


public class MessagesUpdateManager {
    private static MessagesUpdateManager instance = null;

    public static MessagesUpdateManager getInstance() {
        if (instance == null) {
            instance = new MessagesUpdateManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        Admin currAdmin = (Admin) inst.getAdminsDao()
                .getAuthByName(mapParam.get("admin_name")[0]);
        Messages messages = (Messages) inst.getMessagesDao()
                .getEntityByID(parseLong(mapParam.get("id_message")[0]));
        if (currAdmin != null) {
            messages.setText(mapParam.get("new_message")[0]);
            messages.setDate(new SimpleDateFormat().format(new Date()));
            inst.getMessagesDao().update(messages);
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}