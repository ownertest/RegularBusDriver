package com.tel.china.regularbusdiver.http;

import android.content.Context;
import android.os.Build;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.creditease.shangtongdai.BuildConfig;
import com.creditease.shangtongdai.Config;
import com.creditease.shangtongdai.StdApplication;
import com.creditease.shangtongdai.activity.BaseActivity;
import com.creditease.shangtongdai.bean.User;
import com.creditease.shangtongdai.util.ChannelUtil;
import com.creditease.shangtongdai.util.ImageCache;
import com.creditease.shangtongdai.util.Log;

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
    private static ImageCache imageCache;
    private static final int DEFAULT_MAX_RETRIES = 1;
    private static final float DEFAULT_BACKOFF_MULT = 1.0f;

    public static int DEFAULT_TIMEOUT = (int) (DateUtils.SECOND_IN_MILLIS * 30);

    private RequestManager() {
    }

    public static void init(Context context) {
        requestQueue = Volley.newRequestQueue(context, new MockHttpStack(1000));
        imageCache = new ImageCache(context);
        imageLoader = new ImageLoader(requestQueue, imageCache);
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

    public static void backgroundRequest(StdRequest request) {
        request.setRetryPolicy(new StdRetryPolicy(DEFAULT_TIMEOUT, DEFAULT_MAX_RETRIES, DEFAULT_BACKOFF_MULT));
        RequestQueue queue = RequestManager.getRequestQueue();
        queue.add(request);
    }

    /**
     * 获取通用http请求params，含session_id, user_id，设备ID等。
     *
     * @return 含通用信息的params
     */
    public static Map<String, String> getCommonParams() {
        Map<String, String> params = new TreeMap<>();
        User user = StdApplication.getCurrentUser();
        //如果本地没有用户信息记录，说明用户未登录。采用默认信息
        if (user != null) {
            params.put(Config.key_user_id, String.valueOf(user.user_id));
            params.put(Config.key_session_id, StdApplication.getSessionId());
        } else {
            params.put(Config.key_user_id, "0");
        }

        //common 参数
        params.put(Config.key_device_model, Build.MODEL);
        params.put(Config.key_device_guid, StdApplication.deviceGuid);
        params.put(Config.key_channel, ChannelUtil.getChannel(StdApplication.instance.getApplicationContext(), "creditease"));

        return params;
    }

    public static ImageCache getImageCache() {
        return imageCache;
    }
}