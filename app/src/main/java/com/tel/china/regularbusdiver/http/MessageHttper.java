package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;
import org.json.JSONObject;

import java.util.Map;


public class MessageHttper {

    /**
     * 获取从startId开始的最新message列表。
     *
     * @param startId  起始信息id
     * @param listener 响应监听
     */
    public static void backgroundGetMessageList(String startId, TelResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_last_message_id, startId);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_MESSAGE_LIST, params, listener);
    }
}
