package com.toeic.model;


import java.io.Serializable;

public class Unit implements Serializable {

    private int id;
    private int avatar;
    private String  title;
    private int status;
    private String  description;
    private String  name;


    public Unit(int id , int avatar, String title, String description) {
        this.id = id;
        this.avatar = avatar;
        this.title = title;
        this.description = description;
    }

    public Unit(int avatar, String title, String description) {
        this.avatar = avatar;
        this.title = title;
        this.description = description;
    }

    public Unit(int id, int avatar,  String title, int status) {
        this.id = id;
        this.title = title;
        this.avatar = avatar;
        if(status == 1) {
            this.description = "Đã hoàn thành bài học";
        } else {
            this.description = "Bạn chưa học bài này";
        }
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
