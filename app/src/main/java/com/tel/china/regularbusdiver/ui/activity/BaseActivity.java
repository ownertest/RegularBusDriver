package com.tel.china.regularbusdiver.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.tel.china.regularbusdiver.http.RequestManager;

public abstract class BaseActivity extends Activity {
    protected final String TAG = this.getClass().getSimpleName();
    public static BaseActivity mCurrentActivity;
    protected Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCurrentActivity = this;
    }

    protected abstract int getContentViewId();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestManager.getRequestQueue().cancelAll(getClass().getName());
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mCurrentActivity = this;
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    public void showToast(String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(this, text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(text);
        }
        toast.show();
    }


}
