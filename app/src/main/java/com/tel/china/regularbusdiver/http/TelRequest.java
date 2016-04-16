package com.tel.china.regularbusdiver.http;


import android.text.TextUtils;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.Md5Util;

import org.apache.http.protocol.HTTP;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class TelRequest extends Request<JSONObject> {
    private final Response.Listener listener;
    private String url;
    private Map<String, String> params;
    private boolean isCache = false;
    private String cache_key;
    private long startTime = System.currentTimeMillis();

    private static final String EVENT_API_REQUEST = "API";

    public TelRequest(int method, final String url, final TelResponseListener<JSONObject> listener) {
        this(method, url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (listener != null)
                        listener.onResponse(response);
                } catch (Exception e) {
                    if (e != null && e.getMessage() != null) {
                        e.printStackTrace();
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

                    }
                }
            }
        });
    }

    public TelRequest(int method, String url, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
        this.url = url;
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
                    Log.d("返回的数据格式错误：TelRequest.parseNetworkResponse");
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
        listener.onResponse(response);

    }



    @Override
    public void deliverError(VolleyError error) {

        super.deliverError(error);
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