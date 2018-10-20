package com.team.bulkan.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.bulkan.AppController;
import com.team.bulkan.MainActivity;
import com.team.bulkan.R;
import com.team.bulkan.activity.VolcanoInfoActivity;
import com.team.bulkan.model.Volcano;
import com.team.bulkan.network.Consts;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap gMap;
    private LatLng currentPosition;

    //MULTIPLE MARKERS
    private MarkerOptions options = new MarkerOptions();
    private List<Volcano> volcanoList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        volcanoList = AppController.getInstance().getDbAccess().getVolcanoes();

        //FOR DEMO
        currentPosition = new LatLng(Double.valueOf(Consts.DUMMY_LAT), Double.valueOf(Consts.DUMMY_LONG));

        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(HomeFragment.this);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View v){
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;

        //USER POSITION
        googleMap.addMarker(new MarkerOptions().position(currentPosition).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location)
        ).title("I'm here"));

        //VOLCANOES
        for (Volcano v: volcanoList) {
            options.position(new LatLng(v.getGeoLat(), v.getGeoLong()));
            options.title(v.getVolcanoName());
            options.snippet(String.valueOf(v.getGeoLat()) + "," + String.valueOf(v.getGeoLong()));
            //CHEAT ACTIVE; Mayon Volcano id 26
            if(v.getVolcanoId() == 26){
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_active_volcano));
            }else{
                options.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_inactive_volcano));
            }
            googleMap.addMarker(options);
            googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                @Override
                public boolean onMarkerClick(Marker marker) {
                    //currentPosition is user position
                    if (marker.getPosition()!= currentPosition) {
                        //OPEN VOLCANO INFO
                        if(marker.getTitle().equals("Mayon")){
                            Intent showInfo = new Intent(MainActivity.getInstance(), VolcanoInfoActivity.class);
                            showInfo.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            showInfo.putExtra(VolcanoInfoActivity.VOLCANO_ID, 26);
                            startActivity(showInfo);
                        }else{
                            Toast.makeText(MainActivity.getInstance(), "VOLCANO: " + marker.getTitle() +"~"+ marker.getPosition(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    return true;
                }
            });
        }

        //gMap.moveCamera(CameraUpdateFactory.newLatLng(currentPosition));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentPosition, 9));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mapFragment.onDestroy();

    }


}
