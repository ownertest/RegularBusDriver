package com.tel.china.regularbusdiver.http;

import com.android.volley.VolleyError;

/**
 * Created by wuyq on 14-8-6.
 */
public interface QxfResponseListener<T> {
    void onResponse(T response);

    void onErrorResponse(VolleyError error);
}
