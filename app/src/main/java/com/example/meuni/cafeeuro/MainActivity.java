package com.example.meuni.cafeeuro;

import android.os.Bundle;
import android.app.ActionBar;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity  {

    Button btnList;
    Button btnInfo;
    Button btnMap;
    TextView TextResult;
    ListCafe listCafeFireBase;

    private ListFragment listFragment;
    private InfoFragment infoFragment;
    private MapsFragment mapsFragment;
    private ArrayList<Cafe> cafes = new ArrayList<Cafe>();



    //communication avec FireBase
    FirebaseDatabase database;
    private static final String PATH = "";

    //set action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //gere les actions de l'action bar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Toast.makeText(MainActivity.this, "action settings", Toast.LENGTH_LONG).show();
                return true;
            case R.id.action_favorite:
                Toast.makeText(MainActivity.this, "action favorite", Toast.LENGTH_LONG).show();
                // User chose the "Favorite" action, mark the current item as a favorite...
                //TODO : rafraichir les données en récupérant les données sur firebase
                return true;
            default: // If we got here, the user's action was not recognized.
                Toast.makeText(MainActivity.this, "default", Toast.LENGTH_LONG).show();
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnList = findViewById(R.id.buttonList);
        btnInfo = findViewById(R.id.buttonInfo);
        btnMap = findViewById(R.id.buttonMap);

        TextResult = findViewById(R.id.TextEditResult);

        btnList.setVisibility(View.VISIBLE);
        btnInfo.setVisibility(View.VISIBLE);
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
        //listFragment = listFragment.newInstance(cafes);
        //addFragment(listFragment);

        //action des boutons
        btnList.setOnClickListener((view) -> {
            listFragment = listFragment.newInstance(cafes);
            replaceFragment(listFragment);
        });

        btnInfo.setOnClickListener((view) -> {
            //listFragment.setArguments(getIntent().getExtras()); //donne tout ce qu'il faut à l'argument au cas où
            infoFragment = new InfoFragment();
            replaceFragment(infoFragment);
        });


        btnMap.setOnClickListener((view) -> {
            mapsFragment = new MapsFragment();
            Bundle args = new Bundle();
            args.putSerializable("tagCafe",cafes);
            mapsFragment.setArguments(args);


            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.frameLayout, mapsFragment);
            fragmentTransaction.addToBackStack("tagCafe");
            fragmentTransaction.commit();
            fragmentTransaction.show(mapsFragment);
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
