package com.toeic.model;

public class Part2 {
    private String  id;
    private int audio;
    private String answer;

    public Part2(String id, int audio, String  answer) {
        this.id = id;
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

    public int getAudio() {
        return audio;
    }

    public void setAudio(int audio) {
        this.audio = audio;
    }
}
