package com.raphaelframos.refii.wallet.data;

import java.io.Serializable;

public class FundFeed implements Serializable {

    private String symbol;
    private String totalAmount;
    private String price;
    private double wallet;
    private String segment;
    private Long fundScrapId;

    public FundFeed() {}

    public FundFeed(Object[] o, double totalPrice) {
        setSymbol(convert(o[0]));
        setTotalAmount(convert(o[1]));
        setPrice(convert(o[2]));
        setSegment(convert(o[3]));
        setFundScrapId(Long.parseLong(convert(o[4])));
        setWallet(stringToDouble(getPrice())/ totalPrice);
    }

    private String convert(Object o){
        try {
            return o.toString();
        } catch (Exception e){
            return "";
        }

    }

    public Long getFundScrapId() {
        return fundScrapId;
    }

    public void setFundScrapId(Long fundScrapId) {
        this.fundScrapId = fundScrapId;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public double getWallet() {
        return wallet;
    }

    public String getWalletDemo(){
        int percentage = (int) Math.floor(getWallet() * 100);
        return percentage + "%";
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public double stringToDouble(String value){
        try{
            return Double.parseDouble(value);
        }catch (Exception e){
            return 0.0;
        }
    }
}
