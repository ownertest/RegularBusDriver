package com.tel.china.regularbusdiver.http.api;

import com.creditease.shangtongdai.BuildConfig;
import com.creditease.shangtongdai.StdApplication;

/**
 * Created by niutf on 16/1/25.
 */
public class ApiUrl {

    private static final String BASE_URL = (BuildConfig.DEBUG ? "http://" : "https://") + StdApplication.host + "/api";

    public static final String URL_PLATFORMS = BASE_URL + "/platforms";
}
