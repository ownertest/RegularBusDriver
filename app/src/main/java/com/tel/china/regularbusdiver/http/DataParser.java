package com.tel.china.regularbusdiver.http;

import org.json.JSONObject;

/**
 * Created by niutf on 16/1/25.
 */
public interface DataParser<T> {
    T parse(JSONObject data);
}
