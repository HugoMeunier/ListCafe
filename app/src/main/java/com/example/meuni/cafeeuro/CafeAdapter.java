package com.example.meuni.cafeeuro;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.meuni.cafeeuro.models.Cafe;

import java.util.ArrayList;

import static java.lang.String.valueOf;

public class CafeAdapter extends RecyclerView.Adapter<CafeViewHolder> {

    private ArrayList<Cafe> cafes;

    public CafeAdapter(ArrayList<Cafe> cafes) {
        this.cafes = cafes;
    }

    @NonNull
    @Override //le viewholder utilise le rom_bottle pour se remplir
    public CafeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cafe, parent, false);
        return new CafeViewHolder(row);
    }

    @Override //stocke les données utiles (nom et prix) dans le viewHolder
    public void onBindViewHolder(@NonNull CafeViewHolder holder, int position) {
        Cafe iconToDisplay = this.cafes.get(position);
        holder.cafeImage.setVisibility(View.VISIBLE);
        holder.cafeName.setText(iconToDisplay.getFields().getNom_du_cafe());
        holder.cafePlace.setText(valueOf(iconToDisplay.getFields().getArrondissement()));
        holder.cafePrice.setText(String.format("%s €", valueOf(iconToDisplay.getFields().getPrix_compotoire())));
    }

    @Override //compte pour gerer le nombre a afficher
    public int getItemCount() {
        return this.cafes.size();
    }
}

