package com.example.meuni.cafeeuro;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.meuni.cafeeuro.models.Cafe;

import java.util.List;

@Dao
public interface CafeDao {

    @Query("SELECT * FROM cafe")
    List<Cafe> getAll();

    @Insert
    void insert(Cafe... cafes);

    @Delete
    void delete(Cafe cafe);

    @Query("DELETE FROM cafe")
    void nukeTable();

}
