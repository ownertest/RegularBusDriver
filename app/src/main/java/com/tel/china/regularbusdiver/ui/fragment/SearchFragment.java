package com.tel.china.regularbusdiver.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.TelResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.ui.activity.LineDetailActivity;
import com.tel.china.regularbusdiver.ui.adapter.LineItemAdapter;
import com.tel.china.regularbusdiver.util.Constants;
import com.tel.china.regularbusdiver.util.LineInfo;
import com.tel.china.regularbusdiver.util.LineInfoResult;
import com.tel.china.regularbusdiver.util.LineItem;
import com.tel.china.regularbusdiver.util.ListUtils;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseMainFragment implements TelResponseListener{
    private TitleBar mTitle;
    private ListView mListView;
    private LineItemAdapter mAdapter;
    private LineInfoResult mResult;
    private List<LineInfo> mLineInfoList;
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
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int index = i - mListView.getHeaderViewsCount();
                Intent intent = new Intent(mActivity, LineDetailActivity.class);
                intent.putExtra(Constants.LINE_NUM, mLineInfoList.get(index).getLineNum());
                intent.putExtra(Constants.START_STATION, (String) ListUtils.getIndex(mLineInfoList.get(index).getSchedule(), 0));
                intent.putExtra(Constants.END_STATION, (String) ListUtils.getIndex(mLineInfoList.get(index).getSchedule(), 1));
                startActivity(intent);
            }
        });
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
        mResult = new Gson().fromJson(response.toString(), LineInfoResult.class);
        if (null != mResult && mResult.getResult().equals("1")) {
            mLineInfoList = mResult.getLineInfo();
            myHandler.obtainMessage(LIST).sendToTarget();
        } else {
            Log.e("LOGString", "error--result is null");
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("LOGString", "error--" + error.toString());
    }
}
