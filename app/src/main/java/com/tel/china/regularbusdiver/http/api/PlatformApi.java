package com.tel.china.regularbusdiver.http.api;

import com.android.volley.Request;
import com.creditease.shangtongdai.bean.ECPlatform;
import com.creditease.shangtongdai.bean.ECPlatformType;
import com.creditease.shangtongdai.http.DataParser;
import com.creditease.shangtongdai.http.RequestManager;
import com.creditease.shangtongdai.http.StdRequest;
import com.creditease.shangtongdai.http.StdResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by niutf on 16/1/25.
 */
public class PlatformApi {

    public void getPlatforms(StdResponseListener<List<ECPlatform>> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        DataParser<List<ECPlatform>> parser = new DataParser<List<ECPlatform>>() {
            @Override
            public List<ECPlatform> parse(JSONObject data) {
                List<ECPlatform> list = new ArrayList<>();
                JSONArray platforms = data.optJSONArray("platforms");
                if (platforms != null) {
                    try {
                        for (int i = 0; i < platforms.length(); i++) {
                            JSONObject cur = platforms.getJSONObject(i);
                            ECPlatform p = new ECPlatform();
                            p.setName(cur.optString("name"));
                            p.setPlatformType(ECPlatformType.valueOf(cur.optString("platformType")));
                            list.add(p);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                return list;
            }
        };
        StdRequest<List<ECPlatform>> req = new StdRequest<>(Request.Method.POST, ApiUrl.URL_PLATFORMS, params, parser, listener);
        RequestManager.backgroundRequest(req);
    }
}
