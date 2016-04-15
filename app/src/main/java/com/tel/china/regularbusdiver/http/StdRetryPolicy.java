package com.tel.china.regularbusdiver.http;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.creditease.shangtongdai.util.Log;

/**
 * Created by niutf on 16/1/25.
 */
public class StdRetryPolicy extends DefaultRetryPolicy {
    /**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     * @param backoffMultiplier Backoff multiplier for the policy.
     */
    public StdRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
        super(initialTimeoutMs, maxNumRetries, backoffMultiplier);
    }

    @Override
    public void retry(VolleyError error) throws VolleyError {
        //如果是403错误，不重试
        if (error.networkResponse != null) {
            if (error.networkResponse.statusCode == HttpStatus.UNAUTHORIZED ||
                    error.networkResponse.statusCode == HttpStatus.FORBIDDEN) {
                Log.d("网络返回： " + error.networkResponse.statusCode + " 不重试");
                throw error;
            }
        } else if (error instanceof StdApiError) {
            Log.d(String.format("网络返回：%d %s 不重试", ((StdApiError) error).getCode(), error.getMessage()));
            throw error;
        }
        super.retry(error);
    }
}