package com.tel.china.regularbusdiver.system;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.VolleyLog;
import com.tel.china.regularbusdiver.BuildConfig;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.bean.User;
import com.tel.china.regularbusdiver.http.RequestManager;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.SharedPrefsUtil;



public class StdApplication extends Application {
    public static long GESTURE_SHOW_DURATION = 5 * 60 * 1000;
    public static long TIME_WHEN_SCREEN_CLOSE = 0L;
    public static String host;
    public static String deviceGuid = "";
    public static StdApplication instance;
    public static String userAgent;
    private static User currentUser;
    private static String session_id;
    private static String key_se_user;
    private static String shared_prefs;


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
                initVolley();
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
            if (user != null && user.user_id.length() > 0) {
                return true;
            }
            return false;
        } catch (Exception e) {
            SharedPrefsUtil.deleteUser();
            return false;
        }
    }

}
