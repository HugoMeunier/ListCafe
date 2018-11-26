package com.example.meuni.cafeeuro.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "cafe")
public class Cafe implements Serializable {

    private String datasetid;
    @Ignore
    @SerializedName("fields")
    private Fields fields;
    @Ignore
    @SerializedName("geometry")
    private Geometry geometry;
    private String record_timestamp;
    @PrimaryKey
    @NonNull
    private String recordid;

    public Cafe() {
        this.fields = new Fields();
        this.geometry = new Geometry();
        this.datasetid = "datasetid";
        this.record_timestamp = "record_timestamp";
        this.recordid = "recordid";
    }

    public Cafe(String datasetid, Fields fields, Geometry geometry, String record_timestamp, String recordid) {
        this.datasetid = datasetid;
        this.fields = fields;
        this.geometry = geometry;
        this.record_timestamp = record_timestamp;
        this.recordid = recordid;
    }


    public String getDatasetid() {
        return datasetid;
    }

    public void setDatasetid(String datasetid) {
        this.datasetid = datasetid;
    }

    public Fields getFields() {
        return fields;
    }

    public void setFields(Fields fields) {
        this.fields = fields;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getRecord_timestamp() {
        return record_timestamp;
    }

    public void setRecord_timestamp(String record_timestamp) {
        this.record_timestamp = record_timestamp;
    }

    public String getRecordid() {
        return recordid;
    }

    public void setRecordid(String recordid) {
        this.recordid = recordid;
    }

    @Override
    public String toString() {
        return "Cafe{" +
                ", datasetid='" + datasetid + '\'' +
                ", fields=" + fields +
                ", geometry=" + geometry +
                ", record_timestamp='" + record_timestamp + '\'' +
                ", recordid='" + recordid + '\'' +
                '}';
    }

    /*@PrimaryKey(autoGenerate = true)
    private int id;

    private String nom_du_cafe;
    private float price;

    public Cafe() {
    }

    @Ignore
    public Cafe(String nom_du_cafe, float price) {
        this.nom_du_cafe = nom_du_cafe;
        this.price = price;


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
    */
}
