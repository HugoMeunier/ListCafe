package com.example.meuni.cafeeuro;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CafeViewHolder extends RecyclerView.ViewHolder {
    public TextView cafeName;
    public TextView cafePlace;
    public TextView cafePrice;
    public ImageView cafeImage;

    public CafeViewHolder(View rootView) {
        super(rootView);
        this.cafeName = rootView.findViewById(R.id.TextCafeName);
        this.cafePlace = rootView.findViewById(R.id.TextCafePlace);
        this.cafePrice = rootView.findViewById(R.id.TextCafePrice);
        this.cafeImage = rootView.findViewById(R.id.imageCoffee);
    }
}
