package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.creditease.zhiwang.Config;
import com.creditease.zhiwang.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lei on 7/3/15.
 */
public class ReinvestHttper {
    public static void backgroundReinvestProductList(long asset_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_asset_id, String.valueOf(asset_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_reinvest_product_list, param, listener);
    }

    public static void backgroundCreateReinvest(long asset_id, String trade_password, long amount, long product_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_asset_id, String.valueOf(asset_id));
        param.put(Config.key_trade_password, trade_password);
        param.put(Config.key_amount, String.valueOf(amount));
        param.put(Config.key_product_id, String.valueOf(product_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_create_reinvest, param, listener);
    }

    public static void backgroundCancelReinvest(String trade_password, long reinvest_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_trade_password, String.valueOf(trade_password));
        param.put(Config.key_reinvest_id, String.valueOf(reinvest_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_cancel_reinvest, param, listener);
    }

    public static void backgroundModifyReinvest(long asset_id, String trade_password, long amount, long product_id, long reinvest_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_asset_id, String.valueOf(asset_id));
        param.put(Config.key_amount, String.valueOf(amount));
        param.put(Config.key_trade_password, trade_password);
        param.put(Config.key_product_id, String.valueOf(product_id));
        param.put(Config.key_reinvest_id, String.valueOf(reinvest_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_update_reinvest, param, listener);
    }
}
