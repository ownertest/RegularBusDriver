package com.tel.china.regularbusdiver.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.TelResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.ui.adapter.LineItemAdapter;
import com.tel.china.regularbusdiver.util.LineInfo;
import com.tel.china.regularbusdiver.util.LineInfoResult;
import com.tel.china.regularbusdiver.util.LineItem;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseMainFragment implements TelResponseListener{
    private TitleBar mTitle;
    private ListView mListView;
    private LineItemAdapter mAdapter;
    private LineInfoResult mResult;
    private LineInfo mLineInfo;
    private final static int LIST = 0x01;

    private class MyHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (!mActivity.isFinishing()) {
                switch (message.what) {
                    case LIST:
                        mAdapter.setData(mResult.getLineInfo(), mActivity);
                        mAdapter.notifyDataSetChanged();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(mActivity).inflate(R.layout.activity_home_new, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        mTitle = (TitleBar) view.findViewById(R.id.search_titlebar);
        mTitle.showBackButton(false);
        mListView = (ListView) view.findViewById(R.id.serch_listview);
    }

    private void initData() {
        mTitle.setTitleText(R.string.main_search_title);
        mAdapter = new LineItemAdapter();
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        requestLineData();
    }

    public void requestLineData() {
        UserHttper.backgroundRequestLineData(this);
    }
    @Override
    public void onResponse(Object response) {
        Log.e("LOGString", response.toString());
        mResult = new Gson().fromJson(response.toString(), LineInfoResult.class);
        if (null != mResult && mResult.getResult().equals("1")) {
            myHandler.obtainMessage(LIST).sendToTarget();
            Log.e("LOGString", mResult.getLineInfo().get(0).getLineNum());
            Log.e("LOGString", mResult.getLineInfo().get(0).getSchedule().get(0));
        } else {
            Log.e("LOGString", "result request error");
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("LOGString", "error--" + error.toString());
    }
}
