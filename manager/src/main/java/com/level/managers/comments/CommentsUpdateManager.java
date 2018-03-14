package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import static java.lang.Long.*;


public class CommentsUpdateManager {
    private static CommentsUpdateManager instance = null;

    public static CommentsUpdateManager getInstance() {
        if (instance == null) {
            instance = new CommentsUpdateManager();
        }
        return instance;
    }

    public JSONObject update(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        User currentUser = (User) inst.getUserDao()
                .getAuthByName(mapParam.get("username")[0]);
        Comments comments = (Comments) inst.getCommentDAO()
                .getEntityByID(parseLong(mapParam.get("id_comment")[0]));
            comments.setText(mapParam.get("new_text")[0]);
            comments.setDate(new SimpleDateFormat().format(new Date()));
        if (currentUser != null) {
            if (comments.getUser().getIdUser() == currentUser.getIdUser()) {
                inst.getCommentDAO().update(comments);
                result = "complete";
            } else {
                result = "wrong";
            }
        } else {
            result = "error";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}