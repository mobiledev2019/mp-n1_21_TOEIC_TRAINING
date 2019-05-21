package com.toeic.database;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.toeic.dao.Part1DAO;
import com.toeic.model.Part1;

@Database(entities = {Part1.class}, version = MyDatabase.DATABASE_VERSION)
public abstract class MyDatabase extends RoomDatabase {
    private static MyDatabase myDatabase;

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TOEIC";

    public abstract Part1DAO part1DAO();

    public static MyDatabase getInstance(Context context) {
        if (myDatabase == null) {
            myDatabase = Room.databaseBuilder(context, MyDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return myDatabase;
    }
}