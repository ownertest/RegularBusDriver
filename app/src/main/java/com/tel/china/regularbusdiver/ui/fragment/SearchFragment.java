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
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.QxfResponseListener;
import com.tel.china.regularbusdiver.ui.activity.MainActivity;
import com.tel.china.regularbusdiver.ui.adapter.LineItemAdapter;
import com.tel.china.regularbusdiver.util.LineItem;
import com.tel.china.regularbusdiver.util.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends BaseMainFragment implements QxfResponseListener{
    private TitleBar mTitle;
    private ListView mListView;
    private LineItemAdapter mAdapter;

    private class MyHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (!mActivity.isFinishing()) {
                switch (message.what) {

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
        mListView = (ListView) view.findViewById(R.id.serch_listview);
    }

    private void initData() {
        mTitle.setTitleText(R.string.main_search_title);
        mAdapter = new LineItemAdapter();
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();

        //test
        LineItem lineItem1 = new LineItem("线路1", "上地", "日月宫");
        LineItem lineItem2 = new LineItem("线路2", "天坛", "六里桥");
        LineItem lineItem3 = new LineItem("线路3", "四通", "人大");
        LineItem lineItem4 = new LineItem("线路4", "北门", "明德");
        LineItem lineItem5 = new LineItem("线路5", "苏州街", "正阳门");
        LineItem lineItem6 = new LineItem("线路6", "前门", "北京站");
        LineItem lineItem7 = new LineItem("线路7", "香山", "奥森公园");
        List<LineItem> list = new ArrayList<LineItem>();
        list.add(lineItem1);
        list.add(lineItem2);
        list.add(lineItem3);
        list.add(lineItem4);
        list.add(lineItem5);
        list.add(lineItem6);
        list.add(lineItem7);
        mAdapter.setData(list, mActivity);
        mAdapter.notifyDataSetChanged();
        //end test
    }

    @Override
    public void onResponse(Object response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}
