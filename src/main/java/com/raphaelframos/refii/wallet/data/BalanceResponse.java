package com.raphaelframos.refii.wallet.data;

import java.io.Serializable;

public class BalanceResponse implements Serializable {

    private String title;
    private String value;

    public BalanceResponse(String title, String value) {
        this.title = title;
        this.value = value;
    }

    public BalanceResponse() {
    }

    public BalanceResponse(String title, double total) {
        setTitle(title);
        setValue(String.valueOf(total));
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
