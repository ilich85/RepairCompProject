package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class CommentsForUserManager {
    private static CommentsForUserManager instance = null;

    public static CommentsForUserManager getInstance() {
        if (instance == null) {
            instance = new CommentsForUserManager();
        }
        return instance;
    }

    public JSONObject usersComments(String username) {
        Factory inst = Factory.getInstance();
        User currUser = (User) inst.getUsersDao().getAuthByName(username);
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonComments = new JSONObject();
        if (inst.getUsersDao().getAuthByName(username) != null) {
            for (Map.Entry<Long, Object> entry : new TreeMap<>(inst.getCommentsDao()
                    .listAll()).entrySet()) {
                Comments comments = (Comments) entry.getValue();
                if (comments.getUser().equals(currUser)) {
                    jsonComments.put(entry.getKey(), serializableComment(comments));
                    jsonObject.put("users_comments", jsonComments);
                }
            }
        } else {
            return null;
        }
        return jsonObject;
    }

    private JSONObject serializableComment(Comments comments) {
        JSONObject commentJson = new JSONObject();
        commentJson.put("id", comments.getIdComment());
        commentJson.put("date", comments.getDate());
        commentJson.put("text", comments.getText());
        return commentJson;
    }
}