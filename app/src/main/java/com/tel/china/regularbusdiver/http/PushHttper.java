package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.creditease.zhiwang.Config;
import com.creditease.zhiwang.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 14-8-12.
 */
public class PushHttper {
    public static void backgroundRegisterPush(String push_token, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_push_token, push_token);
        params.put(Config.key_push_channel_type, Config.push_channel_type);
        params.put(Config.key_push_channel_id, Config.app_id_prod);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_register_push, params, listener);
    }
}
