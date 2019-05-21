package com.toeic.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.lang.ref.SoftReference;

@Entity(tableName = "words")
public class Word {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "word")
    private String  word;
    @ColumnInfo(name = "description")
    private SoftReference description;

    @Ignore
    public Word(int id, String word, SoftReference description) {
        this.id = id;
        this.word = word;
        this.description = description;
    }
    @Ignore
    public int getId() {
        return id;
    }
    @Ignore
    public void setId(int id) {
        this.id = id;
    }
    @Ignore
    public String getWord() {
        return word;
    }
    @Ignore
    public void setWord(String word) {
        this.word = word;
    }
    @Ignore
    public SoftReference getDescription() {
        return description;
    }
    @Ignore
    public void setDescription(SoftReference description) {
        this.description = description;
    }
}
