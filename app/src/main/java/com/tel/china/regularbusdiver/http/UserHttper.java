package com.tel.china.regularbusdiver.http;

import com.android.volley.Request;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.URLConfig;

import org.json.JSONObject;

import java.util.Map;


public class UserHttper {
    public static void backgroundUserExistSms(String phone, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_exist_sms, param, listener);
    }



    public static void backgroundUserSms(String phone, int action, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_phone, phone);
        param.put(Config.key_action, String.valueOf(action));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_sms, param, listener);
    }

    public static void backgroundRealName(String name, String id_card_number, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_name, name);
        param.put(Config.key_id_card_number, id_card_number);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_realname, param, listener);
    }

    public static void backgroundUserLogin(String name, String password, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.userName, name);
        param.put(Config.password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_login, param, listener);
    }

    public static void backgroundRequestLineData(TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.POST, URLConfig.http_line_info, param, listener);
    }

    public static void backgroundRequestarInfoDetail(String lineNum,TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.line_num, lineNum);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_car_info_detail, param, listener);
    }

    public static void backgroundRequestarOrder(String tag, String operation, String userName, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        //operation == 1 order operation == 0 cancel order
        param.put(Config.http_tag, tag);
        param.put(Config.http_operation, operation);
        param.put(Config.userName, userName);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_order_line, param, listener);
    }
    public static void backgroundRequestOrderQuery(String lineId, String userName, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.line_id, lineId);
        param.put(Config.userName, userName);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_car_info_detail, param, listener);
    }

    public static void backgroundUserInfo(TelResponseListener<JSONObject> listener, String filter) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(filter, Request.Method.GET, URLConfig.http_user, param, listener);
    }


    public static void backgroundLogout(TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_user_logout, param, listener);
    }

    public static void backgroundModifyLoginPassword(String old_password, String new_password, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        param.put(Config.key_new_password, new_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_login_pwdchange, param, listener);
    }

    public static void backgroundModifyTradePasswordAuth(String old_password, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_pwdchange_auth, param, listener);
    }

    public static void backgroundModifyTradePassword(String old_password, String new_password, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_old_password, old_password);
        param.put(Config.key_new_password, new_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_trade_pwdchange, param, listener);
    }

    public static void backgroundForgetLoginPassword(String password, String verify_code, String id_card_number, String phone, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_password, password);
        param.put(Config.key_verify_code, verify_code);
        param.put(Config.key_id_card_number, id_card_number);
        param.put(Config.key_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_forget_login_password, param, listener);
    }





    public static void backgroundPwdCheck(String user_id, String password, TelResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_user_id, user_id);
        param.put(Config.key_password, password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_pwd_check, param, listener);
    }
}