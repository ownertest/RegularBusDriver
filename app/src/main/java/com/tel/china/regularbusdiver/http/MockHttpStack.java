package com.tel.china.regularbusdiver.http;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.HttpStack;
import com.creditease.shangtongdai.bean.ECPlatformType;
import com.creditease.shangtongdai.http.api.ApiUrl;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Created by niutf on 16/1/25.
 */
public class MockHttpStack implements HttpStack {

    private final int mDelay;

    public MockHttpStack(int delayInMs) {
        mDelay = delayInMs;
    }

    @Override
    public HttpResponse performRequest(Request<?> request, Map<String, String> stringStringMap)
            throws IOException, AuthFailureError {
        try {
            Thread.sleep(mDelay);
        } catch (InterruptedException ignored) {
        }
        HttpResponse response
                = new BasicHttpResponse(new BasicStatusLine(HttpVersion.HTTP_1_1, 200, "OK"));
        List<Header> headers = defaultHeaders();
        response.setHeaders(headers.toArray(new Header[headers.size()]));
        response.setLocale(Locale.CHINA);
        response.setEntity(createEntity(request));
        return response;
    }

    private List<Header> defaultHeaders() {
        DateFormat dateFormat = new SimpleDateFormat("EEE, dd mmm yyyy HH:mm:ss zzz");
        List<Header> list = new ArrayList<>();
        list.add(new BasicHeader("Date", dateFormat.format(new Date())));
        list.add(new BasicHeader("Server", "Apache/1.3.42 (Unix) mod_ssl/2.8.31 OpenSSL/0.9.8e"));
        return list;
    }

    private HttpEntity createEntity(Request request) throws UnsupportedEncodingException {
        String response = createMockResponse(request);
        return new StringEntity(response);
    }

    private String createMockResponse(Request request) {
        String reqUrl = request.getUrl();
        try {
            JSONObject data = new JSONObject();
            String[] platformTypes = new String[]{ECPlatformType.TaoBao.toString(), ECPlatformType.TMall.toString(), ECPlatformType.JD.toString()};
            if (reqUrl.equals(ApiUrl.URL_PLATFORMS)) {
                JSONArray platforms = new JSONArray();
                for (int i = 0; i < 3; i++) {
                    JSONObject p = new JSONObject();
                    p.put("name", "name" + i);
                    p.put("platformType", platformTypes[i]);
                    platforms.put(p);
                }
                data.put("platforms", platforms);
            }
            JSONObject obj = new JSONObject();
            obj.put("code", 0);
            obj.put("message", "ok");
            obj.put("data", data);
            return obj.toString();
        } catch (JSONException e) {
            e.printStackTrace();
            return "invalid result";
        }
    }
}
