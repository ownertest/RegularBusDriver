package com.tel.china.regularbusdiver;

import com.creditease.zhiwang.activity.BaseActivity;
import com.creditease.zhiwang.activity.InputPhoneActivity;
import com.creditease.zhiwang.activity.TabContainerActivity;
import com.creditease.zhiwang.activity.WebActivity;
import com.creditease.zhiwang.activity.asset.ZQBAssetDetailActivity;
import com.creditease.zhiwang.activity.product.ProductDetailActivity;

import java.util.HashMap;

/**
 * Created by longyi on 14/12/23.
 */
public class Config {
    public static final String TAG = "qxf";

    public static final String salt = "qxfsn3.1415926535";
    public static final String service_phone = "4008-185-618";

    public static final String wx_app_id = "wx70df8d25def6cf2b";
    public static final String weibo_app_id = "3391388316";
    public static final String qq_app_id = "1104158665";

    public static final String ga_property_id_prod = "UA-59044824-1";
    public static final String ga_property_id_dev = "UA-59044824-2";

    public static final String app_id_prod = "zhiwang";
    public static final String app_id_dev = "zhiwangtest";

    public static final String default_channel = "creditease";

    public static final int sms_action_register = 1;
    public static final int sms_action_foget_login_password = 2;
    public static final int sms_action_foget_trade_password = 3;

    public static String wifissid;
    public static String network_type;

    public static final String key_device_guid = "device_guid";
    public static final String key_token = "token";
    public static final String key_password = "password";
    public static final String key_user_id = "user_id";

    public static final String key_data = "data";
    public static final String key_message = "message";

    public static final String push_channel_type = "jpush";
    public static final String key_push_channel_id = "push_channel_id";
    public static final String key_push_channel_type = "push_channel_type";
    public static final String key_session_id = "session_id";
    public static final String key_sn = "sn";
    public static final String key_phone = "phone";
    public static final String key_return_code = "return_code";
    public static final String key_return_message = "return_message";
    public static final String key_exist = "exist";
    public static final String key_verify_code = "verify_code";
    public static final String key_url = "url";
    public static final String key_recommend = "recommend";
    public static final String key_product = "product";
    public static final String key_name = "name";
    public static final String key_id_card_number = "id_card_number";
    public static final String key_amount = "amount";
    public static final String key_product_id = "product_id";
    public static final String key_bank_card_number = "bank_card_number";
    public static final String key_bank_id = "bank_id";
    public static final String key_user_bank_account_id = "user_bank_account_id";
    public static final String key_trade_password = "trade_password";
    public static final String key_order_id = "order_id";
    public static final String key_interest_str = "interest_str";
    public static final String key_pay_code = "pay_code";
    public static final String key_user = "user";
    public static final String key_user_bank_account = "user_bank_account";
    public static final String key_realname_session_id = "realname_session_id";
    public static final String key_user_bank_accounts = "user_bank_accounts";
    public static final String key_saved_products = "saved_products";
    public static final String key_products = "products";
    public static final String key_next_target = "next_target";
    public static final String key_tab = "tab";
    public static final String key_push_stop_work = "push_stop_work";
    public static final String key_financing_record = "financing_record";
    public static final String key_user_protocol_url = "user_protocol_url";
    public static final String key_old_password = "old_password";
    public static final String key_new_password = "new_password";
    public static final String key_bank_card = "bank_card";
    public static final String key_success_pay_bank_cards = "success_pay_bank_cards";

    public static final String key_action = "action";
    public static final String key_device_model = "device_model";
    public static final String key_push_token = "push_token";
    public static final String key_has_step_header = "has_step_header";
    public static final String key_feedback_list_last_id = "feedback_list_last_id";
    public static final String key_feedback = "feedback";
    public static final String key_last_id = "last_id";
    public static final String key_page_size = "page_size";
    public static final String key_list = "list";
    public static final String key_open_gesture = "open_gesture";
    public static final String key_open_voice = "open_voice";
    public static final String key_voice_lock_for_result = "voice_lock_for_result";
    public static final String key_gesture_pattern = "gesture_pattern";
    public static final String key_activity_title = "activity_title";
    public static final String key_about_us_urls = "about_us_urls";
    public static final String key_page_no = "page_no";
    public static final String key_total_count = "total_count";
    public static final String key_order_infos = "order_infos";
    public static final String key_has_order = "has_order";
    public static final String key_image_config = "image_config";
    public static final String key_platform = "platform";
    public static final String key_size = "size";
    public static final String key_target = "target";
    public static final String key_from_outer = "from_outer";
    public static final String key_add_card = "add_card";
    public static final String key_result = "result";

    public static final String key_from_user_info = "from_user_info";
    public static final String key_assets_record = "asserts_record";

    public static final String key_context = "context";
    public static final String go_shareto = "shareto";
    public static final String go_productlist = "productlist";
    public static final String go_assets = "assets";
    public static final String go_more = "more";
    public static final String go_home = "home";
    public static final String go_productdetail = "productdetail";
    public static final String go_h5 = "h5";
    public static final String go_buy_product = "buy_product";
    public static final String go_zanqianbao = "zanqianbao";//推送跳转攒钱宝详情页
    public static final String key_bonus = "bonus";
    public static final String key_order_deduct_id = "order_deduct_id";
    public static final String key_pay_amount = "pay_amount";
    public static final String key_alert = "alert";
    //单独处理
    public static final String go_login = "login";

    public static final String go_register = "register";

    public static final HashMap<String, Class<? extends BaseActivity>> targetMap = new HashMap<>();
    public static final String key_installed_short_cut = "installed_short_cut";
    public static final String key_outer_target = "outer_target";
    public static final String key_has_risk_test = "has_risk_test";
    public static final String key_imei = "imei";
    public static final String key_sign_activity = "sign_activity";
    public static final String key_bg_image_url = "bg_image_url";
    public static final String key_month_interest_str = "month_interest_str";
    public static final String key_last_refresh_push_info_time = "last_refresh_push_info_time";
    public static final String key_maybe_kill = "maybe_kill";
    public static final String key_legacy = "legacy";
    public static final String key_start_time = "start_time";
    public static final String key_asset_id = "asset_id";
    public static final String key_reinvest_id = "cont_invest_id";
    public static final String key_reinvest = "cont_invest";
    public static final String key_asset_sys_note = "asset_sys_note";
    public static final String key_jiejiegao_asset = "jiejiegao_asset";
    public static final String key_product_channel = "product_channel";
    public static final String key_has_no_order_tips = "has_no_order_tips";
    public static final String key_jiejiegao_asset_detail = "jiejiegao_asset_detail";
    public static final String key_jiejiegao_asset_payback_able = "jiejiegao_asset_paybackable";
    public static final String key_pay_back_info = "pay_back_info";
    public static final String key_payback_apply_date = "payback_apply_date";
    public static final String key_expect_payback_date = "expect_payback_date";
    public static final String key_payback_amount = "payback_amount";
    public static final String key_bank_card_mask_number = "bank_card_mask_number";
    public static final String key_target_name = "target_name";
    public static final String key_alert_last_show_time = "alert_last_show_time";
    public static final String key_alert_last_show_id = "alert_last_show_id";
    public static final String key_notify_shared = "notify_shared";
    public static final String key_jiejiegao_asset_tip_str = "jiejiegao_asset_tip_str";
    public static final String key_asset_news_id = "asset_news_id";
    public static final String key_auto_feedback = "auto_feedback";
    public static final String key_reinvest_options = "reinvest_options";
    public static final String key_fund_steps = "fund_steps";
    public static final String key_redeem_tips = "redeem_tips";
    public static final String key_asset_title = "asset_title";
    public static final String key_refresh_asset = "refresh_asset";
    public static final String key_cancel_action_tip = "cancel_action_tip";
    public static final String key_cancel_action_cancel = "cancel_action_cancel";
    public static final String key_cancel_action_confirm = "cancel_action_confirm";
    public static final String key_voice = "voice";
    public static final String key_script = "script";
    public static final String key_voice_random_sequence = "voice_random_sequence";
    public static final String key_fund_type = "fund_type";
    public static final String key_asset_refresh_time = "asset_refresh_time";
    public static final String key_payback_asset_type = "payback_asset_type";
    public static final String key_wx_share_content = "wx_share_content";
    public static final String key_liquidate_display_info = "liquidate_display_info";
    public static final String key_liquidate_rate = "liquidate_rate";
    public static final String key_payback_asset_groups = "payback_asset_groups";
    public static final String key_liquidate_result = "liquidate_result";
    public static final String key_liquidate_start_info = "liquidate_start_info";
    public static final String key_target_type = "target_type";
    public static final String key_asset_red_point_info = "asset_red_point_info";
    public static final String key_points = "points";
    public static final String key_last_refresh_asset_point_time = "last_refresh_asset_point_time";
    public static final String key_image_config_last_load_time = "image_config_last_load_time";
    public static final String key_show_asset_tab_red_point = "show_asset_tab_red_point";

    static {
        initTargetMap();
    }

    public static final String key_select_position = "select_position";
    public static final String key_reload_url = "reload_url";
    public static final String key_shares = "shares";
    public static final String key_sys_note = "sys_note";
    public static final String key_payback_assets = "payback_assets";
    public static final String key_channel = "channel";
    public static final String key_pay_result = "pay_result";
    public static final String key_buy_origin = "buy_origin";
    public static final String key_origin = "origin";
    public static final String key_confirm_card_info_type = "confirm_card_info_type";
    public static final String key_fang_dai_bao_detail = "fang_dai_bao_detail";
    public static final String key_selected_due_date = "selected_due_date";
    public static final String key_coupons = "coupons";
    public static final String key_selected_coupon = "selected_coupon";
    public static final String key_available = "available";
    public static final String key_coupon_id = "coupon_id";
    public static final String key_coupon_type = "coupon_type";
    public static final String key_default_coupon = "default_coupon";
    public static final String key_input_gesture_type = "input_gesture_type";
    public static final String key_verify_result = "verify_result";
    public static final String key_input_password_type = "input_password_type";
    public static final String key_timestamp = "timestamp";
    public static final String key_bonus_info = "bonus_info";
    public static final String key_last_message_id = "notification_id";//本地最新消息id
    public static final String key_unread_count = "unread_count";//本地未读消息总数
    public static final String key_notifications = "notifications";//消息列表
    public static final String key_asset_detail = "asset_detail";//资产详情
    public static final String key_extra_bonus_info = "extra_bonus_info";
    public static final String key_push_id_upload_flag = "push_id_upload_flag";//推送id是否已上传
    public static final String key_fund_sms_code = "fund_sms_code";//短信验证码
    public static final String key_product_groups = "product_groups";//产品列表
    public static final String key_group_name = "group_name";//产品组名
    public static final String key_group_desc = "group_desc";//产品分组描述
    public static final String key_fund_branch_bank_id = "branch_bank_id";//开户支行
    public static final String key_branch_banks = "branch_banks";//支行列表
    public static final String key_use_default_branch_bank = "use_default_branch_bank";//填写支行名字
    public static final String key_plan_id = "plan_id";//攒钱宝攒钱计划id
    public static final String key_test_id = "testid";//AB测试
    public static final String key_test_group = "testgroup";//AB测试
    public static final String key_pushid = "pushid";//推送id
    public static final String key_pushts = "pushts";//推送时间
    public static final String key_pushcontent = "pushcontent";//推送内容
    public static final String key_pushtarget = "pushtarget";//推送点击进入页面
    public static final String key_disclaimer = "disclaimer";//普泽合规参数
    public static final String key_productid = "productid";//tracking使用参数

    private static void initTargetMap() {
        targetMap.put(go_more, TabContainerActivity.class);
        targetMap.put(go_productlist, TabContainerActivity.class);
        targetMap.put(go_assets, TabContainerActivity.class);
        targetMap.put(go_home, TabContainerActivity.class);
        targetMap.put(go_productdetail, ProductDetailActivity.class);
        targetMap.put(go_h5, WebActivity.class);
        targetMap.put(go_buy_product, null);
        targetMap.put(go_login, InputPhoneActivity.class);
        targetMap.put(go_zanqianbao, ZQBAssetDetailActivity.class);
    }

    public static final String key_support_banks = "support_banks";
    public static final String key_provinces = "provinces";
    public static final String key_province = "province";
    public static final String key_city = "city";
    public static final String key_bank_name = "bank_name";
    public static final String key_bank = "bank";
    public static final String key_reserve_phone = "reserve_phone";

    public static final float PULL_TO_REFRESH_RESISTANCE = 2.5f;//倍数
    public static final int PULL_TO_REFRESH_DURATION_CLOSE = 300;//毫秒
    public static final int PULL_TO_REFRESH_DURATION_CLOSE_HEADER = 1000;//毫秒
    public static final float PULL_TO_REFRESH_HEADER_HEIGHT_REFRESH = 1.0f;//倍数

    //省份信息的常规排序顺序
    public static final String[] PROVINCE_NAME_IN_ORDER = {"北京市", "上海市", "天津市", "重庆市", "河北省", "山西省", "辽宁省",
            "吉林省", "黑龙江省", "江苏省", "浙江省", "安徽省", "福建省", "台湾省", "江西省", "山东省", "河南省", "湖北省", "湖南省", "广东省",
            "海南省", "四川省", "贵州省", "云南省", "陕西省", "甘肃省", "青海省", "内蒙古自治区", "广西壮族自治区", "西藏自治区", "宁夏回族自治区",
            "新疆维吾尔自治区"};

    //变现
    public static final String key_liquidate_entry = "liquidate_info";
    public static final String key_no_product_tip = "no_product_tip";

    //
    public static final String key_intro_items = "intro_items";
    //注册成功之后跳转H5
    public static final String KEY_STIMULATE_BUY_URL = "stimulate_buy_url";
    //WebActivity中的Url一些特殊来源
    public static final String KEY_WHERE_URL_COME_FROM = "where_url_come_from";
}
