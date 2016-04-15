package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 14/12/30.
 */
public class RecommendHttper {
    public static void backgroundLoadRecommend(QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_recommend, param, listener);
    }
}