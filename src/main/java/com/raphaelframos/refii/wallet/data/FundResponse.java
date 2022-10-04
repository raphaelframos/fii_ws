package com.raphaelframos.refii.wallet.data;

import com.raphaelframos.refii.common.model.Option;

import java.io.Serializable;

public class FundResponse implements Serializable {

    private String symbol;
    private String segmented;
    private Option option1;
    private Option option2;
    private Option option3;

    public FundResponse() {
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getSegmented() {
        return segmented;
    }

    public void setSegmented(String segmented) {
        this.segmented = segmented;
    }

    public Option getOption1() {
        return option1;
    }

    public void setOption1(Option option1) {
        this.option1 = option1;
    }

    public Option getOption2() {
        return option2;
    }

    public void setOption2(Option option2) {
        this.option2 = option2;
    }

    public Option getOption3() {
        return option3;
    }

    public void setOption3(Option option3) {
        this.option3 = option3;
    }
}
