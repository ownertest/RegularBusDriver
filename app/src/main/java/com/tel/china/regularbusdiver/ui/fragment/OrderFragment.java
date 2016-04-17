package com.tel.china.regularbusdiver.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.bean.Line;
import com.tel.china.regularbusdiver.util.Log;

import java.util.ArrayList;

public class OrderFragment extends BaseMainFragment {
    private ArrayList<Line> options1Items = new ArrayList<Line>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<ArrayList<String>>();
    OptionsPickerView pvOptions;
    View vMasker;
    TextView selectBus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(mActivity).inflate(R.layout.order_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        initData();
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        vMasker = (View) view.findViewById(R.id.vMasker);
        selectBus =(TextView) view.findViewById(R.id.bt_select_bus);
        pvOptions = new OptionsPickerView(view.getContext());

    }

    private  void initData() {
        options1Items.add(new Line(0,"线路一"));
        options1Items.add(new Line(1,"线路二"));
        options1Items.add(new Line(3,"线路三"));
        ArrayList<String> options2Items_01=new ArrayList<String>();
        options2Items_01.add("07:10");
        options2Items_01.add("12:10");
        options2Items_01.add("18:10");
        ArrayList<String> options2Items_02=new ArrayList<String>();
        options2Items_02.add("08:10");
        options2Items_02.add("12:20");
        ArrayList<String> options2Items_03=new ArrayList<String>();
        options2Items_03.add("09:10");
        options2Items_03.add("11:10");
        options2Items_03.add("15:10");
        options2Items.add(options2Items_01);
        options2Items.add(options2Items_02);
        options2Items.add(options2Items_03);
        //三级联动效果
        pvOptions.setPicker(options1Items, options2Items, true);
        //设置选择的三级单位
        //        pwOptions.setLabels("省", "市", "区");
        pvOptions.setTitle("选择班车");
        pvOptions.setCyclic(false, false, false);
        //设置默认选中的三级项目
        //监听确定选择按钮
        pvOptions.setSelectOptions(1, 1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText()
                        + options2Items.get(options1).get(option2);
                Log.d("OrderFragment", tx);
                vMasker.setVisibility(View.GONE);
            }
        });

        selectBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
    }

}
