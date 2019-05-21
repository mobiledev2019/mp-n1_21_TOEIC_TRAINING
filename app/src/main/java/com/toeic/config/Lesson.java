package com.toeic.config;

public class Lesson {
    private String  title, description;
    private int  avatar;

    public Lesson(String title, String description, int avatar) {
        this.title = title;
        this.description = description;
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

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
