package com.tel.china.regularbusdiver.util;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tel.china.regularbusdiver.R;

public class TitleBar extends LinearLayout {

    private Context mContext;
    private TextView mTitleTv;
    private ImageView mBackIv;
    private Button mRightBtn;
    private String mTitleName;
    private boolean mShowBackBtn;

    public TitleBar(Context context) {
        super(context);
        Init(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        Init(context);
    }

    private void Init(Context context) {
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.view_title_bar, this);
        mTitleTv = (TextView) findViewById(R.id.title_bar_text);
        mBackIv = (ImageView) findViewById(R.id.title_bar_btn_back);
        showBackButton(mShowBackBtn);
        mRightBtn = (Button) findViewById(R.id.title_right_btn);
        if (mTitleName == null) {
            mTitleName = "";
        }
        if (mTitleTv != null) {
            mTitleTv.setText(mTitleName);
        }
        setBackgroundColor(getResources().getColor(R.color.common_title_bg_color));
    }

    public void showBackButton(boolean isShow) {
        if (mBackIv != null) {
            if (isShow) {
                mBackIv.setVisibility(View.VISIBLE);
            } else {
                mBackIv.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void setBackClickListener(OnClickListener listener) {
        mBackIv.setOnClickListener(listener);
    }

    public void setTopClickListener(OnClickListener listener) {
        mTitleTv.setOnClickListener(listener);
    }

    public void setTitleText(String text) {
        mTitleTv.setText(text);
    }

    public void setTitleText(int id) {
        String text = mContext.getResources().getString(id);
        mTitleTv.setText(text);
    }

    public void setRightButtonText(int id) {
        mRightBtn.setVisibility(View.VISIBLE);
        String text = mContext.getResources().getString(id);
        mRightBtn.setText(text);
    }

    public void setRightButtonText(String text) {
        mRightBtn.setVisibility(View.VISIBLE);
        mRightBtn.setText(text);
    }

    public void showRightButtonArrow(int id) {
        Drawable drawableTitle = mContext.getResources().getDrawable(id);
        if (drawableTitle != null) {
            drawableTitle.setBounds(0, 0, drawableTitle.getMinimumWidth(), drawableTitle.getMinimumHeight());// 必须设置图片大小，否则不显示
            mRightBtn.setCompoundDrawables(null, null, drawableTitle, null);
        }
    }

    public void setRightClickListener(OnClickListener listener) {
        mRightBtn.setOnClickListener(listener);
    }

    public TextView getTitleTv() {
        return mTitleTv;
    }

    public View getBackButton() {
        return mBackIv;
    }

}

