package com.level.managers.comments;

import com.level.hibernateFactory.Factory;
import org.json.simple.JSONObject;

import java.util.Map;

public class CommentsAdminRemovalManager {
    private static CommentsAdminRemovalManager instance = null;

    public static CommentsAdminRemovalManager getInstance() {
        if (instance == null) {
            instance = new CommentsAdminRemovalManager();
        }
        return instance;
    }

    public JSONObject delete(Map<String, String[]> mapParam) {
        JSONObject jsonObject = new JSONObject();
        Factory inst = Factory.getInstance();
        String result;
        if (inst.getAdminsDao().getAuthByName(mapParam.get("admin_name")[0]) != null) {
            inst.getCommentsDao().delete(inst.getCommentsDao()
                    .getEntityByID(Long.parseLong(mapParam.get("comment_id")[0])));
            result = "complete";
        } else {
            result = "wrong";
        }
        jsonObject.put("result", result);
        return jsonObject;
    }
}