package com.tel.china.regularbusdiver.http;

import android.app.Dialog;
import android.os.Build;
import android.text.TextUtils;

import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.ReturnCode;import com.tel.china.regularbusdiver.activity.BaseActivity;
import com.tel.china.regularbusdiver.util.Log;

import org.json.JSONObject;


public class BaseQxfResponseListener implements QxfResponseListener<JSONObject> {
    protected BaseActivity activity;
    protected Dialog dialog;

    public BaseQxfResponseListener(BaseActivity activity, Dialog dialog) {
        this.activity = activity;
        this.dialog = dialog;
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d("request_info", response.toString());
        if (dialog != null) {
            dialog.dismiss();
        }
        int return_code = response.optInt(Config.key_return_code, -1);
        //如果响应内容为空，忽略。
        if (response.isNull(Config.key_return_message)) {
            return;
        }

        String message = response.optString(Config.key_return_message);
        //如果消息为空，或者activity不适合显示错误信息
        if (TextUtils.isEmpty(message) || activity == null || activity.isFinishing()) {
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1 && activity.isDestroyed()) {
            return;
        }
        //sessionID过期，提示重新登录.
        if (ReturnCode.ERROR_INVALID_SESSION == return_code) {
            //activity.showSessionIDErrorDialog(message);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        String errorMessage = "on error response\t";
        if (error.networkResponse != null)
            errorMessage += new String(error.networkResponse.data);
        Log.d("request_info", errorMessage);
        if (dialog != null) {
            dialog.dismiss();
        }
        if (activity != null) {
            //activity.processCommonError(error);
        }
    }
}
