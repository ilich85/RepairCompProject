package com.level.managers.comments;

import com.level.dao.entity.Comments;
import com.level.dao.entity.User;
import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


public class CommentsAddManager {
    private CommentsAddManager() {
    }

    private static CommentsAddManager instance = null;

    public static CommentsAddManager getInstance() {
        if (instance == null) {
            instance = new CommentsAddManager();
        }
        return instance;
    }

    public JSONObject add(Map<String, String[]> mapParam, String username) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        User currentUser = (User) inst.getUsersDao().getAuthByName(username);
        if (currentUser != null) {
            inst.getCommentsDao().add(new Comments(new SimpleDateFormat()
                    .format(new Date()), mapParam.get("comment_text")[0], currentUser));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}