package com.tel.china.regularbusdiver.http;

import com.android.volley.VolleyError;

/**
 * Created by niutf on 16/1/25.
 */
public class StdApiError extends VolleyError {

    private int code;

    public StdApiError(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
