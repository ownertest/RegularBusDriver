package com.tel.china.regularbusdiver.http;

import com.android.volley.VolleyError;


public interface QxfResponseListener<T> {
    void onResponse(T response);

    void onErrorResponse(VolleyError error);
}
