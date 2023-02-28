package com.raphaelframos.refii.common.entity;

import javax.persistence.*;

@Entity
@Table(name = "fund_chat")
public class ChatFund {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int position;
    private String value;
    @ManyToOne
    private Profile profile;

    public ChatFund() {}

    public ChatFund(Profile profile, int position, String value) {
        setPosition(position);
        setProfile(profile);
        setValue(value);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
