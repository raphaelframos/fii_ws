package com.raphaelframos.refii.insights.model;

import com.raphaelframos.refii.common.model.Option;

public class Fii {

    private Long id;
    private String position;
    private String name;
    private Option option;

    public Fii() {
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

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position + "º";
    }
}
