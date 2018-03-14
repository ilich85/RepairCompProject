package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


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
        User currUser = (User) inst.getUserDao().getAuthByName(username);
        Map<Long, Comments> userComments = new HashMap<>();
        Set<Comments> commentsSet = inst.getCommentDAO().getAllEntities();
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonComments = new JSONObject();
        if (commentsSet != null) {
            for (Comments comments : commentsSet) {
                Comments comment = (Comments) inst.getCommentDAO().getEntityByID(comments.getIdComment());
                if (currUser != null && comment.getUser().getUsername().equals(currUser.getUsername())) {
                    userComments.put(comment.getIdComment(), comment);
                }
            }
        }

        for (Map.Entry<Long, Comments> entry : userComments.entrySet()) {
            jsonComments.put(entry.getKey(), serializableComment(entry.getValue()));
            jsonObject.put("users_comments", jsonComments);

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