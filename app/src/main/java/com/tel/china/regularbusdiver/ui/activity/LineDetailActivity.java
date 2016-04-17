package com.tel.china.regularbusdiver.ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.TextureView;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.bean.ClassLines;
import com.tel.china.regularbusdiver.bean.Station;
import com.tel.china.regularbusdiver.bean.carInfoDetail;
import com.tel.china.regularbusdiver.http.TelResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.ui.adapter.LineDetailAdapter;
import com.tel.china.regularbusdiver.util.Constants;
import com.tel.china.regularbusdiver.util.Schedule;
import com.tel.china.regularbusdiver.util.TitleBar;

import java.util.ArrayList;
import java.util.List;

public class LineDetailActivity extends Activity implements TelResponseListener{

    private String mLineNum;
    private String mStartStation;
    private String mEndStation;
    private TextView mLineName;
    private TextView mStartName;
    private TextView mEndName;
    private TitleBar mTitleBar;

    private ListView mListView;
    private LineDetailAdapter mAdapter;
    private carInfoDetail mCarInfoDetail;
    private String allStations;
    private List<Schedule> mData = new ArrayList<Schedule>();
    private final static int LIST = 1;

    private class MyHandler implements Handler.Callback {

        @Override
        public boolean handleMessage(Message message) {
            if (!isFinishing()) {
                switch (message.what) {
                    default:
                    case LIST:
                        mAdapter.setData(mData, LineDetailActivity.this);
                        mAdapter.notifyDataSetChanged();
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
        setContentView(R.layout.activity_line_detail);
        initView();
        initData();
        initListener();
    }

    private void initView() {
        mTitleBar = (TitleBar) findViewById(R.id.line_detail_title_bar);
        mLineName = (TextView) findViewById(R.id.line_detail_line_name);
        mStartName = (TextView) findViewById(R.id.line_detail_line_start);
        mEndName = (TextView) findViewById(R.id.line_detail_line_end);
        mListView = (ListView) findViewById(R.id.line_detail_list);
    }

    private void initData() {
        if (null != getIntent()) {
            mLineNum = getIntent().getStringExtra(Constants.LINE_NUM);
            mStartStation = getIntent().getStringExtra(Constants.START_STATION);
            mEndStation = getIntent().getStringExtra(Constants.END_STATION);

            mLineName.setText("线路" + mLineNum);
            mStartName.setText(mStartStation);
            mEndName.setText(mEndStation);

            mTitleBar.setTitleText(R.string.line_detail_title);
            mTitleBar.showBackButton(true);
            mAdapter = new LineDetailAdapter();

            mListView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();

//            //Test
//            Schedule s1 = new Schedule("08:00", "12", "18");
//            Schedule s2 = new Schedule("08:20", "10", "20");
//            Schedule s3 = new Schedule("09:10", "22", "31");
//            Schedule s4 = new Schedule("11:00", "18", "30");
//            Schedule s5 = new Schedule("11:20", "15", "40");
//            List<Schedule> list = new ArrayList<>();
//            list.add(s1);list.add(s2);list.add(s3);list.add(s4);list.add(s5);
//            mAdapter.setData(list, this);
//            mAdapter.notifyDataSetChanged();
            //end test

            requestLineDetailInfo();
        }
    }

    private void initListener() {
        mTitleBar.setBackClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void requestLineDetailInfo() {
        UserHttper.backgroundRequestarInfoDetail(mLineNum, this);
    }

    @Override
    public void onResponse(Object response) {

        mCarInfoDetail = new Gson().fromJson(response.toString(), carInfoDetail.class);
        if (null != mCarInfoDetail && mCarInfoDetail.getResult().equals("1")) {
            List<Station> stations = mCarInfoDetail.getStation();
            allStations = "途径各站：";
            for (int i = 0; i < stations.size()-2; i++) {
                allStations += (stations.get(i).getName() + " - ");
            }
            allStations += stations.get(stations.size() - 1).getName();
            for (ClassLines line : mCarInfoDetail.getClassLines()) {
                Schedule s = new Schedule(line.getLineTime(), "" + (line.getTimeSeat() - line.getFreeSeat()), "" + line.getFreeSeat(), "" + line.getLineId());
                mData.add(s);
            }
            myHandler.obtainMessage(LIST).sendToTarget();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }
}
