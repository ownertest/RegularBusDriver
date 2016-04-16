package com.tel.china.regularbusdiver.util;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.tel.china.regularbusdiver.BuildConfig;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.bean.User;
import com.tel.china.regularbusdiver.system.StdApplication;


public class SharedPrefsUtil {
    private static final String prefix = BuildConfig.DEBUG ? "d_" : "r_";
    public static final String shared_prefs = "qxf";
    private static final String key_se_user = "se_user";
    private static final String key_se_real_name_info = "se_real_name_info";

    public static boolean contains(String key) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.contains(prefix + key);
    }

    public static long getLong(String key) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.getLong(prefix + key, 0);
    }

    public static void putLong(String key, long value) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putLong(prefix + key, value);
        editor.commit();
    }

    public static boolean getBoolean(String key) {
        return getBoolean(key, false);
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.getBoolean(prefix + key, defaultValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(prefix + key, value);
        editor.commit();
    }

    public static float getFloat(String key) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.getFloat(prefix + key, 0);
    }

    public static void putFloat(String key, float value) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(prefix + key, value);
        editor.commit();
    }

    public static String getString(String key) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.getString(prefix + key, "");
    }

    public static void putString(String key, String value) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(prefix + key, value);
        editor.commit();
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(prefix + key, value);
        editor.commit();
    }

    public static int getInt(String key) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        return sp.getInt(prefix + key, 0);
    }


    public static void saveObject(String key, Object obj) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(prefix + key, new Gson().toJson(obj));
        editor.commit();
    }

    public static Object getObject(String key, Class clazz) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        String json = sp.getString(prefix + key, "");
        return new Gson().fromJson(json, clazz);
    }

    /**
     * 获取本地记录，如果无记录则返回null
     *
     * @param key   key
     * @param clazz 返回结果对象类型
     * @return 无记录为null，反之为clazz类型
     */
    public static Object getObjectCouldBeNull(String key, Class clazz) {
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        String json = sp.getString(prefix + key, "");
        if (TextUtils.isEmpty(json)) {
            return null;
        }
        return new Gson().fromJson(json, clazz);
    }

    public static void removeObject(String key) {
        deleteValues(key);
    }




    public static void saveEncryptUser(User user) {
        String jsonUser = new Gson().toJson(user);
        String se_user = AesUtil.encrypt(jsonUser, StdApplication.deviceGuid);
        Log.d("encrypt string: " + se_user);
        putString(key_se_user, se_user);
    }

    public static User loadEncryptUser() {
        String se_user = getString(key_se_user);
        if (TextUtils.isEmpty(se_user)) {
            return null;
        }
        String jsonUser = AesUtil.decrypt(se_user, StdApplication.deviceGuid);
        return new Gson().fromJson(jsonUser, User.class);
    }




    //logout，清空用户数据和session_id
    public static void deleteUser() {
        deleteValues(key_se_user, Config.key_session_id);
    }

    public static void deleteValues(String... keys) {
        if (keys == null || keys.length == 0)
            return;
        SharedPreferences sp = StdApplication.instance.getApplicationContext().getSharedPreferences(shared_prefs, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        for (String key : keys) {
            editor.remove(prefix + key);
            Log.d("delete " + key);
        }
        editor.commit();
    }

}
