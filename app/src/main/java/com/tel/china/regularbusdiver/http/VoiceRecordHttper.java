package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import java.util.Map;

/**
 * Created by lei on 10/8/15.
 */
public class VoiceRecordHttper {
    public static void backgroundCreateVoice(String voice, String script, QxfResponseListener listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_voice, voice);
        params.put(Config.key_script, script);
        RequestManager.backgroundRequest(Request.Method.POST, URLConfig.http_voice_create, params, listener);
    }

    public static void backgroundVoiceCheck(String voice, QxfResponseListener listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_voice, voice);
        RequestManager.backgroundRequest(Request.Method.POST, URLConfig.http_voice_check, params, listener);
    }

    public static void backgroundVoiceTest(String voice, QxfResponseListener listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_voice, voice);
        RequestManager.backgroundRequest(Request.Method.POST, URLConfig.http_voice_test, params, listener);
    }
}
