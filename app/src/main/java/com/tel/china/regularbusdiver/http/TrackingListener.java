package com.tel.china.regularbusdiver.http;

import com.android.volley.VolleyError;
import com.creditease.shangtongdai.StdApplication;
import com.creditease.shangtongdai.util.TrackingUtil;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.tendcloud.tenddata.TCAgent;

/**
 * Created by niutf on 16/1/25.
 */
/*package*/ class TrackingListener<T> implements StdResponseListener<T>{

    private StdResponseListener<T> mInnerListener;
    private String mUrl;



    public TrackingListener(StdResponseListener<T> mInnerListener, String mUrl) {
        this.mInnerListener = mInnerListener;
        this.mUrl = mUrl;
    }

    @Override
    public void onResponse(T response) {
        try {
            mInnerListener.onResponse(response);
        } catch (Exception e) {
            e.printStackTrace();
            trackNetworkError(e, mUrl);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        try {
            if (mInnerListener != null) {
                mInnerListener.onErrorResponse(error);
            }
        } catch (Exception e) {
            e.printStackTrace();
            trackNetworkError(e, mUrl);
        }
    }

    private static void trackNetworkError(Exception e, String url) {
        TCAgent.onEvent(StdApplication.instance.getApplicationContext(), url, e.getMessage());
        //google analytics
        Tracker tracker = StdApplication.instance.getGaTracker();
        tracker.send(new HitBuilders.EventBuilder()
                .setCategory(TrackingUtil.GACategoryError)
                .setAction(url)
                .setLabel(e.getMessage())
                .build());
    }
}
