package com.toeic.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.toeic.model.Part1;
import com.toeic.model.Unit;

import java.util.List;

@Dao
public interface Part1DAO {
    @Insert
    void insert(Part1 part1 );

    @Update
    void update(Part1 part1 );

    @Delete
    void delete(Part1 part1 );

    @Query("SELECT * FROM part1 WHERE unit_id=:unit_id")
    List<Unit> getAllRepos(final int unit_id);

    @Query("SELECT * FROM part1  WHERE id=:id")
    List<Part1> findRepositoriesForPart1(final int id);

}
