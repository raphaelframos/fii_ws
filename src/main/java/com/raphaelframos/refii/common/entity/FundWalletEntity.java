package com.raphaelframos.refii.common.entity;

import com.raphaelframos.refii.common.FundType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "FUND_WALLET")
public class FundWalletEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private FundEntity fund;
    private int amount;
    private int rating;
    private BigDecimal price;
    @ManyToOne
    private ProfileEntity profile;
    @Column(columnDefinition = "DATE")
    private LocalDate date;
    private FundType type;

    public FundWalletEntity() {
        date = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public void setProfile(ProfileEntity profile) {
        this.profile = profile;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public FundEntity getFund() {
        return fund;
    }

    public void setFund(FundEntity fund) {
        this.fund = fund;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDateDemonstration() {
        String result;
        try{
            result = date.toString();
        }catch (Exception e){
            result = "";
        }
        return result;
    }
}
