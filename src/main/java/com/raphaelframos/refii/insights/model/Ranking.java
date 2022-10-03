package com.raphaelframos.refii.insights.model;

import java.io.Serializable;
import java.util.List;

public class Ranking implements Serializable {

    private String title;
    private List<Fii> fiis;

    public Ranking() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Fii> getFiis() {
        return fiis;
    }

    public void setFiis(List<Fii> fiis) {
        this.fiis = fiis;
    }
}
