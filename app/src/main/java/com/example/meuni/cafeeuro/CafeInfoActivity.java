package com.example.meuni.cafeeuro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.meuni.cafeeuro.models.Cafe;

public class CafeInfoActivity extends AppCompatActivity {

    private Cafe cafe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        cafe = (Cafe) extras.getSerializable("cafemarker");

        cafe.getFields().getArrondissement();



        setContentView(R.layout.activity_cafeinfo);



        TextView nameTextView = findViewById(R.id.name);
        nameTextView.setText(cafe.getFields().getNom_du_cafe());


        TextView adresseTextView = findViewById(R.id.adresse);
        adresseTextView.setText(cafe.getFields().getAdresse());

        TextView arrondissementTextView = findViewById(R.id.arrondissement);
        arrondissementTextView.setText(Integer.toString(cafe.getFields().getArrondissement()));

        TextView prixTextView = findViewById(R.id.prix);
        prixTextView.setText(cafe.getFields().getPrix_compotoire() + "€");


        TextView linkTextView = findViewById(R.id.lien);
        linkTextView.setText("Coming soon !!");




    }
}


