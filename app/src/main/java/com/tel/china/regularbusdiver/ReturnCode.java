package com.tel.china.regularbusdiver;

/**
 * Created by longyi on 14/12/29.
 */
public class ReturnCode {
    public static final int OK = 0;
    public static final int ERROR_INVALID_SESSION = 3;
    public static final int ERROR_TRADE_PWDCHANGE_OLDPWD_MISMATCH = 103;
    public static final int ERROR_TRADE_PWDCHANGE_OLDPWD_MISMATCH_NORETRY = 107;
    public static final int ERROR_REALNAME_VERIFY = 300;
    public static final int ERROR_REALNAME_BIND_BEFORE = 301;
    public static final int ERROR_REALNAME_ALREADY = 302;
    public static final int ERROR_INSUFFICIENT_PRODUCT = 400;
    public static final int ERROR_PRODUCT_NOT_ON_SELL = 407;
    public static final int ERROR_NEW_USER_ONLY_PRODUCT = 408;
    public static final int ERROR_DOESNOT_SUPPORT_CREDIT_CARD = 411;
    public static final int ERROR_BANK_PHONE_MISMATCH = 412;
    public static final int ERROR_BANK_DONOT_SUPPORT = 413;
    public static final int ERROR_SEND_SMS_FAIL = 415;
    public static final int ERROR_MONEY_OVER_PER_DAY = 416;//超出银行卡每日限额
    public static final int ERROR_MAX_TRIES_OVER_PER_DAY = 417;//超出银行卡每日支付次数
    public static final int ERROR_VERIFY_FAIL_NEED_LOCATION = 418;
    public static final int ERROR_BANK_CARD_NUM_NOT_MATCH = 421;
    public static final int ERROR_PAY_VERIFY_CODE_WRONG = 500;
    public static final int ERROR_TRADE_PASSWORD_ERROR = 506;
    public static final int ERROR_PAY_WAIT = 510;
    public static final int ERROR_MONEY_NOT_ENOUGH = 512;
    public static final int ERROR_BANK_CARD_STATE_UNUSUAL = 513;
    public static final int ERROR_CAN_NOT_REDEEM_FUND = 1600;
    public static final int ERROR_UNKNOWN = 31415;
    public static final int ERROR_ALREADY_HAS_FUND_ACCOUNT = 1601;//该卡在购买过程中通过微信等购买方式完成开户
    public static final int ERROR_FUND_NOT_SUPPORT_CARD = 1604;//虽然该卡在p2p鉴权成功，但在基金鉴权失败
    public static final int ERROR_ANOTHER_CARD_HAS_FUND_ACCOUNT = 1606;//该用户已有其他卡在该基金开户了
    public static final int ERROR_NEED_BRANCH_BANK = 1611;//需要填写支行信息
    public static final int ERROR_LIQUIDATE_LEFT_TOO_SMALL = 1902;//购买变现产品，不能使剩余金额太少
    public static final int ERROR_LIQUIDATE_INSUFFICIENT = 1903;//变现产品余额不足


//    public static final int ERROR_MISSING_PARAM = 1;
//    public static final int ERROR_INVALID_SN = 2;
//    public static final int ERROR_INVALID_PARAM = 4;
//    public static final int ERROR_SMS_VERIFY_FAIL = 100;
//    public static final int ERROR_EXIST_PHONE = 101;
//    public static final int ERROR_BANK_CARD_INVALID = 403;
//    public static final int ERROR_ORDER_AMOUNT_BELLOW_MIN = 404;
//    public static final int ERROR_ORDER_AMOUNT_EXCEED_MAX = 405;
//    public static final int ERROR_CANNOT_RECOGNIZE_WHICH_BANK = 409;
//    public static final int ERROR_PAY_EXCEED_BANK_AMOUNT_LIMIT_OTHER = 424;
//    public static final int ERROR_PAY_EXPIRED = 505;
//    public static final int ERROR_PAYMENT_USER_MISMATCH = 507;
//    public static final int ERROR_PAYMENT_ORDER_MISMATCH = 508;
//    public static final int ERROR_PAYMENT_AMOUNT_MISMATCH = 509;
//    public static final int ERROR_REALIDENTIFY_INVALID = 600;
//    public static final int ERROR_WRONG_LONGIN_PASSWORD = 700;
//    public static final int ERROR_USER_UNKNOWN = 800;
//    public static final int ERROR_SMS_SEND_FAIL = 1000;
//    public static final int ERROR_FUND_OTHER_SAME_BANK_ACCO_EXIST = 1607;//该用户已经在普泽开通同一个银行的卡
//    public static final int ERROR_ZANQIANBAO_NEW_PLAN_HINT = 5000;//攒钱宝二次购买时开启新计划提醒
}
