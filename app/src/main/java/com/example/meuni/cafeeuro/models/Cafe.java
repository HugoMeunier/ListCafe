package com.example.meuni.cafeeuro.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.io.Serializable;

@Entity(tableName = "cafe")
public class Cafe implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nom_du_cafe;
    private float price;


    public Cafe(){
    }

    @Ignore
    public Cafe(float price, String nom_du_cafe) {
        this.price = price;
        this.nom_du_cafe = nom_du_cafe;

    }

    @Override
    public String toString() {
        return "Cafe{" +
                "price=" + price +
                ", nom_du_cafe='" + nom_du_cafe + '\'' +
                '}';
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setNom_du_cafe(String nom_du_cafe) {
        this.nom_du_cafe = nom_du_cafe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom_du_cafe() {
        return nom_du_cafe;
    }

    public float getPrice() {
        return price;
    }
}
