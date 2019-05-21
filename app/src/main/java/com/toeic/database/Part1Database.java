package com.toeic.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.toeic.dao.Part1DAO;
import com.toeic.dao.UnitDAO;
import com.toeic.model.Part1;
import com.toeic.model.Unit;

@Database(entities = { Part1.class, Unit.class },
        version = 1)
public abstract class Part1Database extends RoomDatabase {
    public abstract Part1DAO getPart1Dao();
    public abstract UnitDAO getUnitDao();
}
