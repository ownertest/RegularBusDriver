package com.tel.china.regularbusdiver;


import com.tel.china.regularbusdiver.system.StdApplication;

import java.util.Map;

public class URLConfig {
    public static final String prefix = "http://" + StdApplication.host;
    //验证手机号是否注册，并发送短信验证码
    public static final String http_user_exist_sms = prefix + "/user/exist/sms";

    //实名认证
    public static final String http_user_realname = prefix + "/user/realname";
    //登录
    public static final String http_user_login = prefix + "/login";
    //请求line线路信息
    public static final String http_line_info = prefix + "/lineInfo";
    public static final String http_user_register = prefix + "/user/register";
    //请求line线路信息
    public static final String http_car_info_detail = prefix + "/carInfoDetail";
    //order
    public static final String http_order_line = prefix + "/order";
    //发送短信验证码
    public static final String http_user_sms = prefix + "/user/sms";

    //用户状态
    public static final String http_user = prefix + "/user";
    //退出
    public static final String http_user_logout = prefix + "/user/logout";



    //修改登录密码
    public static final String http_login_pwdchange = prefix + "/user/login/pwdchange";

    //修改交易密码，认证
    public static final String http_trade_pwdchange_auth = prefix + "/user/trade/pwdchange/auth";
    //修改交易密码，设置
    public static final String http_trade_pwdchange = prefix + "/user/trade/pwdchange";

    //忘记登录密码
    public static final String http_forget_login_password = prefix + "/user/login/forget/password";




    public static final String http_pwd_check = prefix + "/user/login/pwdcheck";



    //获取消息列表
    public static String HTTP_MESSAGE_LIST = prefix + "/user/notifications";


    public static Map<String, Long> cacheTime;

    public static long getCacheTime(String url) {
        for (String key : cacheTime.keySet()) {
            if (url.startsWith(key)) {
                return cacheTime.get(key);
            }
        }
        return 0;
    }
}
