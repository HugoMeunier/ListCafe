package com.example.meuni.cafeeuro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {


    private static final String BASE_URL = "https://www.data.gouv.fr/fr/datasets/r/700734c8-1cbd-4d26-8f95-da045be62f08";
    private RequestQueue queue;
    Button btnList;
    Button btnInfo;
    Button btnMap;
    private ListFragment listFragment;
    private InfoFragment infoFragment;
    MapsFragment mapFragment;
    private ArrayList<Cafe> cafeArrayList= new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        setContentView(R.layout.activity_main);


        queue = Volley.newRequestQueue(this);

        loadCafeFromServer();

        btnList = findViewById(R.id.buttonList);
        btnInfo = findViewById(R.id.buttonInfo);
        btnMap = findViewById(R.id.buttonMap);

        btnList.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        btnMap.setVisibility(View.VISIBLE);

        listFragment = new ListFragment();

        //cree dès le départ le fragment dans l'activity sans rien faire
//        listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
        //addFragment(listFragment);

        btnList.setOnClickListener((view) -> {
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            listFragment = new ListFragment();
            replaceFragment(listFragment);
        });

        btnInfo.setOnClickListener((view) -> {
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            infoFragment = new InfoFragment();
            replaceFragment(infoFragment);
        });


        btnMap.setOnClickListener((view) -> {
            mapFragment = new MapsFragment();
            Bundle args = new Bundle();
            mapFragment.setArguments(args);
            replaceFragment(mapFragment);

        });

    }


    private void loadCafeFromServer() {
        String url = BASE_URL;

        GsonRequest getCafeRequest = new GsonRequest<Cafe>(url,
                Cafe.class,
                null,
                cafe -> {
                    if (cafe != null) {
                        //
                        cafeArrayList.add(cafe);

                    }
                },


                error -> Toast.makeText(MainActivity.this, "Unable to load cafe", Toast.LENGTH_LONG).show());

        queue.add(getCafeRequest);
    }

    // Replace current Fragment with the destination Fragment.
    public void addFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
        fragmentTransaction.show(fragment);
    }


    public void replaceFragment(Fragment destFragment) {
        // First get FragmentManager object.
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        // Begin Fragment transaction.
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        // Replace the layout holder with the required Fragment object.
        fragmentTransaction.replace(R.id.frameLayout, destFragment);
        // Commit the Fragment replace action.
        fragmentTransaction.commit();

    }

}
