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
        Factory inst = Factory.getInstance();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonComments = new JSONObject();
        Map<Long, Comments> listComments = new TreeMap<>();
        Set<Comments> commentsSet = inst.getCommentDAO().getAllEntities();

        if (commentsSet != null) {
            for (Comments comments : commentsSet) {
                Comments comment = (Comments) inst.getCommentDAO()
                        .getEntityByID(comments.getIdComment());
                listComments.put(comment.getIdComment(), comment);
            }
        }

        for (Map.Entry<Long, Comments> entry : listComments.entrySet()) {
            jsonComments.put(entry.getKey(), serializableComment(entry.getValue()));
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