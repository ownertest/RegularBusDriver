package com.tel.china.regularbusdiver.http;


import android.net.Uri;
import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.creditease.shangtongdai.Config;
import com.creditease.shangtongdai.StdApplication;
import com.creditease.shangtongdai.URLConfig;
import com.creditease.shangtongdai.util.Log;
import com.creditease.shangtongdai.util.Md5Util;
import com.creditease.shangtongdai.util.TrackingUtil;
import com.creditease.tracking.TrackingAgent;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.google.gson.Gson;
import com.tendcloud.tenddata.TCAgent;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by longyi on 14-12-24.
 */
public class QxfRequest extends Request<JSONObject> {
    private final Response.Listener listener;
    private String url;
    private Map<String, String> params;
    private boolean isCache = false;
    private String cache_key;
    private long startTime = System.currentTimeMillis();

    private static final String EVENT_API_REQUEST = "API";

    public QxfRequest(int method, final String url, final QxfResponseListener<JSONObject> listener) {
        this(method, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (listener != null)
                        listener.onResponse(response);
                } catch (Exception e) {
                    if (e != null && e.getMessage() != null) {
                        e.printStackTrace();
                        trackNetworkError(e, url);
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                try {
                    if (listener != null)
                        listener.onErrorResponse(error);
                } catch (Exception e) {
                    if (e != null && e.getMessage() != null) {
                        e.printStackTrace();
                        trackNetworkError(e, url);
                    }
                }
            }
        });
    }

    public QxfRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.url = url;
    }

    private static void trackNetworkError(Exception e, String url) {
        TCAgent.onEvent(StdApplication.instance.getApplicationContext(), url, e.getMessage());
        //google analytics
        Tracker tracker = StdApplication.instance.getGaTracker();
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(TrackingUtil.GACategoryError)
                .setAction(url)
                .setLabel(e.getMessage())
                .build());
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            if (response.headers != null && !response.headers.containsKey(HTTP.CONTENT_TYPE) && url.contains("api.map.baidu.com")) {
                response.headers.put(HTTP.CONTENT_TYPE, "text/json; charset=utf-8");
            }
            String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            //解密
            if (!TextUtils.isEmpty(jsonString)) {
                if (jsonString.startsWith("{") && jsonString.endsWith("}")) {
                    //已经是明文
                    Log.d("返回的明文数据: " + jsonString);
                } else {
//                    jsonString = AesUtil.decrypt(jsonString, secret);
//                    Log.d("解密之后的数据: " + jsonString);
                    Log.d("返回的数据格式错误：QxfRequest.parseNetworkResponse");
                }
            }
            Log.d("isCache " + isCache);
            Cache.Entry entry = HttpHeaderParser.parseCacheHeaders(response);
            if (getMethod() == Method.GET && !isCache) {
                long cacheTime = URLConfig.getCacheTime(url);
                if (cacheTime > 0) {
                    entry.softTtl = System.currentTimeMillis() + cacheTime;
                    entry.ttl = entry.softTtl;
                }
            }
            return Response.success(new JSONObject(jsonString), entry);
        } catch (Exception je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    public void addMarker(String tag) {
        super.addMarker(tag);
        if ("cache-hit".equalsIgnoreCase(tag)) {
            isCache = true;
            Log.d("tag: " + tag + " " + getUrl());
        }
    }


    @Override
    protected void deliverResponse(JSONObject response) {
        trackingApi(null);
        listener.onResponse(response);
        saveABTestToken(response);
    }

    /**
     * tracking里加上这两个域。testid有三态：
     * 1. 空字符串或者域不存在： 表示不做任何处理。tracking里的testid和testgroup保持上一次的值
     * 2. “-1”：表示清空测试，tracking里的testid和testgroup都设为空字符串。
     * 3. 其它非空字符串：更新测试，tracking里的testid和testgroup设为此次返回值
     *
     * @param response http response
     */
    private void saveABTestToken(JSONObject response) {
        try {
            String testID = response.optString(Config.key_test_id);
            if (TextUtils.isEmpty(testID)) {
                return;
            }
            if (testID.equals("-1")) {
                StdApplication.updateABTestInfo("", "");

                return;
            }
            StdApplication.updateABTestInfo(testID, response.optString(Config.key_test_group));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deliverError(VolleyError error) {
        trackingApi(error);
        super.deliverError(error);
    }


    private void trackingApi(VolleyError error) {
        try {
            long duration = System.currentTimeMillis() - startTime;
            Uri uri = Uri.parse(this.url);
            String path = uri.getPath();
            if (path != null && path.startsWith("/"))
                path = path.substring(1);
            String api = path.replaceAll("/", "_");
            if (api.startsWith("api_v1_for_user_")) {
                api = api.substring("api_v1_for_user_".length());
            }
            Log.d("api " + api);
            String network = Config.network_type;
            Map<String, String> map = new HashMap<String, String>();
            map.put("duration", String.valueOf(duration));
            map.put("api", api);
            map.put("network", network);
            if (!TextUtils.isEmpty(Config.wifissid)) {
                map.put("wifissid", Config.wifissid);
            }
            //错误记录
            if (error != null) {
                String msg = error.getMessage();
                if (msg != null && msg.length() > 200)
                    msg = msg.substring(0, 200);
                map.put("error_message", msg);
                Throwable cause = error.getCause();
                if (cause != null) {
                    map.put("error_type", cause.getClass().getSimpleName());
                }
            }
            TrackingAgent.onEvent(StdApplication.instance.getApplicationContext(), EVENT_API_REQUEST, "", map);
            String label = calcRequestTimeLabel(duration);
            //talking data
            TCAgent.onEvent(StdApplication.instance.getApplicationContext(), api, label);
            //google analytics
            Tracker tracker = StdApplication.instance.getGaTracker();
            tracker.send(new HitBuilders.TimingBuilder()
                    .setCategory("API")
                    .setVariable(path)
                    .setLabel(network)
                    .setValue(duration)
                    .build());
        } catch (Exception e) {
            //ignore
        }
    }

    private String calcRequestTimeLabel(long duration) {
        try {
            String label;
            String network = !TextUtils.isEmpty(Config.network_type) ? Config.network_type : "unknown";
            if (duration <= 2000) {
                //两秒以内的，100ms为间隔
                long t = (long) (Math.ceil(duration / 100.0) * 100);
                label = network + "_" + t + "ms";
            } else if (duration <= 10000) {
                //10秒以内的，以500ms为间隔
                long t = (long) (Math.ceil(duration / 500.0) * 500);
                label = network + "_" + t + "ms";
            } else {
                //大于10秒的统一计为10秒
                label = network + "_gt10s";
            }
            return label;
        } catch (Exception e) {
            //ignore
            e.printStackTrace();
        }
        return "";
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        params = params == null ? new TreeMap<String, String>() : new TreeMap<String, String>(params);
        Set<String> empty = new HashSet<String>();
        for (String key : params.keySet()) {
            Log.d("请求参数：" + key + " " + params.get(key));
            String value = params.get(key);
            if (value == null) {
                empty.add(key);
            }
        }
        //检查，如果有null的参数，替换为empty，不然volley会报错
        for (String key : empty) {
            params.put(key, "");
        }

        //cache key部分，不包含时间戳
        cache_key = Md5Util.encrypt(url + new Gson().toJson(params));
        //加入时间参数
        params.put(Config.key_timestamp, String.valueOf(System.currentTimeMillis()));

        //如果是加密的方式
//        String auth = params.remove(Config.key_auth);
//        if (TextUtils.isEmpty(auth)) {
//            auth = "false";
//        }
//        String token = params.remove(Config.key_token);
//        String secret = params.remove(Config.key_user_secret);
//        setSecret(secret);
//        if ("true".equalsIgnoreCase(auth)) {
//            //参数需要加密
//            params.put(Config.key_timestamp, String.valueOf(System.currentTimeMillis()));
//            Log.d("加密部分的参数：" + new Gson().toJson(params));
//            Log.d("加密秘钥：" + secret);
//            String data = AesUtil.encrypt(new Gson().toJson(params), secret);
//            params.clear();
//            params.put(Config.key_data, data);
//            params.put(Config.key_ev, Config.encrypt_way);
//            params.put(Config.key_kv, (Config.default_secret_key.equals(secret) ? "uc" : "u") + "_2_1");
//        }
//        if (!TextUtils.isEmpty(token)) {
//            params.put(Config.key_token, token);
//        }
//        params.put(Config.key_auth, auth);

        //更新url
        StringBuilder sbURL;
        int idx = url.indexOf("?");
        if (idx > 0) {
            sbURL = new StringBuilder(url.substring(0, idx + 1));
        } else {
            sbURL = new StringBuilder(url).append("?");
        }
        //更新参数
        StringBuilder sbParam = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            if (value == null) {
                value = "";
            }
            sbParam.append(urlEncode(key)).append("=").append(urlEncode(value)).append("&");
        }
        //加入sn验证参数
        //去掉末尾&
        String verifyString = sbParam.substring(0, sbParam.length() - 1);
        sbParam.append(urlEncode(Config.key_sn)).append("=").append(Md5Util.encrypt(verifyString, Config.salt));

        if (getMethod() == Method.GET) {
            //更新完成
            this.url = sbURL.append(sbParam).toString();
        } else {
            this.params = params;
            this.params.put(urlEncode(Config.key_sn), Md5Util.encrypt(verifyString, Config.salt));
            //清空一下对应的cache
            RequestManager.getRequestQueue().getCache().remove(cache_key);
        }
    }

    private String urlEncode(String value) {
        try {
            return URLEncoder.encode(value, RequestManager.encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String getCacheKey() {
        return cache_key;
    }
}