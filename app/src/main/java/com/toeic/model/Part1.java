package com.toeic.model;

import java.io.Serializable;


public class Part1 implements Serializable {
    private int id;

    private int unit_id;
    private String  picture,
                    name,
                    audio,
                    answer;
    public Part1(int id, int unit_id, String picture, String name, String audio, String answer) {
        this.id = id;
        this.unit_id = unit_id;
        this.picture = picture;
        this.name = name;
        this.audio = audio;
        this.answer = answer;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudio() {
        return audio;
    }

    public void setAudio(String audio) {
        this.audio = audio;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
