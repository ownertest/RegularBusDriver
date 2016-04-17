package com.tel.china.regularbusdiver.http;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.text.format.DateUtils;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.tel.china.regularbusdiver.BuildConfig;
import com.tel.china.regularbusdiver.ui.activity.BaseActivity;
import com.tel.china.regularbusdiver.system.StdApplication;
import com.tel.china.regularbusdiver.util.Log;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by longyi on 14-12-24.
 */
public class RequestManager {
    public static final String encoding = "UTF-8";
    private static RequestQueue requestQueue;
    private static ImageLoader imageLoader;

    private static Map<String, String> extraHeader;
    private static final int DEFAULT_MAX_RETRIES = 1;
    private static final float DEFAULT_BACKOFF_MULT = 1.0f;

    public static String FILTER_NEED_NOT_CANCEL = "need_not_cancel";

    private RequestManager() {
    }

    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context, new TelHurlStack());
        //初始化请求头
        initExtraHeader();
    }

    private static void initExtraHeader() {
        extraHeader = new HashMap<>();
        extraHeader.put("User-Agent", StdApplication.userAgent);
        extraHeader.put("Connection", "Keep-Alive");
        extraHeader.put("Charset", "UTF-8");
        extraHeader.put("platform", "android");
        extraHeader.put("platform_version", Build.VERSION.RELEASE);
        extraHeader.put("device_guid", StdApplication.deviceGuid);
        extraHeader.put("version_code", String.valueOf(BuildConfig.VERSION_CODE));
        extraHeader.put("version_name", BuildConfig.VERSION_NAME);
    }

    public static RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            throw new IllegalStateException("request queue not initialized");
        }
        return requestQueue;
    }

    public static ImageLoader getImageLoader() {
        if (imageLoader == null) {
            throw new IllegalStateException("image loader not initialized");
        }
        return imageLoader;
    }

    public static Map<String, String> getExtraHeader() {
        return extraHeader;
    }

    /**
     * 当发起此请求的activity执行onDestroy时，此request会被cancel
     */
    public static void backgroundRequest(int method, String url, Map<String, String> params, TelResponseListener<JSONObject> listener) {
        backgroundRequest(method, url, params, listener, (int) (DateUtils.SECOND_IN_MILLIS * 30));
    }

    /**
     * @param filter request cancel 的filter
     */
    public static void backgroundRequest(String filter, int method, String url, Map<String, String> params, TelResponseListener<JSONObject> listener) {
        backgroundRequest(filter, method, url, params, listener, (int) (DateUtils.SECOND_IN_MILLIS * 30));
    }

    /**
     * 当发起此请求的activity执行onDestroy时，此request会被cancel
     */
    public static void backgroundRequest(int method, String url, Map<String, String> params, TelResponseListener<JSONObject> listener, int timeoutMS) {
        backgroundRequest(BaseActivity.mCurrentActivity == null ? null : BaseActivity.mCurrentActivity.getClass().getName()
                , method, url, params, listener, timeoutMS);
    }

    /**
     * @param filter request cancel 的filter
     */
    public static void backgroundRequest( String filter, int method, String url, Map<String, String> params, TelResponseListener<JSONObject> listener, int timeoutMS) {
        TelRequest request = new TelRequest(method, url, listener);
        if (!TextUtils.isEmpty(filter))
            request.setTag(filter);
        request.setRetryPolicy(new TelRetryPolicy(timeoutMS, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT));
        request.setParams(params);

        RequestQueue queue = RequestManager.getRequestQueue();
        Log.d("cache key: " + request.getCacheKey());
        Log.e("request_info", request.getUrl());
        queue.add(request);
    }

    public static void clearCache(String url, Map<String, String> params) {
        TelRequest request = new TelRequest(Request.Method.GET, url, new TelResponseListener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

            }

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        request.setParams(params);
        String key = request.getCacheKey();
        Cache cache = RequestManager.getRequestQueue().getCache();
        cache.remove(key);
        Log.d("clear cache: " + key);
    }

    /**
     * 获取通用http请求params，含session_id, user_id，设备ID等。
     *
     * @return 含通用信息的params
     */
    public static Map<String, String> getCommonParams() {
        Map<String, String> params = new TreeMap<>();
//        params.put(Config.key_user_id, String.valueOf(user.user_id));
//        params.put(Config.key_session_id, QxfApplication.getSessionId());
//        params.put(Config.key_user_id, "0");

        return params;
    }

}