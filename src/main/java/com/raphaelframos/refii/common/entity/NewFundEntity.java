package com.raphaelframos.refii.common.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "FUND")
public class NewFundEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int amount;
    private BigDecimal price;

    public NewFundEntity() {
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

    public boolean isCompleted() {
        return id > 0 && !name.isEmpty() && amount > 0 && price.compareTo(BigDecimal.ZERO) > 0;
    }
}
