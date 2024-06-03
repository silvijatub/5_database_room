package com.example.a5_database;

import android.app.Application;
import androidx.room.Room;

public class AppActivity extends Application {

    private static AppDatabase db;

    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "my_app_db")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDatabase getDatabase() {
        return db;
    }
}