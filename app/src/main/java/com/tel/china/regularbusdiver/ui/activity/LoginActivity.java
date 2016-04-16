package com.tel.china.regularbusdiver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.QxfResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.ui.ClearableEditText;
import com.tel.china.regularbusdiver.util.Log;

import org.json.JSONObject;


/**
 * Created by zhaohaiyang on 16/1/22.
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, QxfResponseListener<JSONObject>{

    public static final int REQUEST_LOGINSMS = 2001;
    public static final int REQUEST_REGISTER = 2002;
    ClearableEditText et_user_name;
    ClearableEditText et_password;
    Button sign_in_sms;
    Button sign_in;
    Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        et_user_name = (ClearableEditText) findViewById(R.id.et_user_name);
        et_password = (ClearableEditText) findViewById(R.id.et_password);
        sign_in = (Button) findViewById(R.id.bt_sign_in);
        sign_in_sms= (Button) findViewById(R.id.bt_sign_in_sms);
        sign_up = (Button) findViewById(R.id.bt_sign_up);
        sign_in.setOnClickListener(this);
        et_user_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_user_name.requestFocus();
            }
        });
    }

    @Override
    public void onResponse(JSONObject response) {

        Log.d("LoginActivity", response.toString());
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("LoginActivity", error.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_sign_up:
                gotoRegisterActivity();
                break;
            case R.id.bt_sign_in_sms:
                gotoSignInSmsActivity();
                break;
            case R.id.bt_sign_in:
                backgroundLoginCheck();
                break;
        }
    }


    private void backgroundLoginCheck() {
        String userName = et_user_name.getText().toString().trim();
        String password = et_password.getText().toString().trim();
        UserHttper.backgroundUserLogin(userName, password, this);
    }

    private void gotoSignInSmsActivity() {

    }

    private void gotoRegisterActivity() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }



}
