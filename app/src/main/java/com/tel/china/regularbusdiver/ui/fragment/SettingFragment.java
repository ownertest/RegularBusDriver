package com.tel.china.regularbusdiver.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.system.StdApplication;
import com.tel.china.regularbusdiver.ui.activity.LoginActivity;
import com.tel.china.regularbusdiver.util.SharedPrefsUtil;
import com.tel.china.regularbusdiver.util.TitleBar;


public class SettingFragment extends BaseMainFragment {

    private TitleBar mTitleBar;
    private RelativeLayout mLoginView;
    private TextView mAboutView;
    private TextView mLogoutView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(mActivity).inflate(R.layout.setting_fragment, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        initView(view);
        initListener();
        super.onViewCreated(view, savedInstanceState);
    }

    public void initView(View view) {
        mTitleBar = (TitleBar) view.findViewById(R.id.setting_titlebar);
        mTitleBar.setTitleText(R.string.setting_title);
        mLoginView = (RelativeLayout) view.findViewById(R.id.setting_user_layout);
        mLogoutView = (TextView) view.findViewById(R.id.setting_logout);
        mAboutView = (TextView) view.findViewById(R.id.setting_aout);

        if (StdApplication.isLogin()) {
            mLoginView.setEnabled(false);
            mLogoutView.setEnabled(true);
        } else {
            mLoginView.setEnabled(true);
            mLogoutView.setEnabled(false);
        }
    }

    public void initListener() {
        mLoginView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mActivity, LoginActivity.class);
                startActivity(intent);
            }
        });

        mLogoutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (StdApplication.isLogin()) {
                    SharedPrefsUtil.deleteUser();
                    Toast.makeText(mActivity, "用户已经退出", Toast.LENGTH_LONG).show();
                    mLoginView.setEnabled(true);
                    mLogoutView.setEnabled(false);
                }
            }
        });

        mAboutView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mActivity, "电信研究院参赛作品", Toast.LENGTH_LONG).show();
            }
        });
    }
}
