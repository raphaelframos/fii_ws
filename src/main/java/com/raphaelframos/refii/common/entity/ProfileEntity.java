package com.raphaelframos.refii.common.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PROFILE")
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String occupation;
    @OneToMany
    private List<FundWalletEntity> funds;

    public ProfileEntity(){}

    public ProfileEntity(String email) {
        setEmail(email);
    }

    public void add(FundWalletEntity fundWalletEntity) {
        if(funds == null){
            funds = new ArrayList<>();
        }
        funds.add(fundWalletEntity);
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

    public List<FundWalletEntity> getFunds() {
        return funds;
    }

    public void setFunds(List<FundWalletEntity> funds) {
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
}
