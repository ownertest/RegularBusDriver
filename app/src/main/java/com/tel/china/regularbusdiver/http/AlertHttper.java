package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lei on 8/4/15.
 */
public class AlertHttper {
    public static void backgroundGetAlert(QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_alert, params, listener);
    }
}
