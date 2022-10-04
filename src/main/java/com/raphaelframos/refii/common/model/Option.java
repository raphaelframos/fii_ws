package com.raphaelframos.refii.common.model;

import java.io.Serializable;

public class Option implements Serializable {

    private String value;
    private String description;

    public Option() {
    }

    public Option(String value, String description) {
        this.value = value;
        this.description = description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
