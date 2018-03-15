package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Long.parseLong;


public class CommentsDeleteManager {
    private static CommentsDeleteManager instance = null;

    public static CommentsDeleteManager getInstance() {
        if (instance == null) {
            instance = new CommentsDeleteManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam) {
        String result = null;
        JSONObject jsonObject = new JSONObject();
        User currentUser = (User) Factory.getInstance().getUsersDao()
                .getAuthByName(mapParam.get("username")[0]);
        Comments comments = (Comments) Factory.getInstance().getCommentsDao()
                .getEntityByID(parseLong(mapParam.get("id_comment")[0]));
        if (comments != null && currentUser != null) {
            if (comments.getUser().getIdUser() == currentUser.getIdUser()) {
                Factory.getInstance().getCommentsDao().delete(comments);
                result = "complete";
            } else {
                result = "wrong";
            }
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}