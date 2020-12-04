package com.example.linlinmap.activity.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.annotation.ContentView;
import androidx.annotation.NonNull;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.linlinmap.R;


public class DashboardFragment extends Fragment {

    private MapView mapView;
    private ImageView imageView;
    private AMap aMap;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        mapView = root.findViewById(R.id.map);

        if(aMap == null){
            //初始化
            aMap = mapView.getMap();
        }
        // 显示实时交通状况
        aMap.setTrafficEnabled(true);
        //地图模式可选类型：MAP_TYPE_NORMAL,MAP_TYPE_SATELLITE,MAP_TYPE_NIGHT
        aMap.setMapType(AMap.MAP_TYPE_NORMAL);// 常规地图模式
//        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 卫星地图模式
//        aMap.setMapType(AMap.MAP_TYPE_NIGHT);// 夜晚地图模式
//        设置地图语言
        aMap.setMapLanguage(AMap.CHINESE);
//        默认经纬度 宜昌
        LatLng latLng = new LatLng(30.7,111.28);
//        放大倍数 10
        aMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
        //定位
        MyLocationStyle myLocationStyle = new MyLocationStyle();
        myLocationStyle.showMyLocation(true);
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

        mapView.onCreate(savedInstanceState);
        return root;
    }
    @Override
    public void onPause() {
        super.onPause();
    }

}