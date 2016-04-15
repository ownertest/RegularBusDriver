package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lei on 12/4/15.
 * you can you up...
 */
public class LiquidateHttper {
    public static void backgroundStartLiquidate(long assetId, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_liquidate_start, params, listener);
    }

    public static void backgroundConfirmLiquidate(long assetId, String liquidateRate, String tradePassword, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        params.put(Config.key_liquidate_rate, liquidateRate);
        params.put(Config.key_trade_password, tradePassword);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_liquidate_confirm, params, listener);
    }

    public static void backgroundCancelLiquidate(long assetId, String password, CommonQxfResponseListener listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        params.put(Config.key_trade_password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_cancel_liquidate, params, listener);
    }
}
