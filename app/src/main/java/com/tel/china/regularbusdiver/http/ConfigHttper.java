package com.tel.china.regularbusdiver.http;

import android.text.format.DateUtils;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;
import com.creditease.zhiwang.QxfApplication;
import com.creditease.zhiwang.util.UniqueDeviceIDFactory;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 15/1/13.
 */
public class ConfigHttper {
    public static void backgroundGetAboutUsUrls(QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_imei, UniqueDeviceIDFactory.getIMEI(QxfApplication.instance));
        RequestManager.backgroundRequest(null, Request.Method.GET, URLConfig.http_about_us, param, listener);
    }

    public static void backgroundImageConfig(String size, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_platform, "android");
        param.put(Config.key_size, size);
        RequestManager.backgroundRequest(null, Request.Method.GET, URLConfig.http_image_config, param, listener);
    }

    public static void backgroundShareConfig(QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_share_config, param, listener, (int) (DateUtils.SECOND_IN_MILLIS * 5));
    }
}
