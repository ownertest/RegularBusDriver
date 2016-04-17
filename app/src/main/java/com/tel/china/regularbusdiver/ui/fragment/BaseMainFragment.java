package com.tel.china.regularbusdiver.ui.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.widget.Toast;

import com.tel.china.regularbusdiver.ui.activity.MainActivity;

public abstract class BaseMainFragment extends Fragment {
    protected MainActivity mActivity;
    protected Toast toast;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.mActivity = (MainActivity) activity;
    }
    public void showToast(String text, int duration) {
        if (toast == null) {
            toast = Toast.makeText(this.getActivity(), text, duration);
            toast.setGravity(Gravity.CENTER, 0, 0);
        } else {
            toast.setText(text);
        }
        toast.show();
    }
}