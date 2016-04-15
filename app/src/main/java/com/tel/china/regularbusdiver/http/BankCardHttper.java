package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 15/1/13.
 */
public class BankCardHttper {
    public static void backgroundAddBankCard(String productID, String bank_card_number, long bank_id, String reserve_phone, String province, String city, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, productID);
        param.put(Config.key_bank_card_number, bank_card_number);
        param.put(Config.key_reserve_phone, reserve_phone);
        param.put(Config.key_bank_id, String.valueOf(bank_id));
        param.put(Config.key_province, province);
        param.put(Config.key_city, city);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_add_bankcard, param, listener);
    }

    public static void backgroundGetProductCards(long product_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, String.valueOf(product_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_get_products_cards, param, listener);
    }
}
