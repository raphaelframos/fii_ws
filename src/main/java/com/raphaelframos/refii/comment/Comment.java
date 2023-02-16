package com.raphaelframos.refii.comment;

import com.raphaelframos.refii.common.entity.FundEntity;
import com.raphaelframos.refii.common.entity.ProfileEntity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "COMMENT")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private ProfileEntity profile;
    @ManyToOne
    private FundEntity fund;
    private String message;
    @Column(columnDefinition = "DATE")
    private LocalDate date;

    public Comment() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
