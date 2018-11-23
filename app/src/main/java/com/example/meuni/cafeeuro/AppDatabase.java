package com.example.meuni.cafeeuro;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.meuni.cafeeuro.models.Bottle;
import com.example.meuni.cafeeuro.models.Cafe;


@Database(entities = {Cafe.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CafeDao getCafeDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room
                    .databaseBuilder(context.getApplicationContext(), AppDatabase.class, "db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}