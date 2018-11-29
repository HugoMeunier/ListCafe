package com.example.meuni.cafeeuro;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.meuni.cafeeuro.models.Cafe;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


import java.util.ArrayList;

public class MapsFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    private MapView mapView;
    private GoogleMap map;
    private MainActivity main;
    private ArrayList<Cafe> cafes = new ArrayList<Cafe>();
    private ArrayList<Marker> markers = new ArrayList<Marker>();

    public MapsFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = (MainActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_maps, container, false);
        mapView = (MapView) viewGroup.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);

        mapView.onResume();

        Bundle args = getArguments();

        cafes = (ArrayList<Cafe>) args.getSerializable("tagCafe");

        try {
            MapsInitializer.initialize(main.getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
        mapView.getMapAsync(this);

        return viewGroup;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {

        map = googleMap;
        map.setIndoorEnabled(true);
        map.setTrafficEnabled(true);
        map.setBuildingsEnabled(true);
        LatLng location = new LatLng(43.4445612, 5.4797211);

        Cafe caf;
        int n = cafes.size();
        // System.out.println("azerpijazerpojnafpjanevpajinpaejribn");
        System.out.println(n);

        for (int i = 0; i < n; i++) {
            caf = cafes.get(i);
            if ((caf.getFields().getGeoloc().get(0) != null) || (caf.getFields().getGeoloc().get(1) != null)) {
                location = new LatLng(caf.getFields().getGeoloc().get(0), caf.getFields().getGeoloc().get(1));
                markers.add(map.addMarker(new MarkerOptions().position(location).title(caf.getFields().getNom_du_cafe())));
                /*System.out.println("cafe :");
                System.out.println(i);
                System.out.println(Integer.parseInt((markers.get(i).getId()).substring(1)));
                System.out.println("cafe :");
                System.out.println(caf.getFields().getNom_du_cafe());
                System.out.println(caf.getFields().getGeoloc().get(0));
                System.out.println(caf.getFields().getGeoloc().get(1));*/
            }
        }

        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.moveCamera(CameraUpdateFactory.zoomTo(12));
        map.setOnMarkerClickListener(this);
    }
    @Override
    public boolean onMarkerClick(final Marker marker) {

        //launch a new activity

        Intent intent = new Intent(getActivity(),CafeInfoActivity.class);

        intent.putExtra("cafemarker",cafes.get(Integer.parseInt((marker.getId().substring(1)))));

        startActivity(intent);

        return false;
    }
}




