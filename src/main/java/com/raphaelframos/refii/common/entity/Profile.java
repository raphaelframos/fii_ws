package com.raphaelframos.refii.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "profile")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String occupation;
    private String photo;
    private int points;
    @OneToMany
    private List<FundWallet> funds;

    public Profile(){}

    public Profile(String email) {
        setEmail(email);
    }

    public void add(FundWallet fundWallet) {
        if(funds == null){
            funds = new ArrayList<>();
        }
        funds.add(fundWallet);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<FundWallet> getFunds() {
        return funds;
    }

    public void setFunds(List<FundWallet> funds) {
        this.funds = funds;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
