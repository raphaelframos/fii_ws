package com.raphaelframos.refii.common.model;

public class ChatResponse {

    private int position;
    private String text;
    private int type;
    private boolean isOwner = false;

    public ChatResponse() {}

    public ChatResponse(int position, String text, int type) {
        this.position = position;
        this.text = text;
        setType(type);
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

    public boolean isOwner() {
        return isOwner;
    }

    public void setOwner(boolean owner) {
        isOwner = owner;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
