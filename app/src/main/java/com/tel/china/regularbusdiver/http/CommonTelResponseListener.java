package com.tel.china.regularbusdiver.http;

import android.app.Dialog;
import android.text.TextUtils;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.Config;
import com.tel.china.regularbusdiver.ReturnCode;
import com.tel.china.regularbusdiver.ui.activity.BaseActivity;
import com.tel.china.regularbusdiver.util.Log;

import org.json.JSONObject;


public abstract class CommonTelResponseListener extends BaseQxfResponseListener {

    public CommonTelResponseListener(BaseActivity activity, Dialog dialog) {
        super(activity, dialog);
    }

    @Override
    public void onResponse(JSONObject response) {
        super.onResponse(response);
        int return_code = response.optInt(Config.key_return_code, -1);
        if (ReturnCode.OK == return_code) {
            try {
                onSuccessResponse(response);
            } catch (Exception e) {
                Log.e(Log.TAG, "on success response error...", e);
            }
            return;
        }
        if (response.isNull(Config.key_return_message)) {
            return;
        }
        String message = response.optString(Config.key_return_message);
        if (!TextUtils.isEmpty(message) && activity != null && return_code != ReturnCode.ERROR_INVALID_SESSION) {
            activity.showToast(message, Toast.LENGTH_SHORT);
        }
    }

    abstract public void onSuccessResponse(JSONObject response);

    @Override
    public void onErrorResponse(VolleyError error) {
        super.onErrorResponse(error);
    }
}
