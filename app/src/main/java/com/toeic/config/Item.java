package com.toeic.config;

public class Item {
    private String  title;
    private int  avatar;

    public Item(String title, int avatar) {
        this.title = title;
        this.avatar = avatar;
    }

    public Item() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }
}
