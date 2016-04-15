package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by wuyq on 2014-08-12 下午2:45.
 */
public class FeedbackHttper {
    public static void backgroundSubmitFeedback(String feedback, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_feedback, feedback);
        RequestManager.backgroundRequest(Request.Method.POST, URLConfig.http_feedback, params, listener);
    }

    public static void backgroundGetMessages(long last_id, int page_size, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_last_id, String.valueOf(last_id));
        params.put(Config.key_page_size, String.valueOf(page_size));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_feedback_list, params, listener);
    }
}
