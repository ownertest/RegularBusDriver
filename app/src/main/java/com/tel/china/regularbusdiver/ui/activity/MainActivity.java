package com.tel.china.regularbusdiver.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.http.QxfResponseListener;
import com.tel.china.regularbusdiver.listener.OnFragmentSelectedListener;
import com.tel.china.regularbusdiver.ui.fragment.LocationFragment;
import com.tel.china.regularbusdiver.ui.fragment.OrderFragment;
import com.tel.china.regularbusdiver.ui.fragment.SearchFragment;
import com.tel.china.regularbusdiver.ui.fragment.SettingFragment;
import com.tel.china.regularbusdiver.ui.view.NoScrollViewPager;
import com.tel.china.regularbusdiver.util.NavigationBar;

import java.util.HashMap;

public class MainActivity extends FragmentActivity implements QxfResponseListener {
    private PageAdapter adapter;
    private NavigationBar mNavigationBar;
    private NoScrollViewPager mViewPager;
    private Toast mKeyCodeBackToast;
    private int mMilliSeconds = 2 * 1000;
    private long mKeyCodeBackLastDownTime = Integer.MIN_VALUE;

    private int lastPos;// 上次用户选中的页卡
//    private OnPagerScrollListener onScrollListener; //TODO
    private HashMap<Integer, OnFragmentSelectedListener> mSelectedListeners = new HashMap<Integer, OnFragmentSelectedListener>();

    private class HandlerCallback implements Handler.Callback {
        @Override
        public boolean handleMessage(Message msg) {
            return true;
        }
    }

    private Handler mHandler = new Handler(new HandlerCallback());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mian);
        initView();
        initData();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void initView() {
        mViewPager = (NoScrollViewPager) findViewById(R.id.main_viewpager);
        mNavigationBar = (NavigationBar) findViewById(R.id.main_navigation);
        adapter = new PageAdapter(getSupportFragmentManager(), mViewPager, mNavigationBar);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);
    }

    private void initData() {
        mSelectedListeners.clear();
    }

//    public void setOnPagerScrollListener(OnPagerScrollListener listener) {
//        onScrollListener = listener;
//    }

    /**
     * 是否允许ViewPager滑动
     */
    public void setViewPagerScrollEnable(boolean enable) {
        mViewPager.setNoScroll(!enable);
    }

    public void setOnFragmentSelectedListener(int positon, OnFragmentSelectedListener listener) {
        if (positon >= 0 && positon < 4 && listener != null) {
            mSelectedListeners.put(positon, listener);
        }
    }


    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && event.getRepeatCount() == 0) {
                if (null == mKeyCodeBackToast) {
                    mKeyCodeBackToast = Toast.makeText(this, getString(R.string.keycode_back_alert_message),
                            Toast.LENGTH_SHORT);
                    mKeyCodeBackToast.show();
                } else if (mMilliSeconds < System.currentTimeMillis() - mKeyCodeBackLastDownTime) {
                    mKeyCodeBackToast.show();
                } else {
                    mKeyCodeBackToast.cancel();
                    mKeyCodeBackToast = null;
                    System.exit(0);
                }
                mKeyCodeBackLastDownTime = System.currentTimeMillis();
            }
        }
        return super.dispatchKeyEvent(event);
    }

    class PageAdapter extends FragmentStatePagerAdapter implements NavigationBar.NavigationListener, OnPageChangeListener {
        private NavigationBar mNaviBar;
        private NoScrollViewPager mViewPager;

        public PageAdapter(FragmentManager manager, NoScrollViewPager viewPager, NavigationBar navigationBar) {
            super(manager);
            this.mViewPager = viewPager;
            this.mNaviBar = navigationBar;
            mNaviBar.setOnNavigationListener(this);
            mViewPager.setOnPageChangeListener(this);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Fragment.instantiate(MainActivity.this, SearchFragment.class.getName(), null);
                case 1:
                    return Fragment.instantiate(MainActivity.this, OrderFragment.class.getName(), null);
                case 2:
                    return Fragment.instantiate(MainActivity.this, LocationFragment.class.getName(), null);
                case 3:
                    return Fragment.instantiate(MainActivity.this, SettingFragment.class.getName(), null);
                default:
                    break;
            }
            return null;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public void onOnNavigationBarClick(int position) {
            if (lastPos == position) {
                return;
            }
            lastPos = position;
            mViewPager.setCurrentItem(position, false);
            if (mSelectedListeners.get(position) != null) {
                mSelectedListeners.get(position).OnFragmentSelected();
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (ViewPager.SCROLL_STATE_IDLE == state && lastPos != mViewPager.getCurrentItem()) {
                int position = mViewPager.getCurrentItem();
                mNavigationBar.setSelectedStatus(position);
                lastPos = position;
                if (mSelectedListeners.get(position) != null) {
                    mSelectedListeners.get(position).OnFragmentSelected();
                }
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            if (position >= 0 && position < 3) {// 最后的一个页面不能滑
                if (mNavigationBar != null) {
                    mNavigationBar.onTabChanged(positionOffset, position, position + 1);
                }
            }
//            if (onScrollListener != null) {
//                onScrollListener.onScrollChanged();
//            }
        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onDestroy() {
        if (mSelectedListeners != null) {
            mSelectedListeners.clear();
        }
        super.onDestroy();
        System.exit(0);
    }

    @Override
    public void onResponse(Object response) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
}

