package com.toeic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.toeic.model.Unit;

import java.util.List;

@Dao
public interface UnitDAO {
    @Insert
    void insert(Unit unit);

    @Update
    void Update(Unit unit);

    @Delete
    void delete(Unit unit );



    @Query("SELECT * FROM unit  WHERE id=:id")
    List<Unit> findRepositoriesForUnit(final int id);
}
