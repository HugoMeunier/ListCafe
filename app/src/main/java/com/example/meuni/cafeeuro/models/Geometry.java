package com.example.meuni.cafeeuro.models;

import android.arch.persistence.room.Ignore;

import java.util.ArrayList;
import java.util.List;

public class Geometry {

    private List<Integer> coordinates;
    private String type;



    public Geometry() {
        this.coordinates = new ArrayList<>();
        this.coordinates.add(1);
        this.coordinates.add(2);
        this.type = "type";
    }

    @Override
    public String toString() {
        return "Geometry{" +
                "coordinates=" + coordinates +
                ", type='" + type + '\'' +
                '}';
    }

    public List<Integer> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Integer> coordinates) {
        this.coordinates = coordinates;
    }

    @Ignore
    public Geometry(List<Integer> coordinates, String point) {

        this.coordinates = coordinates;
        this.type = point;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
