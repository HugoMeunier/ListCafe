package com.example.meuni.cafeeuro;

import android.content.Intent;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.meuni.cafeeuro.models.Cafe;
import com.example.meuni.cafeeuro.models.ListCafe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;

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
    TextView TextResult;
    ListCafe listCafeFireBase;

    private ListFragment listFragment;
    private InfoFragment infoFragment;
    private MapFragment mapFragment;
    private ArrayList<Cafe> cafes = new ArrayList<>();



    //communication avec FireBase
    FirebaseDatabase database;
    private static final String PATH = "";
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

        TextResult = findViewById(R.id.TextEditResult);

        btnList.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
        btnMap.setVisibility(View.VISIBLE);
        btnMap.setVisibility(View.VISIBLE);

        //communication avec FireBase
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(PATH);

        //recupere la liste de tout les cafes sur firebase
        listCafeFireBase = new ListCafe();
        database.getReference(PATH).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCafeFireBase = dataSnapshot.getValue(ListCafe.class);
                TextResult.setText(listCafeFireBase.toString());
                for (int i =0; i<listCafeFireBase.getListCafe().size(); i++){
                    cafes.add(listCafeFireBase.getListCafe().get(i));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //   taskSource.setError(firebaseError.toException());
            }
        });


        //gestion de l'affichage des cafes
        listFragment = listFragment.newInstance(cafes);
        addFragment(listFragment);


        //action des boutons
        btnList.setOnClickListener((view) -> {
            replaceFragment(listFragment);
        });

        btnInfo.setOnClickListener((view) -> {
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            infoFragment = new InfoFragment();
            replaceFragment(infoFragment);
        });



        btnMap.setOnClickListener((view) -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            //mapFragment = new MapFragment();
            //replaceFragment(mapFragment);
        });


        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ListCafe> cafea = new GenericTypeIndicator<ListCafe>() {};
                ListCafe listCafeFireBase = dataSnapshot.getValue(cafea);
                if (listCafeFireBase == null) {
                    Toast.makeText(MainActivity.this, "null ", Toast.LENGTH_LONG).show();
                } else {
                    TextResult.setText(listCafeFireBase.toString());
                    for (int i =0; i<listCafeFireBase.getListCafe().size(); i++){
                        cafes.add(listCafeFireBase.getListCafe().get(i));
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
            }
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
