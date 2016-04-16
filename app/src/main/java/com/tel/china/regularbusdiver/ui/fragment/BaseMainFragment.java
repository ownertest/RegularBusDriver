package com.tel.china.regularbusdiver.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import com.tel.china.regularbusdiver.ui.activity.MainActivity;

public abstract class BaseMainFragment extends Fragment {
    private MainActivity mActivity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (MainActivity) activity;
    }

}