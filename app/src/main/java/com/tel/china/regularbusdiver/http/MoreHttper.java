package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.creditease.zhiwang.Config;
import com.creditease.zhiwang.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by yixin on 15/7/17.
 */
public class MoreHttper {
    public static void backgroundShareSuccess(QxfResponseListener<JSONObject> listener, String userToken, String type) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_token, userToken);
        param.put(Config.key_target_name, type);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_SHARE_NOTIFY, param, listener);
    }

    public static void fetchMoreReminders(long userId, QxfResponseListener listener){
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_user_id, String.valueOf(userId));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_MORE_REMINDERS, param, listener);
    }
}
