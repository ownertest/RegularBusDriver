package com.tel.china.regularbusdiver.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.tel.china.regularbusdiver.R;
import com.tel.china.regularbusdiver.system.StdApplication;

public class LocationFragment extends BaseMainFragment {

    private MapView mMapView;
    private BaiduMap mBaiduMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SDKInitializer.initialize(mActivity);
        return LayoutInflater.from(mActivity).inflate(R.layout.location_fragment, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initView(view);
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view) {
        mMapView = (MapView) view.findViewById(R.id.bmapView);
        mBaiduMap = mMapView.getMap();
        //普通地图
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
    }

    private void addCar() {
        //定义Maker坐标点
        LatLng point = new LatLng(39.963175, 116.400244);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.drawable.mark);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .position(point)
                .icon(bitmap);
        //在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }
    @Override
    public void onPause() {
        if (null != mMapView) {
            mMapView.onPause();
        }
        super.onPause();
    }

    @Override
    public void onStart() {
        if (null != mMapView) {
            mMapView.onResume();
        }
        super.onStart();
    }

    @Override
    public void onDestroy() {
        if (null != mMapView) {
            mMapView.onDestroy();
        }
        super.onDestroy();
    }
}
