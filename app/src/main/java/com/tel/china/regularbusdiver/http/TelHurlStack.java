package com.tel.china.regularbusdiver.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HurlStack;
import com.tel.china.regularbusdiver.util.Log;

import org.apache.http.HttpResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class TelHurlStack extends HurlStack {

    public TelHurlStack() {
        super();
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> additionalHeaders) throws IOException, AuthFailureError {
        Log.d("TelHurlStack#performRequest");
        Map<String, String> map = new HashMap<>(RequestManager.getExtraHeader());
        if (additionalHeaders != null && additionalHeaders.size() > 0) {
            map.putAll(additionalHeaders);
        }
        Log.d("LOGString", "perform end");
        return super.performRequest(request, map);
    }

}