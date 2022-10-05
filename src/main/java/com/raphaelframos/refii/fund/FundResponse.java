package com.raphaelframos.refii.fund;

import com.raphaelframos.refii.common.model.Option;

import java.io.Serializable;

public class FundResponse implements Serializable {

    private Long id;
    private String name;
    private String segment;
    private Option option1;
    private Option option2;
    private Option option3;
    private Option option4;

    public FundResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSegment() {
        return segment;
    }

    public void setSegment(String segment) {
        this.segment = segment;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Option getOption4() {
        return option4;
    }

    public void setOption4(Option option4) {
        this.option4 = option4;
    }
}
