package com.raphaelframos.refii.common.entity;

import javax.persistence.*;

@Entity(name = "CHAT_FUND")
public class ChatFundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int position;
    private String value;
    @ManyToOne
    private ProfileEntity profileEntity;

    public ChatFundEntity() {}

    public ChatFundEntity(ProfileEntity profileEntity, int position, String value) {
        setPosition(position);
        setProfileEntity(profileEntity);
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

    public ProfileEntity getProfileEntity() {
        return profileEntity;
    }

    public void setProfileEntity(ProfileEntity profileEntity) {
        this.profileEntity = profileEntity;
    }
}
