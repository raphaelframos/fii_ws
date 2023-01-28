package com.raphaelframos.refii.common.entity;

import javax.persistence.*;

@Entity(name = "FUND_CHAT")
public class ChatFundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int position;
    private String value;
    @ManyToOne
    private ProfileEntity profile;

    public ChatFundEntity() {}

    public ChatFundEntity(ProfileEntity profile, int position, String value) {
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

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }
}
