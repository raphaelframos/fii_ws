package com.raphaelframos.refii.comment;

import com.raphaelframos.refii.common.entity.Fund;
import com.raphaelframos.refii.common.entity.Profile;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Profile profile;
    @ManyToOne
    private Fund fund;
    private String message;
    @Column(columnDefinition = "DATE")
    private LocalDateTime date;

    public Comment() {
        setDate(LocalDateTime.now());
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Fund getFund() {
        return fund;
    }

    public void setFund(Fund fund) {
        this.fund = fund;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
