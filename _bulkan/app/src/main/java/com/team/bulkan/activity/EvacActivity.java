package com.team.bulkan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.util.DirectionConverter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.team.bulkan.R;
import com.team.bulkan.network.Consts;

import java.util.ArrayList;

public class EvacActivity extends AppCompatActivity implements OnMapReadyCallback, View.OnClickListener, DirectionCallback {

    private GoogleMap googleMap;
    private LatLng origin = new LatLng(Double.valueOf(Consts.DUMMY_LAT), Double.valueOf(Consts.DUMMY_LONG));
    private LatLng destination = new LatLng(Double.valueOf(Consts.DUMMY_END_LAT), Double.valueOf(Consts.DUMMY_END_LONG));
    boolean plotted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evac);
        ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMapAsync(this);

        GoogleDirection.withServerKey(getResources().getString(R.string.google_maps_key))
                .from(origin)
                .to(destination)
                .transportMode(TransportMode.DRIVING)
                .execute(this);

    }


    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDirectionSuccess(Direction direction, String rawBody) {
        if (direction.isOK()) {
            if(!plotted){
                plotted = true;
                Route route = direction.getRouteList().get(0);
                /*googleMap.addMarker(new MarkerOptions().position(origin));
                googleMap.addMarker(new MarkerOptions().position(destination));*/

                googleMap.addMarker(new MarkerOptions().position(origin).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location)
                ).title("I'm here"));

                //destination
                googleMap.addMarker(new MarkerOptions().position(destination).icon(
                        BitmapDescriptorFactory.fromResource(R.drawable.ic_destination)
                ).title("I'm here"));


                ArrayList<LatLng> directionPositionList = route.getLegList().get(0).getDirectionPoint();
                googleMap.addPolyline(DirectionConverter.createPolyline(this, directionPositionList, 5, Color.RED));
                setCameraWithCoordinationBounds(route);

               // btnRequestDirection.setVisibility(View.GONE);
            }

        } else {
            Toast.makeText(this, direction.getStatus(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDirectionFailure(Throwable t) {
        Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
        //USER POSITION
        googleMap.addMarker(new MarkerOptions().position(origin).icon(
                BitmapDescriptorFactory.fromResource(R.drawable.ic_my_location)
        ).title("I'm here"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(origin, 9));
    }


    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
    }

}
