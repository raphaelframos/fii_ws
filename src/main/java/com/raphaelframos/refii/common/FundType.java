package com.raphaelframos.refii.common;

public enum FundType {

    PURCHASE(0), SALE(1);

    int type;

    FundType(int type) {
        this.type = type;
    }
}
