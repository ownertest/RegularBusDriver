package com.tel.china.regularbusdiver;


import android.text.format.DateUtils;

import com.tel.china.regularbusdiver.system.StdApplication;

import java.util.HashMap;
import java.util.Map;

public class URLConfig {
    public static final String prefix = "http://" + StdApplication.host;
    //验证手机号是否注册，并发送短信验证码
    public static final String http_user_exist_sms = prefix + "/user/exist/sms";
    //获取用户绑定过的银行卡
    public static final String http_user_bankcards = prefix + "/user/bank/cards";
    //身份验证
    public static final String http_user_realidentify = prefix + "/user/realidentify";
    //实名认证
    public static final String http_user_realname = prefix + "/user/realname";
    //设置登录密码
    public static final String http_user_login = prefix + "/login";
    //登录
    public static final String http_user_register = prefix + "/user/register";
    //发送短信验证码
    public static final String http_user_sms = prefix + "/user/sms";

    //用户状态
    public static final String http_user = prefix + "/user";
    //退出
    public static final String http_user_logout = prefix + "/user/logout";

    //首页推荐和banner
    public static final String http_recommend = prefix + "/recommend";
    //产品列表
    public static final String http_product_list = prefix + "/product/list";
    //产品介绍
    public static final String http_product = prefix + "/product";
    //购买检查
    public static final String http_valid_order = prefix + "/valid/order";

    //计算预期收益
    public static final String http_estimate_interest = prefix + "/product/interest/estimate";
    //创建支付订单并发短信
    public static final String http_pay_sms = prefix + "/pay/sms";
    //重新发送支付短信
    public static final String http_pay_sms_resend = prefix + "/pay/sms/resend";
    //重新发送支付短信
    public static final String http_pay_do = prefix + "/pay/do";

    //修改登录密码
    public static final String http_login_pwdchange = prefix + "/user/login/pwdchange";

    //修改交易密码，认证
    public static final String http_trade_pwdchange_auth = prefix + "/user/trade/pwdchange/auth";
    //修改交易密码，设置
    public static final String http_trade_pwdchange = prefix + "/user/trade/pwdchange";

    //忘记登录密码
    public static final String http_forget_login_password = prefix + "/user/login/forget/password";

    //忘记交易密码，认证
    public static final String http_forget_trade_password_auth = prefix + "/user/trade/pwdforget/auth";
    //设置交易密码，设置
    public static final String http_forget_trade_password_set = prefix + "/user/trade/pwdforget/set";

    //关于我们urls
    public static final String http_about_us = prefix + "/about/us";
    //增加银行卡
    public static final String http_add_bankcard = prefix + "/user/bank/cards/add";

    //设置交易密码第一步，验证密码
    public static final String http_trade_password_auth = prefix + "/user/trade/pwdset/auth";
    //设置交易密码第二步，设置交易密码
    public static final String http_trade_password_set = prefix + "/user/trade/pwdset";

    //push
    public static final String http_register_push = prefix + "/user/push/register";

    //发送反馈意见，吐槽一下
    public static final String http_feedback = prefix + "/feedback/submit";
    public static final String http_feedback_list = prefix + "/feedback/list";

    //分享完成后通知后台
    public static final String HTTP_SHARE_NOTIFY = prefix + "/share/notify";

    /**
     * 获取App图片的配置，目前包括启动图片和首页背景图
     */
    public static final String http_image_config = prefix + "/image/config";

    /**
     * 获取分享文案的配置，支持A/B test
     */
    public static final String http_share_config = prefix + "/share/config";
    /**
     * 验证登录密码
     */
    public static final String http_pwd_check = prefix + "/user/login/pwdcheck";

    /**
     * 获取产品支持的银行列表
     */
    public static final String http_get_products_cards = prefix + "/product/cards";

    public static final String http_get_order_history = prefix + "/order/history";

    public static final String share_lp_prefix = "https://zhiwang.yixin.com/share_lp?target=";

    /**
     * 请求系统紧急通知
     */
    public static String http_sys_note = prefix + "/sys/note";

    //获取消息列表
    public static String HTTP_MESSAGE_LIST = prefix + "/user/notifications";
    //根据资产id获取资产详情
    public static String HTTP_GET_ASSET = prefix + "/single/asset/detail";
    //基金开户
    public static String HTTP_FUND_CREATE_ACCOUNT = prefix + "/fund/auth/code/send";
    //基金校验短信
    public static String HTTP_FUND_VERIFY_SMS_CODE = prefix + "/fund/auth/code/verify";
    //支行省市信息列表
    public static String HTTP_FUND_BRANCH_BANK_CITIES_ALL = prefix + "/fund/bank/branch/city/list";
    //支行列表
    public static String HTTP_FUND_BRANCH_BANK_LIST = prefix + "/fund/bank/branch/list";

    /**
     * 续投相关
     */
    public static final String http_create_reinvest = prefix + "/cont/invest/create";
    public static final String http_update_reinvest = prefix + "/cont/invest/update";
    public static final String http_cancel_reinvest = prefix + "/cont/invest/cancel";
    public static final String http_reinvest_product_list = prefix + "/product/list/for/cont/invest";
    public static final String http_asset_channel_stat = prefix + "/asset/channel/stat";
    public static final String http_asset_payback = prefix + "/asset/payback";
    public static final String http_alert = prefix + "/alert";

    /**
     * fund
     */
    public static final String HTTP_FUND_REDEEM_VALID = prefix + "/fund/redeem/valid";
    public static final String HTTP_FUND_REDEEM = prefix + "/fund/redeem";

    /**
     * voice lock
     */
    public static final String http_voice_create = prefix + "/voice/create";
    public static final String http_voice_check = prefix + "/voice/check";
    public static final String http_voice_test = prefix + "/voice/test";
    public static final String http_asset_stat_simple = prefix + "/asset/stat/simple";
    public static final String http_asset_payback_list = prefix + "/asset/payback/list";
    public static final String http_liquidate_start = prefix + "/liquidate/start";

    public static String http_fang_dai_bao_due_date = prefix + "/product/fangdaibao/duedate";
    public static String http_net_worth_history_data = prefix + "/fund/history/data";
    //缓存配置
    public static Map<String, Long> cacheTime;
    public static final String http_liquidate_confirm = prefix + "/liquidate/confirm";
    public static final String http_cancel_liquidate = prefix + "/liquidate/cancel";

    public static final String HTTP_ORDER_CANCEL = prefix + "/order/cancel";

    /**
     * 变现
     */
    public static final String HTTP_LIQUIDATE_LIST = prefix + "/liquidate/product";
    public static final String HTTP_POINT_ASSET = prefix + "/point/asset";

    //More warnings
    public static final String HTTP_MORE_REMINDERS = prefix + "/more/intro";

    static {
        cacheTime = new HashMap<>();
        cacheTime.put(http_about_us, DateUtils.DAY_IN_MILLIS);
        cacheTime.put(http_product_list, DateUtils.MINUTE_IN_MILLIS * 15);
        cacheTime.put(http_share_config, DateUtils.MINUTE_IN_MILLIS * 30);
        cacheTime.put(http_net_worth_history_data, DateUtils.MINUTE_IN_MILLIS * 2);
    }

    public static long getCacheTime(String url) {
        for (String key : cacheTime.keySet()) {
            if (url.startsWith(key)) {
                return cacheTime.get(key);
            }
        }
        return 0;
    }
}
