package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class CommentsListManager {
    private static CommentsListManager instance = null;

    public static CommentsListManager getInstance() {
        if (instance == null) {
            instance = new CommentsListManager();
        }
        return instance;
    }

    public JSONObject list() {
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonComments = new JSONObject();
        for (Map.Entry<Long, Object> entry : new TreeMap<>(Factory.getInstance()
                .getCommentsDao().listAll()).entrySet()) {
            jsonComments.put(entry.getKey(), serializableComment((Comments) entry.getValue()));
            jsonObject.put("comments", jsonComments);
        }
        return jsonObject;
    }

    private JSONObject serializableComment(Comments comments) {
        JSONObject commentJson = new JSONObject();
        commentJson.put("id", comments.getIdComment());
        commentJson.put("date", comments.getDate());
        commentJson.put("text", comments.getText());
        commentJson.put("user", comments.getUser().getUsername());
        return commentJson;
    }
}