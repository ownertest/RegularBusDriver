package com.tel.china.regularbusdiver.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.util.LineItem;

import java.util.List;

public class LineItemAdapter extends BaseAdapter{

    private List<LineItem> mData;
    private Context mContext;

    public void setData(List<LineItem> list, Context context) {
        mData = list;
        mContext = context;
    }
    @Override
    public int getCount() {
        return mData != null ? mData.size():0 ;
    }

    @Override
    public Object getItem(int i) {
        if (null != mData) {
            return mData.get(i);
        }
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.line_listview_item, null);
            holder = new ViewHolder();
            holder.mLineName = (TextView) view.findViewById(R.id.item_line_name);
            holder.startStation = (TextView) view.findViewById(R.id.item_line_start);
            holder.endStation = (TextView) view.findViewById(R.id.item_line_end);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if (null != mData) {
            LineItem lineItem = mData.get(i);
            if (null != lineItem) {
                holder.mLineName.setText(lineItem.getLineName());
                holder.startStation.setText(lineItem.getStartStation());
                holder.endStation.setText(lineItem.getEndStation());
            }
        }
        return view;
    }

    class ViewHolder {
        private TextView mLineName;
        private TextView startStation;
        private TextView endStation;
    }
}
