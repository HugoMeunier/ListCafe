package com.example.meuni.cafeeuro;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.meuni.cafeeuro.models.Cafe;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CafeAdapter extends RecyclerView.Adapter<CafeViewHolder> {

    private ArrayList<Cafe> cafes;
    private Context context;


    public CafeAdapter(ArrayList<Cafe> cafes) {
        this.cafes = cafes;
    }

    @NonNull
    @Override //le viewholder utilise le rom_bottle pour se remplir
    public CafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cafe, parent, false);
        context = parent.getContext();
        return new CafeViewHolder(row);
    }

    @Override //stocke les données utiles (nom et prix) dans le viewHolder
    public void onBindViewHolder(@NonNull CafeViewHolder holder, int position) {
        Cafe iconToDisplay = this.cafes.get(position);
        holder.cafeImage.setVisibility(View.VISIBLE);
        holder.cafeName.setText(iconToDisplay.getFields().getNom_du_cafe());
        holder.cafePlace.setText(valueOf(iconToDisplay.getFields().getArrondissement()));
        holder.cafePrice.setText(String.format("%s €", valueOf(iconToDisplay.getFields().getPrix_compotoire())));
       // Picasso.with(context).load("http://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png").into(holder.cafeImage.setImageDrawable());
       // holder.cafeImage.setVisibility(View.VISIBLE);

    }


    @Override //compte pour gerer le nombre a afficher
    public int getItemCount() {
        return this.cafes.size();
    }


}


