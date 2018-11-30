package com.example.meuni.cafeeuro.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.meuni.cafeeuro.MapsFragmentListener;
import com.example.meuni.cafeeuro.R;
import com.example.meuni.cafeeuro.fragments.InfoFragment;
import com.example.meuni.cafeeuro.fragments.ListFragment;
import com.example.meuni.cafeeuro.fragments.MapsFragment;
import com.example.meuni.cafeeuro.models.Cafe;
import com.example.meuni.cafeeuro.models.ListCafe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MapsFragmentListener {

    Button btnList;
    Button btnInfo;
    Button btnMap;
    ListCafe listCafeFireBase;
    private ListFragment listFragment;
    private InfoFragment infoFragment;
    private MapsFragment mapsFragment;
    private ArrayList<Cafe> cafes = new ArrayList<Cafe>();

    //communication avec FireBase
    FirebaseDatabase database;
    private static final String PATH = "";


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

        //communication avec FireBase
        database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference(PATH);

        //recupere la liste de tout les cafes sur firebase
        //url base de donnée d'origine : https://www.data.gouv.fr/fr/datasets/liste-des-cafes-a-un-euro-prs/?fbclid=IwAR1vehaD78ypaf-j0tR_T5L7sJ5IC8DYI5KK9vLHXgSZDXZVV1BbmvMUh6U
        //url firebase : https://console.firebase.google.com/project/listcafedatabase/overview
        //Nous avons importé sur firebase le fichier JSON récupéré sur le site du gouvernement.
        listCafeFireBase = new ListCafe();
        database.getReference(PATH).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCafeFireBase = dataSnapshot.getValue(ListCafe.class);
                for (int i = 0; i < listCafeFireBase.getListCafe().size(); i++) {
                    cafes.add(listCafeFireBase.getListCafe().get(i));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Failed to read value", Toast.LENGTH_LONG).show();
            }
        });

        btnList.setOnClickListener((view) -> { //affiche la liste de cafe
            listFragment = listFragment.newInstance(cafes);
            replaceFragment(listFragment);
        });

        btnInfo.setOnClickListener((view) -> { //affiche l'info de l'app
            infoFragment = new InfoFragment();
            replaceFragment(infoFragment);
        });

        btnMap.setOnClickListener((view) -> {//affiche la carte des cafes
            mapsFragment = new MapsFragment();
            Bundle args = new Bundle();
            args.putSerializable("tagCafe", cafes);
            mapsFragment.setArguments(args);

            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, mapsFragment);
            fragmentTransaction.addToBackStack("tagCafe");
            fragmentTransaction.commit();
            fragmentTransaction.show(mapsFragment);
        });

        myRef.addValueEventListener(new ValueEventListener() { //acualisation des données firebase dès changements
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ListCafe> cafea = new GenericTypeIndicator<ListCafe>() {
                };
                ListCafe listCafeFireBase = dataSnapshot.getValue(cafea);
                if (listCafeFireBase == null) {
                    Toast.makeText(MainActivity.this, "null ", Toast.LENGTH_LONG).show();
                } else {
                    for (int i = 0; i < listCafeFireBase.getListCafe().size(); i++) {
                        cafes.add(listCafeFireBase.getListCafe().get(i));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to update value", Toast.LENGTH_LONG).show();
            }
        });
    }

    //set action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //gere les actions de l'action bar
    //actualise les données à la demande. Mais cela est peut utile car on utilise déjà une base de donnée qui s'actualise automatiquement
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Toast.makeText(MainActivity.this, "actualisation de la liste des cafés", Toast.LENGTH_LONG).show();
                database.getReference(PATH).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        listCafeFireBase = dataSnapshot.getValue(ListCafe.class);
                        for (int i = 0; i < listCafeFireBase.getListCafe().size(); i++) {
                            cafes.add(listCafeFireBase.getListCafe().get(i));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(MainActivity.this, "Failed to read value", Toast.LENGTH_LONG).show();
                    }
                });
                return true;
            default: // If we got here, the user's action was not recognized.
                Toast.makeText(MainActivity.this, "default", Toast.LENGTH_LONG).show();
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
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

    //lance l'activité affichant en détail les données d'un café
    @Override
    public void onCafeSelected(Cafe cafe) {
        Intent intent = new Intent(MainActivity.this, CafeInfoActivity.class);
        intent.putExtra("cafemarker", cafe);
        MainActivity.this.startActivity(intent);
    }
}
