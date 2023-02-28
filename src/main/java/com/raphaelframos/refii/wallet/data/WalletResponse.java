package com.raphaelframos.refii.wallet.data;

import com.raphaelframos.refii.common.entity.FundWallet;
import com.raphaelframos.refii.common.utils.MoneyUtils;

import java.io.Serializable;

public class WalletResponse implements Serializable {

    private Long id;
    private String total;
    private int amount;
    private int rating;
    private String price;
    private String date;

    public WalletResponse(){}

    public WalletResponse(FundWallet fundWallet) {
        setId(fundWallet.getId());
        setAmount(fundWallet.getAmount());
        setDate(fundWallet.getDateDemonstration());
        setPrice(fundWallet.getPrice().toString());
        setRating(fundWallet.getRating());
        setTotal(fundWallet.getPrice().multiply(MoneyUtils.intToBigDecimal(fundWallet.getAmount())).toString());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
