package com.tel.china.regularbusdiver.util;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wuyq on 14-8-5.
 */
public class RuleUtil {
    public static final String rule_id_card_no = "identity_id";
    public static int id_card_hint_length = 18;
    public static final String rule_phone = "verify_phone";
    public static int phone_hint_length = 11;
    public static final String rule_string = "string";
    public static final String rule_num = "verify_num";
    public static final String rule_decimal = "decimal";
    public static final String rule_email = "email";
    public static final String rule_3_digits = "verify_3digits";
    public static final String rule_4_digits = "verify_4digits";
    public static final String rule_password = "verify_password";


    private static Pattern p_phone = Pattern.compile("^(1[3-9])[0-9]{9}$");
    private static Pattern p_test_phone = Pattern.compile("^314159[0-9]{5}$");
    private static Pattern p_6_num = Pattern.compile("[0-9]{6}");
    private static Pattern p_decimal = Pattern.compile("[0-9]*\\.?[0-9]{0,2}");
    private static Pattern p_email = Pattern.compile("^\\S+@\\S+\\.\\S+$");
    private static Pattern p_3_digits = Pattern.compile("[0-9]{3}");
    private static Pattern p_4_digits = Pattern.compile("[0-9]{4}");

    public static boolean verifyPhone(String phone) {
        if (phone == null || phone.trim().length() == 0)
            return false;
        Matcher m = p_test_phone.matcher(phone);
        if (m.matches())
            return true;
        m = p_phone.matcher(phone);
        return m.matches();
    }

    public static boolean verifyNum(String num) {
        Matcher matcher = p_6_num.matcher(num);
        return matcher.matches();
    }

    public static boolean verifyDecimal(String decimal) {
        if (decimal == null || decimal.trim().length() == 0) return false;
        Matcher matcher = p_decimal.matcher(decimal);
        return matcher.matches();
    }

    public static boolean verifyEmail(String email) {
        if (email == null || email.trim().length() == 0) return false;
        Matcher matcher = p_email.matcher(email);
        return matcher.matches();
    }

    public static boolean verify3Digits(String num) {
        Matcher matcher = p_3_digits.matcher(num);
        return matcher.matches();
    }

    public static boolean verify4Digits(String num) {
        Matcher matcher = p_4_digits.matcher(num);
        return matcher.matches();
    }

    public static boolean verifyPassword(String password) {
        if (password != null && password.length() >= 6) {
            return true;
        }
        return false;
    }


    public static boolean verifyIDCardNo(String identity) {
        if (identity == null || identity.trim().length() == 0) {
            return false;
        }
        if (identity.length() != 18) {
            return false;
        }
        String dddddd = identity.substring(0, 6);
        if (!verifyNum(dddddd)) {
            return false;
        }
        String birthday = identity.substring(6, 14);
        if (!verifyDecimal(birthday)) {
            return false;
        }
        if (!verifyDate(birthday)) {
            return false;
        }
        char p = identity.charAt(17);
        p = Character.toUpperCase(p);
        char q = expectVerifyCode(identity);
        if (p != q) {
            return false;
        }
        return true;
    }

    private static final int[] verify_weight = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
    private static final char[] verify_code = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'};

    private static char expectVerifyCode(String identity) {
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += (identity.charAt(i) - '0') * verify_weight[i];
        }
        return verify_code[sum % 11];
    }


    private static boolean verifyDate(String date) {
        int year = Integer.valueOf(date.substring(0, 4));
        int month = Integer.valueOf(date.substring(4, 6));
        if (month <= 0 || month > 13) {
            return false;
        }
        int day = Integer.valueOf(date.substring(6));
        if (day <= 0 || day >= 32) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        calendar.set(Calendar.DATE, 1);
        int max = calendar.getActualMaximum(Calendar.DATE);
        if (day > max) {
            return false;
        }
        return true;
    }

    public static boolean verify(String rule, String str) {
        if (rule_string.equals(rule)) {
            return !TextUtils.isEmpty(str);
        }
        if (rule_num.equals(rule)) {
            return verifyNum(str);
        }
        if (rule_email.equals(rule)) {
            return verifyEmail(str);
        }
        if (rule_decimal.equals(rule)) {
            return verifyDecimal(str);
        }
        if (rule_phone.equals(rule)) {
            str = str.replaceAll(" ", "");
            return verifyPhone(str);
        }
        if (rule_id_card_no.equals(rule)) {
            return verifyIDCardNo(str);
        }
        if (rule_3_digits.equals(rule)) {
            return verify3Digits(str);
        }
        if (rule_4_digits.equals(rule)) {
            return verify4Digits(str);
        }
        if (rule_password.equals(rule)) {
            return verifyPassword(str);
        }
        return false;
    }
}
