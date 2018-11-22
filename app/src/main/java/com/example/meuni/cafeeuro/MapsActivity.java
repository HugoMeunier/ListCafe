package com.example.meuni.cafeeuro;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        map.setIndoorEnabled(true);
        map.setTrafficEnabled(true);
        map.setBuildingsEnabled(true);

        // Add a marker in Sydney and move the camera
        LatLng gardanneLocation = new LatLng(43.4445612, 5.4797211);
        map.addMarker(new MarkerOptions().position(gardanneLocation).title("Mines St Etienne - Georges Charpak Provence"));
        map.moveCamera(CameraUpdateFactory.newLatLng(gardanneLocation));
        map.moveCamera(CameraUpdateFactory.zoomTo(17));
    }
}