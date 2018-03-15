package com.level.managers.messages;

import com.level.dao.entity.Messages;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.TreeMap;


public class MessagesListManager {
    private static MessagesListManager instance = null;

    public static MessagesListManager getInstance() {
        if (instance == null) {
            instance = new MessagesListManager();
        }
        return instance;
    }

    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonMessages = new JSONObject();
        for (Map.Entry<Long, Object> entry : new TreeMap<>(Factory.getInstance()
                .getMessagesDao().listAll()).entrySet()) {
            jsonMessages.put(entry.getKey(), serializableMessage((Messages) entry.getValue()));
            jsonObject.put("messages", jsonMessages);
        }
        return jsonObject;
    }

    private JSONObject serializableMessage(Messages messages) {
        JSONObject messagesJson = new JSONObject();
        messagesJson.put("id", messages.getIdMessage());
        messagesJson.put("mess_text", messages.getText());
        return messagesJson;
    }
}