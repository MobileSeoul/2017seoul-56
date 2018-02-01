package com.funnytoday.ddoniddoni.seoulzerobottle.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;

import com.funnytoday.ddoniddoni.seoulzerobottle.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by Owner on 2017-10-09.
 */

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    private String location;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_place);
        location = getIntent().getStringExtra("LOCATION");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        if (location.equals("공덕")) {
            LatLng address = new LatLng(37.5445940, 126.9507150);
            googleMap.addMarker(new MarkerOptions().position(address).title("롯데슈퍼 공덕점!").snippet("서울특별시 마포구 공덕동 423-3"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("구로")) {
            LatLng address = new LatLng(37.4981800, 126.8726480);
            googleMap.addMarker(new MarkerOptions().position(address).title("롯데마트 구로점!").snippet("서울특별시 구로구 구로2동 경인로 482"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("영등포")) {
            LatLng address = new LatLng(37.5181780, 126.8958960);
            googleMap.addMarker(new MarkerOptions().position(address).title("홈플러스 영등포점!").snippet("서울특별시 영등포구 문래동 당산로 42"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("성수")) {
            LatLng address = new LatLng(37.5396580, 127.0533800);
            googleMap.addMarker(new MarkerOptions().position(address).title("이마트 성수점!").snippet("서울특별시 성동구 성수2가1동 뚝섬로 379"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        } else if (location.equals("목동")) {
            LatLng address = new LatLng(37.6138090, 127.0775140);
            googleMap.addMarker(new MarkerOptions().position(address).title("이마트 목동점!").snippet("서울특별시 양천구 목1동 962"));
            CameraPosition cp = new CameraPosition.Builder().target((address)).zoom(15).build();
            googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cp));
        }

    }
}
