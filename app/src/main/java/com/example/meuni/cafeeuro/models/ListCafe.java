package com.example.meuni.cafeeuro.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListCafe {

    @SerializedName("listCafe")
    @Expose
    private List<Cafe> listCafe;

    public ListCafe(){
        listCafe = new ArrayList<>();
    }

    public List<Cafe> getListCafe() {
        return listCafe;
    }

    public void setListCafe(List<Cafe> listCafe) {
        this.listCafe = listCafe;
    }

    public void addCafe(Cafe cafe){listCafe.add(cafe);}

    @Override
    public String toString() {
        return "ListCafe{" +
                "listCafe=" + listCafe +
                '}';
    }
}
