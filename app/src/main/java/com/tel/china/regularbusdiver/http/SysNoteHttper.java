package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;

import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lei on 3/11/15.
 */
public class SysNoteHttper {
    public static void backgroundGetSysNote(QxfResponseListener<JSONObject> listener){
        Map<String,String> params = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_sys_note,params,listener);
    }
}