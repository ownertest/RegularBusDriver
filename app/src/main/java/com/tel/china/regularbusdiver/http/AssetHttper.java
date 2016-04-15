package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;
import com.creditease.zhiwang.bean.AssetItemRecord;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lei on 15-1-8.
 */
public class AssetHttper {

    public static void backgroundOrderHistory(int pageNo, int pageSize, long assetId, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_page_no, pageNo + "");
        params.put(Config.key_page_size, pageSize + "");
        params.put(Config.key_asset_id, String.valueOf(assetId));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_get_order_history, params, listener);
    }

    public static void backgroundGetAssetByChannel(String productChannel, QxfResponseListener<JSONObject> listener) {
        backgroundGetAssetByChannel(0, productChannel, listener);
    }

    public static void backgroundGetAssetByChannel(long assetId, String productChannel, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_product_channel, productChannel);
        params.put(Config.key_asset_id, String.valueOf(assetId));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_asset_channel_stat, params, listener);
    }

    public static void backgroundAssetPayback(long assetId, String pasword, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        params.put(Config.key_trade_password, pasword);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_asset_payback, params, listener);
    }

    public static void backgroundAssetDetail(String assetId, QxfResponseListener<JSONObject> listener) {
        backgroundAssetDetail(assetId, AssetItemRecord.NORMAL_TYPE, listener);
    }

    public static void backgroundAssetDetail(String assetId, String targetType, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        params.put(Config.key_target_type, targetType);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_GET_ASSET, params, listener);
    }

    public static void backgroundFundRedeemValid(long assetId, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_REDEEM_VALID, params, listener);
    }

    public static void backgroundFundRedeem(long assetId, String amount, String tradePassword, QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        params.put(Config.key_asset_id, String.valueOf(assetId));
        params.put(Config.key_amount, String.valueOf(amount));
        params.put(Config.key_trade_password, tradePassword);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_REDEEM, params, listener);
    }

    public static void backgroundGetSimpleAssets(QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_asset_stat_simple, params, listener);
    }

    public static void backgroundGetSimpleAssetsAfterClearCache(QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        RequestManager.clearCache(URLConfig.http_asset_stat_simple, params);
        backgroundGetSimpleAssets(listener);
    }

    public static void backgroundGetPaybackAssetList(QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_asset_payback_list, params, listener);
    }

    public static void backgroundGetAssetPoint(QxfResponseListener<JSONObject> listener) {
        Map<String, String> params = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_POINT_ASSET, params, listener);
    }
}
