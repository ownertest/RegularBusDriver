package com.tel.china.regularbusdiver.http;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;

/**
 * Created by niutf on 16/1/25.
 */
public class StdRequest<T> extends Request<T> {

    protected static final String PROTOCOL_CHARSET = "utf-8";

    private static final String CODE = "code";
    private static final String DATA = "data";
    private static final String MESSAGE = "message";
    private static final int CODE_SUCCESS = 0;

    private String mUrl;
    private Map<String, String> mParams;
    private long startTime = System.currentTimeMillis();
    private StdResponseListener<T> mListener;
    private DataParser<T> mDataParser;

    public StdRequest(int method, final String url, Map<String, String> params, final DataParser<T> parser, final StdResponseListener<T> listener) {
        super(method, url, listener);
        mUrl = url;
        mParams = params;
        mListener = listener;
        mDataParser = parser;
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
            JSONObject json = new JSONObject(jsonString);
            Cache.Entry cache = HttpHeaderParser.parseCacheHeaders(response);

            int code = json.getInt(CODE);
            String msg = json.optString(MESSAGE);
            if (code == CODE_SUCCESS) {
                JSONObject dataJson = json.optJSONObject(DATA);
                T data = mDataParser.parse(dataJson);
                return Response.success(data, cache);
            } else {
                return Response.error(new StdApiError(code, msg));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

    @Override
    public Map<String, String> getParams() {
        return mParams;
    }

    //    public void setParams(Map<String, String> params) {
//        params = params == null ? new TreeMap<String, String>() : new TreeMap<String, String>(params);
//        Set<String> empty = new HashSet<String>();
//        for (String key : params.keySet()) {
//            Log.d("请求参数：" + key + " " + params.get(key));
//            String value = params.get(key);
//            if (value == null) {
//                empty.add(key);
//            }
//        }
//        //检查，如果有null的参数，替换为empty，不然volley会报错
//        for (String key : empty) {
//            params.put(key, "");
//        }
//
//        //cache key部分，不包含时间戳
//        cache_key = Md5Util.encrypt(url + new Gson().toJson(params));
//        //加入时间参数
//        params.put(Config.key_timestamp, String.valueOf(System.currentTimeMillis()));
//
//        //如果是加密的方式
////        String auth = params.remove(Config.key_auth);
////        if (TextUtils.isEmpty(auth)) {
////            auth = "false";
////        }
////        String token = params.remove(Config.key_token);
////        String secret = params.remove(Config.key_user_secret);
////        setSecret(secret);
////        if ("true".equalsIgnoreCase(auth)) {
////            //参数需要加密
////            params.put(Config.key_timestamp, String.valueOf(System.currentTimeMillis()));
////            Log.d("加密部分的参数：" + new Gson().toJson(params));
////            Log.d("加密秘钥：" + secret);
////            String data = AesUtil.encrypt(new Gson().toJson(params), secret);
////            params.clear();
////            params.put(Config.key_data, data);
////            params.put(Config.key_ev, Config.encrypt_way);
////            params.put(Config.key_kv, (Config.default_secret_key.equals(secret) ? "uc" : "u") + "_2_1");
////        }
////        if (!TextUtils.isEmpty(token)) {
////            params.put(Config.key_token, token);
////        }
////        params.put(Config.key_auth, auth);
//
//        //更新url
//        StringBuilder sbURL;
//        int idx = url.indexOf("?");
//        if (idx > 0) {
//            sbURL = new StringBuilder(url.substring(0, idx + 1));
//        } else {
//            sbURL = new StringBuilder(url).append("?");
//        }
//        //更新参数
//        StringBuilder sbParam = new StringBuilder();
//        for (Map.Entry<String, String> entry : params.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            if (value == null) {
//                value = "";
//            }
//            sbParam.append(urlEncode(key)).append("=").append(urlEncode(value)).append("&");
//        }
//        //加入sn验证参数
//        //去掉末尾&
//        String verifyString = sbParam.substring(0, sbParam.length() - 1);
//        sbParam.append(urlEncode(Config.key_sn)).append("=").append(Md5Util.encrypt(verifyString, Config.salt));
//
//        if (getMethod() == Method.GET) {
//            //更新完成
//            this.url = sbURL.append(sbParam).toString();
//        } else {
//            this.params = params;
//            this.params.put(urlEncode(Config.key_sn), Md5Util.encrypt(verifyString, Config.salt));
//            //清空一下对应的cache
//            RequestManager.getRequestQueue().getCache().remove(cache_key);
//        }
//    }
//
//    private String urlEncode(String value) {
//        try {
//            return URLEncoder.encode(value, RequestManager.encoding);
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        return "";
//    }
//
}
