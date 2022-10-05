package com.raphaelframos.refii.common.model;

public class ChatResponse {

    private int position;
    private String text;

    public ChatResponse() {
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
