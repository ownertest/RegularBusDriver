package com.tel.china.regularbusdiver.http;

import android.text.format.DateUtils;

import com.android.volley.Request;
import com.creditease.zhiwang.Config;
import com.creditease.zhiwang.URLConfig;
import com.creditease.zhiwang.bean.Product;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by longyi on 14/1/3.
 */
public class ProductHttper {

    public static void backgroundGetNetValueDataSet(String productId, QxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, productId);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_net_worth_history_data, param, listener);
    }

    public static void backgroundEstimateInterest(String amount, Product product, String selected_due_date, long coupon_id, int coupon_type, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_amount, amount);
        param.put(Config.key_product_id, String.valueOf(product.product_id));
        param.put(Config.key_selected_due_date, selected_due_date);
        param.put(Config.key_coupon_id, String.valueOf(coupon_id));
        param.put(Config.key_coupon_type, String.valueOf(coupon_type));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_estimate_interest, param, listener);
    }

    public static void backgroundFangDaiBaoDueDate(String productId, String dueDate, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, productId);
        param.put(Config.key_selected_due_date, dueDate);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_fang_dai_bao_due_date, param, listener);
    }

    public static void backgroundPaySms(Product product, String amount, String bank_card_number, long bank_id,
                                        long user_bank_account_id, String trade_password, String reserve_phone,
                                        String province, String city, long order_deduct_id,
                                        long coupon_id, int coupon_type, String selected_due_date,
                                        QxfResponseListener<JSONObject> listener, int planID, long assetID) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, String.valueOf(product.product_id));
        param.put(Config.key_amount, amount);
        param.put(Config.key_bank_card_number, bank_card_number);
        param.put(Config.key_bank_id, String.valueOf(bank_id));
        param.put(Config.key_user_bank_account_id, String.valueOf(user_bank_account_id));
        param.put(Config.key_trade_password, trade_password);
        param.put(Config.key_reserve_phone, reserve_phone);
        param.put(Config.key_province, province);
        param.put(Config.key_city, city);
        param.put(Config.key_order_deduct_id, String.valueOf(order_deduct_id));
        param.put(Config.key_selected_due_date, selected_due_date);
        param.put(Config.key_coupon_id, String.valueOf(coupon_id));
        param.put(Config.key_coupon_type, String.valueOf(coupon_type));
        param.put(Config.key_asset_id, assetID + "");
        param.put(Config.key_plan_id, planID + "");

        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_pay_sms, param, listener, (int) (DateUtils.SECOND_IN_MILLIS) * 60);
    }

    public static void backgroundPaySmsResend(long order_id, String amount, String bank_card_number, long bank_id,
                                              long user_bank_account_id, String trade_password, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_order_id, String.valueOf(order_id));
        param.put(Config.key_amount, amount);
        param.put(Config.key_bank_card_number, bank_card_number);
        param.put(Config.key_bank_id, String.valueOf(bank_id));
        param.put(Config.key_user_bank_account_id, String.valueOf(user_bank_account_id));
        param.put(Config.key_trade_password, trade_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_pay_sms_resend, param, listener, (int) (DateUtils.SECOND_IN_MILLIS) * 60);
    }

    public static void backgroundPayDo(long order_id, String pay_code, String verify_code, String trade_password, String amount,
                                       QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_order_id, String.valueOf(order_id));
        param.put(Config.key_amount, amount);
        param.put(Config.key_pay_code, pay_code);
        param.put(Config.key_verify_code, verify_code);
        param.put(Config.key_trade_password, trade_password);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_pay_do, param, listener, (int) (DateUtils.SECOND_IN_MILLIS) * 60);
    }

    public static void backgroundLoadProductList(QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_product_list, param, listener);
    }

    public static void backgroundClearProductListCache() {
        Map<String, String> param = RequestManager.getCommonParams();
        RequestManager.clearCache(URLConfig.http_product_list, param);
    }

    public static void backgroundGetProduct(long product_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, String.valueOf(product_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_product, param, listener);
    }

    /**
     * 如果不需要asset_id域，传0
     *
     * @param product_id 产品id
     * @param asset_id   产品对应asset_id，攒钱宝使用
     * @param listener   listener
     */
    public static void backgroundGetValidOrder(long product_id, long asset_id, QxfResponseListener<JSONObject> listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, String.valueOf(product_id));
        param.put(Config.key_asset_id, asset_id + "");
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.http_valid_order, param, listener);
    }

    /**
     * 获取短信验证码,用于基金开通时验证
     *
     * @param cardNum           卡号
     * @param userBankAccountID UBA ID
     * @param bankID            银行ID
     * @param province          省份
     * @param city              城市
     * @param branchBankID      开户支行
     * @param listener          事件回调
     */
    public static void backgroundCreateFundAccount(String productID, String amount,
                                                   String cardNum, String userBankAccountID,
                                                   String bankID, String province,
                                                   String city, String branchBankID,
                                                   String phone, BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_product_id, productID);
        param.put(Config.key_amount, amount);
        param.put(Config.key_user_bank_account_id, userBankAccountID);
        param.put(Config.key_bank_card_number, cardNum);
        param.put(Config.key_bank_id, bankID);
        param.put(Config.key_fund_branch_bank_id, branchBankID);
        param.put(Config.key_province, province);
        param.put(Config.key_city, city);
        param.put(Config.key_reserve_phone, phone);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_CREATE_ACCOUNT, param, listener);
    }

    /**
     * 校验基金开户短信验证
     *
     * @param authCode      用户输入的短信验证码
     * @param requestSerial 序列号，由server给出
     * @param listener      事件回调
     */
    public static void backgroundCheckFundSmsCode(String authCode, String requestSerial,
                                                  BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_verify_code, authCode);
        param.put(Config.key_pay_code, requestSerial);
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_VERIFY_SMS_CODE, param, listener);
    }

    /**
     * 获取所有省市信息
     *
     * @param listener 事件回调
     * @param bankID   银行ID
     */
    public static void backgroundGetBranchBankCities(long bankID, BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_bank_id, bankID + "");
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_BRANCH_BANK_CITIES_ALL, param, listener);
    }

    /**
     * 获取该省市的所有支行
     *
     * @param province 省份
     * @param city     城市
     * @param bankID   银行ID
     * @param listener 事件回调
     */
    public static void backgroundGetBranchBankList(String province, String city, String bankID, BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_province, province);
        param.put(Config.key_city, city);
        param.put(Config.key_bank_id, bankID);

        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_FUND_BRANCH_BANK_LIST, param, listener);
    }

    /**
     * 当用户已获取短信验证码后，却没有继续交易，将短信验证码输入框关闭了的时候，调用此接口，释放产品额度
     *
     * @param order_id 订单id
     * @param listener 事件回调
     */
    public static void backgroundCancelOrder(long order_id, BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_order_id, String.valueOf(order_id));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_ORDER_CANCEL, param, listener);
    }

    public static void getLiquidateList(int pageNo, int pageSize, BaseQxfResponseListener listener) {
        Map<String, String> param = RequestManager.getCommonParams();
        param.put(Config.key_page_no, String.valueOf(pageNo));
        param.put(Config.key_page_size, String.valueOf(pageSize));
        RequestManager.backgroundRequest(Request.Method.GET, URLConfig.HTTP_LIQUIDATE_LIST, param, listener);
    }
}