package com.tel.china.regularbusdiver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.tel.china.regularbusdiver.R;


public class SplashActivity extends Activity {

    private ImageView mImageView;
    private long mTime;
    private final static int SPLASH = 1;

    private class MyHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (!isFinishing()) {
                switch (message.what) {
                    case SPLASH:
                        while (true) {
                            long time = System.currentTimeMillis();
                            if (time - mTime > 2000) {
                                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            }
                        }
                        break;
                    default:
                        break;
                }
            }
            return false;
        }
    }

    private Handler myHandler = new Handler(new MyHandler());
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mTime = System.currentTimeMillis();
        initView();
        initListener();
        myHandler.sendEmptyMessageDelayed(SPLASH, 2000);
    }

    private void initView() {
        mImageView = (ImageView)findViewById(R.id.logo);
    }

    private void initListener() {
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
