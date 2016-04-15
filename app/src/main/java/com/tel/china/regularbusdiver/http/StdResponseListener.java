package com.tel.china.regularbusdiver.http;

import com.android.volley.Response;

/**
 * Created by niutf on 16/1/25.
 */
public interface StdResponseListener<T> extends Response.ErrorListener, Response.Listener<T> {

}
