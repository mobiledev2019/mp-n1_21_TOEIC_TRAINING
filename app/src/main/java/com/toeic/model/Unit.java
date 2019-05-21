package com.toeic.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Unit")

public class Unit implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "avatar")
    private int avatar;
    @ColumnInfo(name = "title")
    private String  title;
    @ColumnInfo(name = "description")
    private String  description;

    @Ignore
    public Unit(int id , int avatar, String title, String description) {
        this.id = id;
        this.avatar = avatar;
        this.title = title;
        this.description = description;
    }

    @Ignore
    public Unit(int avatar, String title, String description) {
        this.avatar = avatar;
        this.title = title;
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
    public int getAvatar() {
        return avatar;
    }
    @Ignore
    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
    @Ignore
    public String getTitle() {
        return title;
    }
    @Ignore
    public void setTitle(String title) {
        this.title = title;
    }
    @Ignore
    public String getDescription() {
        return description;
    }
    @Ignore
    public void setDescription(String description) {
        this.description = description;
    }
}
