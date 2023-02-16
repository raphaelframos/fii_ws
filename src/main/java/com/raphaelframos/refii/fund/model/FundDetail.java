package com.raphaelframos.refii.fund.model;

import java.io.Serializable;
import java.util.List;

public class FundDetail implements Serializable {

    private String name;
    private String admin;
    private String symbol;
    private String segment;
    private List<String> links;

    public FundDetail() {}

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
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

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }
}
