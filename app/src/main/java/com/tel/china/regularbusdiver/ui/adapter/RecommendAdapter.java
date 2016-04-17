package com.tel.china.regularbusdiver.ui.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.bean.ClassLines;

import java.util.List;

public class RecommendAdapter extends BaseAdapter{

    private List<ClassLines> mData;
    private Context mContext;

    public void setData(List<ClassLines> list, Context context) {
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
        ViewHolderRecommend holder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.recommend_listview_item, null);
            holder = new ViewHolderRecommend();
            holder.recommendItem = (TextView) view.findViewById(R.id.recmmendItem);

            view.setTag(holder);
        } else {
            holder = (ViewHolderRecommend) view.getTag();
        }
        if (null != mData) {
            ClassLines classLines = mData.get(i);
            if (null != classLines) {
                holder.recommendItem.setText(classLines.getBelongLine() + "发车时间" + classLines.getLineTime());
            }
        }
        return view;
    }

    class ViewHolderRecommend {
        private TextView recommendItem;

    }
}
