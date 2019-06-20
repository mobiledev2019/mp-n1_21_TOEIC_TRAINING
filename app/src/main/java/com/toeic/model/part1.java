package com.toeic.model;


public class part1 {
    private String  id;
    private int  images;
    private int audio;
    private String answer;

    public part1(String id, int images, int audio, String  answer) {
        this.id = id;
        this.images = images;
        this.audio = audio;
        this.answer = answer;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
