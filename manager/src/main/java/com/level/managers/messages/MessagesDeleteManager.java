package com.level.managers.messages;

import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.*;


public class MessagesDeleteManager {
    private static MessagesDeleteManager instance = null;

    public static MessagesDeleteManager getInstance() {
        if (instance == null) {
            instance = new MessagesDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        if (inst.getAdminsDao().getAuthByName(mapParam.get("admin_name")[0]) != null) {
            inst.getMessagesDao().delete(inst.getMessagesDao()
                    .getEntityByID(parseLong(mapParam.get("id_message")[0])));
            result = "complete";
        } else {
            result = "error";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}