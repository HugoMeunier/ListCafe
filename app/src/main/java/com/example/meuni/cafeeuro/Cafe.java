package com.example.meuni.cafeeuro;

public class Cafe {

    private double latX;
    private double longY;
    private String nomCafe ;

    public String getFull() {
        return full;
    }

    public void setFull(String full) {
        this.full = full;
    }

    private String full ;

    public double getLongY() {
        return longY;
    }

    public void setLongY(double longY) {
        this.longY = longY;
    }



    public String getNomCafe() {
        return nomCafe;
    }

    public void setNomCafe(String nomCafe) {
        this.nomCafe = nomCafe;
    }



    public double getLatX() {
        return latX;
    }

    public void setLatX(double latX) {
        this.latX = latX;
    }

    public Cafe(String a, String b)
    {

        this.full = b;
        this.nomCafe = a;

    }

}
