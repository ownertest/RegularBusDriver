package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 14/12/25.
 */
public class UserHttper {
    public static void backgroundUserExistSms(String phone, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_exist_sms, param, listener);
    }

    public static void backgroundUserRegister(String phone, String password, String verify_code, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        param.put(Config.key_password, password);
        param.put(Config.key_verify_code, verify_code);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_register, param, listener);
    }

    public static void backgroundUserSms(String phone, int action, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        param.put(Config.key_action, String.valueOf(action));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_sms, param, listener);
    }

    public static void backgroundRealName(String name, String id_card_number, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_name, name);
        param.put(Config.key_id_card_number, id_card_number);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_realname, param, listener);
    }

    public static void backgroundUserLogin(String phone, String password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        param.put(Config.key_password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_login, param, listener);
    }

    public static void backgroundUserInfo(QxfResponseListener<JSONObject> listener, String filter) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(filter, Request.Method.GET, URLConfig.http_user, param, listener);
    }

    /**
     * 获取用户与产品相关的uba list，如果传入producID为0，则获取该用户所有uba
     *
     * @param productID 要购买的产皮id
     * @param listener
     */
    public static void backgroundBankCards(long productID, long assetID, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, productID + "");
        param.put(Config.key_asset_id, assetID + "");
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_bankcards, param, listener);
    }

    public static void backgroundLogout(QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_logout, param, listener);
    }

    public static void backgroundModifyLoginPassword(String old_password, String new_password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        param.put(Config.key_new_password, new_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_login_pwdchange, param, listener);
    }

    public static void backgroundModifyTradePasswordAuth(String old_password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_pwdchange_auth, param, listener);
    }

    public static void backgroundModifyTradePassword(String old_password, String new_password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        param.put(Config.key_new_password, new_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_pwdchange, param, listener);
    }

    public static void backgroundForgetLoginPassword(String password, String verify_code, String id_card_number, String phone, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_password, password);
        param.put(Config.key_verify_code, verify_code);
        param.put(Config.key_id_card_number, id_card_number);
        param.put(Config.key_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_forget_login_password, param, listener);
    }

    public static void backgroundForgetTradePasswordAuth(String id_card_number, String bank_card_number, String verify_code, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_id_card_number, id_card_number);
        param.put(Config.key_bank_card_number, bank_card_number);
        param.put(Config.key_verify_code, verify_code);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_forget_trade_password_auth, param, listener);
    }

    public static void backgroundForgeTradePasswordSet(String trade_password, String realname_session_id, String id_card_number,
                                                       QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_trade_password, trade_password);
        param.put(Config.key_realname_session_id, realname_session_id);
        param.put(Config.key_id_card_number, id_card_number);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_forget_trade_password_set, param, listener);
    }

    public static void backgroundSetTradePasswordAuth(String password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_password_auth, param, listener);
    }

    public static void backgroundSetTradePasswordSet(String trade_password, String realname_session_id, String phone, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_trade_password, trade_password);
        param.put(Config.key_realname_session_id, realname_session_id);
        param.put(Config.key_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_password_set, param, listener);
    }

    public static void backgroundPwdCheck(String user_id, String password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_user_id, user_id);
        param.put(Config.key_password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_pwd_check, param, listener);
    }
}