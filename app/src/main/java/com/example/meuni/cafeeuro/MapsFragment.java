package com.example.meuni.cafeeuro;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

    /** Called when the user clicks a marker. */
    @Override
    public boolean onMarkerClick(final Marker marker) {

        // Retrieve the data from the marker.
        String c = marker.getTitle();
        // Check if a click count was set, then display the click count.
        if (c != null) {
            Log.d("c", c);
            System.out.println(c);
            //faudrait mettre le retour vers le main activity avec l'id du café
        }
        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false;
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
                map.addMarker(new MarkerOptions().position(location).title(caf.getFields().getNom_du_cafe()));
                System.out.println("cafe :");
                System.out.println(caf.getFields().getNom_du_cafe());
                System.out.println(caf.getFields().getGeoloc().get(0));
                System.out.println(caf.getFields().getGeoloc().get(1));
            }
        }

        map.moveCamera(CameraUpdateFactory.newLatLng(location));
        map.moveCamera(CameraUpdateFactory.zoomTo(12));
        map.setOnMarkerClickListener(this);
    }


}


