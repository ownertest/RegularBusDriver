package com.tel.china.regularbusdiver.system;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.VolleyLog;
import com.tel.china.regularbusdiver.BuildConfig;



/**
 * Created by zhaohaiyang on 16/1/8.
 */
public class StdApplication extends Application {
    public static long GESTURE_SHOW_DURATION = 5 * 60 * 1000;
    public static long TIME_WHEN_SCREEN_CLOSE = 0L;
    public static String test_id = "";//AB 测试
    public static String test_group = "";//AB 测试分组

    public static String host;
    public static String deviceGuid = "";
    public static StdApplication instance;
    public static String userAgent;
    private static User currentUser;
    private static String session_id;


    @Override
    public void onCreate() {
        super.onCreate();
        host = getResources().getString(R.string.app_host);
        int pid = android.os.Process.myPid();
        String name = getApplicationContext().getPackageName();
        ActivityManager manager = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        for(ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses()) {
            if(processInfo.pid == pid && processInfo.processName.equalsIgnoreCase(name)) {
                Log.d("application package name========: " + name);
                initTracking();
                break;
            }
        }
        instance = this;
    }

    private void initVolley() {
        VolleyLog.setTag("STD");
        VolleyLog.DEBUG = BuildConfig.DEBUG;
        userAgent = " volley/0" + " (" + Build.BRAND + ";" + Build.MODEL + ";"
                + Build.VERSION.RELEASE + ")" + " Android STD/" + BuildConfig.VERSION_NAME;

        RequestManager.init(this);
    }


    public static User getCurrentUser() {
        if (currentUser == null) {
            currentUser = SharedPrefsUtil.loadEncryptUser();
        }
        return currentUser;
    }

    public static void setCurrentUser(User user) {
        currentUser = user;
        if (currentUser != null) {
            SharedPrefsUtil.saveEncryptUser(currentUser);
        }
        map.put("userid", String.valueOf(user != null ? user.user_id : 0));
        TrackingAgent.setAppKV(map);
    }

    public static String getSessionId() {
        if (!TextUtils.isEmpty(session_id)) {
            return session_id;
        }
        //使用保存过的session_id
        session_id = SharedPrefsUtil.getString(Config.key_session_id);
        if (!TextUtils.isEmpty(session_id)) {
            return session_id;
        }

        return null;
    }

    public static void setSessionId(String session_id) {
        StdApplication.session_id = session_id;
        SharedPrefsUtil.putString(Config.key_session_id, session_id);
    }

    public static boolean isLogin() {
        try {
            User user = getCurrentUser();
            if (user != null && user.user_id > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            SharedPrefsUtil.deleteUser();
            return false;
        }
    }
    /**
     *
     * @param id    test id
     * @param group test group
     */
    public static void updateABTestInfo(String id, String group) {
        if (!test_id.equals(id) || !test_group.equals(group)) {
            test_id = id;
            test_group = group;

            SharedPrefsUtil.putString(Config.key_test_id, test_id);
            SharedPrefsUtil.putString(Config.key_test_group, test_group);
        }
    }

    /**
     * 从preference中取出ABTest相关的参�?     */
    private void initABTestInfo() {
        try {
            test_id = SharedPrefsUtil.getString(Config.key_test_id);
            test_group = SharedPrefsUtil.getString(Config.key_test_group);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
