package com.tel.china.regularbusdiver.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import com.creditease.shangtongdai.util.Log;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuyq on 14-7-30.
 */
public class StdHurlStack extends HurlStack {

    public StdHurlStack() {
        super();
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        Log.d("StdHurlStack#performRequest");
        Map<String, String> map = new HashMap<>(RequestManager.getExtraHeader());
        if (additionalHeaders != null && additionalHeaders.size() > 0) {
            map.putAll(additionalHeaders);
        }
        return super.performRequest(request, map);
    }

}