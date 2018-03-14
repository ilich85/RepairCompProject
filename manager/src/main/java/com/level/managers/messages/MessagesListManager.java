package com.level.managers.messages;

import com.level.dao.entity.Messages;
import com.level.dao.interfaces.EntityDao;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class MessagesListManager {
    private static MessagesListManager instance = null;

    public static MessagesListManager getInstance() {
        if (instance == null) {
            instance = new MessagesListManager();
        }
        return instance;
    }

    public JSONObject list() {
        EntityDao messageDao = Factory.getInstance().getMessagesDAO();
        Set<Messages> messagesSet = messageDao.getAllEntities();
        Map<Long, Messages> listMessages = new HashMap<>();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonMessages = new JSONObject();
        if (messagesSet != null) {
            for (Messages messages : messagesSet) {
                Messages message = (Messages) messageDao.getEntityByID(messages.getIdMessage());
                listMessages.put(message.getIdMessage(), message);
            }
        }

        for (Map.Entry<Long, Messages> entry : listMessages.entrySet()) {
            jsonMessages.put(entry.getKey(), serializableMessage(entry.getValue()));
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