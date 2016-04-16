package com.tel.china.regularbusdiver.util;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.listener.OnNavgiationChangedListener;

import java.util.ArrayList;

/**
 * 导航栏
 *
 */
public class NavigationBar extends LinearLayout implements View.OnClickListener, OnNavgiationChangedListener {
    private TabView mSelectedView = null;
    private Context mContext;
    private ArrayList<TabView> mTabViews = new ArrayList<TabView>();
    private NavigationListener navigateListener;
    private int mSelected = 0;

    public NavigationBar(Context context) {
        super(context);
        init(context);
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        setOrientation(LinearLayout.HORIZONTAL);
        this.mContext = context;
        LayoutInflater.from(mContext).inflate(R.layout.layout_navigation_bar, this, true);

        TabView home = new TabView();
        home.root = findViewById(R.id.navigation_btn_home);
        home.text = (TextView) findViewById(R.id.navigation_home_txt);
        home.image = (ImageView) findViewById(R.id.navigation_home_img);
        home.root.setTag(0);
        mTabViews.add(home);

        TabView cars = new TabView();
        cars.root = findViewById(R.id.navigation_btn_cars);
        cars.text = (TextView) findViewById(R.id.navigation_cars_txt);
        cars.image = (ImageView) findViewById(R.id.navigation_cars_img);
        cars.root.setTag(1);
        mTabViews.add(cars);

        TabView discount = new TabView();
        discount.root = findViewById(R.id.navigation_btn_discount);
        discount.text = (TextView) findViewById(R.id.navigation_discount_txt);
        discount.image = (ImageView) findViewById(R.id.navigation_discount_img);
        discount.root.setTag(2);
        mTabViews.add(discount);

        TabView mine = new TabView();
        mine.root = findViewById(R.id.navigation_btn_mine);
        mine.text = (TextView) findViewById(R.id.navigation_mine_txt);
        mine.image = (ImageView) findViewById(R.id.navigation_mine_img);
        mine.root.setTag(3);
        mTabViews.add(mine);

        setSelectedStatus(0);
        home.root.setOnClickListener(this);
        cars.root.setOnClickListener(this);
        discount.root.setOnClickListener(this);
        mine.root.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v != null && v.getTag() != null) {
            setSelectedStatus((Integer) v.getTag());
            if (navigateListener != null) {
                navigateListener.onOnNavigationBarClick(mSelected);
            }
        }
    }


    public View getDiscountImageView() {
        if (mTabViews.get(2) != null && mTabViews.get(2).image != null) {
            return mTabViews.get(2).image;
        }
        return null;
    }

    public View getCarsImageView() {
        if (mTabViews.get(1) != null && mTabViews.get(1).image != null) {
            return mTabViews.get(1).image;
        }
        return null;
    }

    @SuppressWarnings("deprecation")
    public void setSelectedStatus(int pos) {
        if (pos >= 0 && pos < mTabViews.size()) {
            for (TabView tab : mTabViews) {
                tab.image.setSelected(false);
                tab.image.setAlpha(0);
                tab.text.setTextColor(getColor(0));
            }
            mSelected = pos;
            mSelectedView = mTabViews.get(pos);
            mSelectedView.image.setSelected(true);
            mSelectedView.image.setAlpha(255);
            mSelectedView.text.setTextColor(getColor(1));
        }
    }

    public void setOnNavigationListener(NavigationListener listener) {
        this.navigateListener = listener;
    }

    public int getSelected() {
        return mSelected;
    }

    @Override
    @SuppressWarnings("deprecation")
    public void onTabChanged(float percent, int pos1, int pos2) {
        if (pos1 >= 0 && pos1 < mTabViews.size()) {
            mTabViews.get(pos1).image.setSelected(true);
            mTabViews.get(pos1).image.setAlpha((int) ((1 - percent) * 255));
            mTabViews.get(pos1).text.setTextColor(getColor(1 - percent));

        }
        if (pos2 >= 0 && pos2 < mTabViews.size()) {
            mTabViews.get(pos2).image.setSelected(true);
            mTabViews.get(pos2).image.setAlpha((int) (percent * 255));
            mTabViews.get(pos2).text.setTextColor(getColor(percent));
        }
    }

    private int getColor(float p) {
        int RED1 = 111, GREEN1 = 115, BLUE1 = 120;
        int RED2 = 43, GREEN2 = 167, BLUE2 = 221;
        return Color.rgb((int) (RED1 * (1 - p) + p * RED2), (int) (GREEN1 * (1 - p) + p * GREEN2), (int) (BLUE1
                * (1 - p) + p * BLUE2));
    }

    class TabView {
        View root;
        TextView text;
        ImageView image, dot;
    }

    public interface NavigationListener {
        void onOnNavigationBarClick(int nIndex);
    }
}
