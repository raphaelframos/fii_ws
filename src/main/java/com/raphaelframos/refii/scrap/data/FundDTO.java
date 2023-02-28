package com.raphaelframos.refii.scrap.data;

import com.raphaelframos.refii.common.entity.Fund;

public class FundDTO {

    private String name = "";
    private String admin = "";
    private String symbol = "";
    private String href = "";
    private String segment = "";

    public FundDTO() {}

    public FundDTO(String name, String admin, String symbol, String href) {
        this.name = name;
        this.admin = admin;
        this.symbol = symbol;
        this.href = href;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isValid() {
        return !name.isEmpty() && !symbol.isEmpty() && !href.isEmpty();
    }

    public Fund toEntity() {
        Fund fund = new Fund();
        fund.setAdmin(getAdmin());
        fund.setHref(getHref());
        fund.setName(getName());
        fund.setSymbol(getSymbol());
        fund.setSegment(getSegment());
        return fund;
    }
}
