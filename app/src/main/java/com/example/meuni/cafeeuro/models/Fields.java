package com.example.meuni.cafeeuro.models;

import android.arch.persistence.room.Ignore;

import java.util.ArrayList;
import java.util.List;


public class Fields {

    private String adresse;
    private int arrondissement;
    private String date;
    private List<Double> geoloc;
    private String nom_du_cafe;
    private String prix_compotoire;
    private String prix_salle;
    private String prix_terasse;

    public Fields() {
        this.adresse = "adresse";
        this.arrondissement = 1;
        this.date = "date";
        this.geoloc = new ArrayList<>();
        this.geoloc.add(1.0);
        this.geoloc.add(2.0);
        this.nom_du_cafe = "nom_du_cafe";
        this.prix_compotoire = "1";
        this.prix_salle = "1";
        this.prix_terasse = "1";
    }

    public String getPrix_compotoire() {
        return prix_compotoire;
    }

    public void setPrix_compotoire(String prix_compotoire) {
        this.prix_compotoire = prix_compotoire;
    }

    public String getPrix_salle() {
        return prix_salle;
    }

    public void setPrix_salle(String prix_salle) {
        this.prix_salle = prix_salle;
    }

    public String getPrix_terasse() {
        return prix_terasse;
    }

    public void setPrix_terasse(String prix_terasse) {
        this.prix_terasse = prix_terasse;
    }

    @Override
    public String toString() {
        return "Fields{" +
                "adresse='" + adresse + '\'' +
                ", arrondissement=" + arrondissement +
                ", date='" + date + '\'' +
                ", geoloc=" + geoloc +
                ", nom_du_cafe='" + nom_du_cafe + '\'' +
                ", prix_compotoire='" + prix_compotoire + '\'' +
                ", prix_salle='" + prix_salle + '\'' +
                ", prix_terasse='" + prix_terasse + '\'' +
                '}';
    }

    @Ignore
    public Fields(String adresse, int arrondissement, String date, List<Double> geoloc, String nom_du_cafe, String prix_compotoire, String prix_salle, String prix_terasse) {
        this.adresse = adresse;
        this.arrondissement = arrondissement;
        this.date = date;
        this.geoloc = geoloc;
        this.nom_du_cafe = nom_du_cafe;
        this.prix_compotoire = prix_compotoire;
        this.prix_salle = prix_salle;
        this.prix_terasse = prix_terasse;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getArrondissement() {
        return arrondissement;
    }

    public void setArrondissement(int arrondissement) {
        this.arrondissement = arrondissement;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Double> getGeoloc() {
        return geoloc;
    }

    public void setGeoloc(List<Double> geoloc) {
        this.geoloc = geoloc;
    }

    public String getNom_du_cafe() {
        return nom_du_cafe;
    }

    public void setNom_du_cafe(String nom_du_cafe) {
        this.nom_du_cafe = nom_du_cafe;
    }


}
