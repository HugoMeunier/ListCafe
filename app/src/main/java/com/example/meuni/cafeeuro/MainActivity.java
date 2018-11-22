package com.example.meuni.cafeeuro;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnList;
    Button btnInfo;
    Button btnMap;
    private ListFragment listFragment;
    private InfoFragment infoFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            mapFragment = new MapFragment();
            replaceFragment(mapFragment);
        });

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
