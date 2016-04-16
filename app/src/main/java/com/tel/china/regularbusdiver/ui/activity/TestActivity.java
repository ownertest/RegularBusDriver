package com.tel.china.regularbusdiver.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import com.tel.china.regularbusdiver.R;

public class TestActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        Button login = (Button)findViewById(R.id.btn_login);
        Button splash = (Button)findViewById(R.id.btn_splash);
        login.setOnClickListener(this);
        splash.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Class clazz = null;
        switch (v.getId()) {
            case R.id.btn_login:
                clazz = LoginActivity.class;
                break;
            case R.id.btn_splash:
                clazz = SplashActivity.class;
                break;
        }
        if (clazz != null) {
            Intent intent = new Intent(this, clazz);
            startActivity(intent);
        }
    }

}
