package com.tel.china.regularbusdiver.http;

import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.tel.china.regularbusdiver.util.Log;

/**
 * Created by wuyq on 2014-09-09 下午2:05.
 */
public class DefaultImageListener implements ImageLoader.ImageListener {
    @Override
    public void onResponse(ImageLoader.ImageContainer response, boolean isImmediate) {
        Log.d("downloaded image: " + response.getRequestUrl());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
