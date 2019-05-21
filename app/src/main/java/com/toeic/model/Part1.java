package com.toeic.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

import io.reactivex.annotations.NonNull;


@Entity(foreignKeys = @ForeignKey(entity = Unit.class,
        parentColumns = "id",
        childColumns = "unit_id",
        onDelete = ForeignKey.CASCADE))
public class Part1 implements Serializable {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int id;

    private int unit_id;
    private String  picture,
                    name,
                    descripton,
                    audio,
                    answer;
    @Ignore
    public Part1(int id, int unit_id, String picture, String name, String descripton, String audio, String answer) {
        this.id = id;
        this.unit_id = unit_id;
        this.picture = picture;
        this.name = name;
        this.descripton = descripton;
        this.audio = audio;
        this.answer = answer;
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
    public String getPicture() {
        return picture;
    }
    @Ignore
    public void setPicture(String picture) {
        this.picture = picture;
    }
    @Ignore
    public String getName() {
        return name;
    }
    @Ignore
    public void setName(String name) {
        this.name = name;
    }
    @Ignore
    public String getDescripton() {
        return descripton;
    }
    @Ignore
    public void setDescripton(String descripton) {
        this.descripton = descripton;
    }
    @Ignore
    public String getAudio() {
        return audio;
    }
    @Ignore
    public void setAudio(String audio) {
        this.audio = audio;
    }
    @Ignore
    public String getAnswer() {
        return answer;
    }
    @Ignore
    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
