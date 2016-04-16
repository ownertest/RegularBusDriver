package com.tel.china.regularbusdiver.http;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.VolleyError;
import com.tel.china.regularbusdiver.util.Log;


public class TelRetryPolicy extends DefaultRetryPolicy {
    /**
     * Constructs a new retry policy.
     *
     * @param initialTimeoutMs  The initial timeout for the policy.
     * @param maxNumRetries     The maximum number of retries.
     * @param backoffMultiplier Backoff multiplier for the policy.
     */
    public TelRetryPolicy(int initialTimeoutMs, int maxNumRetries, float backoffMultiplier) {
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
        }
        super.retry(error);
    }
}
