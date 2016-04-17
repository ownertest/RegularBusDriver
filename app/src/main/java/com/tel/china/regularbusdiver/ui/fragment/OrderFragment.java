package com.tel.china.regularbusdiver.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.bigkoo.pickerview.OptionsPickerView;
import com.google.gson.Gson;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.bean.carInfoDetail;
import com.tel.china.regularbusdiver.bean.ClassLines;
import com.tel.china.regularbusdiver.bean.Line;
import com.tel.china.regularbusdiver.bean.User;
import com.tel.china.regularbusdiver.http.TelResponseListener;
import com.tel.china.regularbusdiver.http.UserHttper;
import com.tel.china.regularbusdiver.system.StdApplication;
import com.tel.china.regularbusdiver.ui.adapter.RecommendAdapter;
import com.tel.china.regularbusdiver.util.LineInfo;
import com.tel.china.regularbusdiver.util.LineInfoResult;
import com.tel.china.regularbusdiver.util.Log;
import com.tel.china.regularbusdiver.util.TitleBar;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class OrderFragment extends BaseMainFragment {
    private ArrayList<Line> options1Items = new ArrayList<Line>();
    private ArrayList<ArrayList<ClassLines>> options2Items = new ArrayList<ArrayList<ClassLines>>();
    private OptionsPickerView pvOptions;
    private View vMasker;
    private TextView selectBus;
    private TitleBar mTitle;
    private LineInfoResult mLineInfoResult;
    private carInfoDetail mCarInfoDetail;
    private Button orderConfirm;
    private ListView mListView;
    private RecommendAdapter mRecommedAdapter;

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
        orderConfirm = (Button) view.findViewById(R.id.bt_confirm);
        pvOptions = new OptionsPickerView(view.getContext());
        mTitle = (TitleBar) view.findViewById(R.id.order_titlebar);
        mListView = (ListView) view.findViewById(R.id.recommend_listview);
        mTitle.showBackButton(false);
    }

    private  void initData() {

        mTitle.setTitleText(R.string.order_title);
        mRecommedAdapter = new RecommendAdapter();
        mListView.setAdapter(mRecommedAdapter);
        List<ClassLines> recommendCars = new ArrayList<ClassLines>();
        recommendCars.add(new ClassLines("12:12", "线路1"));
        recommendCars.add(new ClassLines("11:12", "线路1"));
        mRecommedAdapter.setData(recommendCars, mActivity);
//        options1Items.add(new Line(0,"线路一"));
//        options1Items.add(new Line(1,"线路二"));
//        options1Items.add(new Line(3,"线路三"));
//        ArrayList<String> options2Items_01=new ArrayList<String>();
//        options2Items_01.add("07:10");
//        options2Items_01.add("12:10");
//        options2Items_01.add("18:10");
//        ArrayList<String> options2Items_02=new ArrayList<String>();
//        options2Items_02.add("08:10");
//        options2Items_02.add("12:20");
//        ArrayList<String> options2Items_03=new ArrayList<String>();
//        options2Items_03.add("09:10");
//        options2Items_03.add("11:10");
//        options2Items_03.add("15:10");
//        options2Items.add(options2Items_01);
//        options2Items.add(options2Items_02);
//        options2Items.add(options2Items_03);

        requestLineData();

        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {

            @Override
            public void onOptionsSelect(int options1, int option2, int option3) {
                //返回的分别是三个级别的选中位置
                if (options1Items.size() > options1 && options2Items.get(options1).size() > option2) {
                    String tx = options1Items.get(options1).getPickerViewText()
                            + options2Items.get(options1).get(option2).getLineTime();
                    Log.d("OrderFragment", tx);
                    int lineId = options2Items.get(options1).get(option2).getLineId();
                    User cu = StdApplication.getCurrentUser();
                    UserHttper.backgroundRequestOrderQuery(lineId + "", cu.getName(), new TelResponseListener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }

                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                }
                vMasker.setVisibility(View.GONE);
            }
        });

        selectBus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });
        orderConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    public void requestLineData() {
        UserHttper.backgroundRequestLineData(new TelResponseListener<JSONObject>() {
           // ArrayList<String> options2Items_01;
            @Override
            public void onResponse(JSONObject response) {
                Log.e("LOGString", response.toString());
                mLineInfoResult = new Gson().fromJson(response.toString(), LineInfoResult.class);
                if (null != mLineInfoResult && mLineInfoResult.getResult().equals("1")) {
                    List<LineInfo> lineInfos= mLineInfoResult.getLineInfo();
                    for(int i = 0; i < lineInfos.size(); i++) {
                        String lineNum = lineInfos.get(i).getLineNum();
                        options1Items.add(new Line(i, "线路" + lineNum));
                        final ArrayList<ClassLines>  options2Items_01 = new ArrayList<ClassLines>();
                        UserHttper.backgroundRequestarInfoDetail(lineNum, new TelResponseListener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Log.e("LOGString", "111" + response.toString());
                                mCarInfoDetail = new Gson().fromJson(response.toString(), carInfoDetail.class);
                                if (null != mCarInfoDetail && mCarInfoDetail.getResult().equals("1")) {
                                    int count = mCarInfoDetail.getClassLines().size();
                                    for (int i = 0; i < count; i++) {
                                        Log.e("LOGString", "22" + mCarInfoDetail.getClassLines().get(i).getLineTime());
                                        options2Items_01.add(mCarInfoDetail.getClassLines().get(i));
                                    }
                                } else {
                                    Log.e("LOGString", "error--" + mCarInfoDetail.toString());
                                }
                            }

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.e("LOGString", "+++++" + error.toString());
                            }
                        });
                        options2Items.add(options2Items_01);
                    }
                } else {
                    Log.e("LOGString", "error--" + mLineInfoResult.toString());
                }

                pvOptions.setPicker(options1Items, options2Items, true);
                //设置选择的三级单位
                //pwOptions.setLabels("省", "市", "区");
                pvOptions.setTitle("选择班车");
                pvOptions.setCyclic(false, false, false);
                //设置默认选中的三级项目
                pvOptions.setSelectOptions(0, 0);
            }

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("LOGString", "error--" + error.toString());
            }
        });
    }


}
