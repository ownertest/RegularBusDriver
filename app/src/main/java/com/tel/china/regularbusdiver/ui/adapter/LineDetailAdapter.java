package com.tel.china.regularbusdiver.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.TelResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.system.StdApplication;
import com.tel.china.regularbusdiver.ui.activity.LoginActivity;
import com.tel.china.regularbusdiver.util.ListUtils;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.Schedule;

import java.util.List;


public class LineDetailAdapter extends BaseAdapter implements TelResponseListener{
    private List<Schedule> mData;
    private Context mContext;

    public void setData(List<Schedule> data, Context context) {
        mData = data;
        mContext = context;
    }
    @Override
    public int getCount() {
        return null != mData ? mData.size() : 0;
    }

    @Override
    public Object getItem(int i) {
        return ListUtils.getIndex(mData, i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == view) {
            view = LayoutInflater.from(mContext).inflate(R.layout.detail_listview_item, null);
            holder = new ViewHolder();
            holder.mTime = (TextView) view.findViewById(R.id.detail_time);
            holder.mOrder = (TextView) view.findViewById(R.id.detail_order);
            holder.mFree = (TextView) view.findViewById(R.id.detail_free);
            holder.mOrderBtn = (Button) view.findViewById(R.id.btn_order);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (null != mData && null != mData.get(i)) {
            final Schedule schedule = mData.get(i);
            holder.mTime.setText(schedule.getTime());
            holder.mOrder.setText(schedule.getOrder());
            holder.mFree.setText(schedule.getFree());

            holder.mOrderBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.e("LOGString", "orderd!");
                    if (!StdApplication.isLogin()) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        mContext.startActivity(intent);
                    } else {
                        UserHttper.backgroundRequestarOrder(schedule.getLineId(), "1", StdApplication.getCurrentUser().name, LineDetailAdapter.this);
                    }
                }
            });
        }
        return view;
    }

    @Override
    public void onResponse(Object response) {
        Log.e("LOGString", "order = " +response.toString());
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    class ViewHolder {
        private TextView mTime;
        private TextView mOrder;
        private TextView mFree;
        private Button mOrderBtn;
    }
}
