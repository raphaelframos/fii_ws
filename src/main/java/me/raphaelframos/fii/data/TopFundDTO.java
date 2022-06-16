package me.raphaelframos.fii.data;

import java.util.List;

public class TopFundDTO {

    private String title;
    private List<ClassificationFundDTO> classificationFunds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ClassificationFundDTO> getClassificationFunds() {
        return classificationFunds;
    }

    public void setClassificationFunds(List<ClassificationFundDTO> classificationFunds) {
        this.classificationFunds = classificationFunds;
    }
}
