package me.raphaelframos.fii.motor;

import me.raphaelframos.fii.data.ClassificationFundDTO;
import me.raphaelframos.fii.insights.Insight;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class InsightsMotor {

    private final int AMOUNT_DEFAULT = 5;

    public List<ClassificationFundDTO> fundsByDividends(){
        return fundsByDividends(AMOUNT_DEFAULT);
    }

    private List<ClassificationFundDTO> fundsByDividends(int size){
        ArrayList<ClassificationFundDTO> funds = new ArrayList<>();
        funds.add(new ClassificationFundDTO(1, "MXRF11", "1.4%"));
        funds.add(new ClassificationFundDTO(2, "TORD11", "1.3%"));
        funds.add(new ClassificationFundDTO(3, "HGL11", "1.2%"));
        funds.add(new ClassificationFundDTO(4, "KINEA", "1.1%"));
        funds.add(new ClassificationFundDTO(5, "TGAR11", "1.0%"));
        funds.add(new ClassificationFundDTO(6, "MXRF11", "1.1%"));
        funds.add(new ClassificationFundDTO(7, "MXRF11", "1.1%"));
        funds.add(new ClassificationFundDTO(8, "MXRF11", "1.1%"));
        funds.add(new ClassificationFundDTO(9, "MXRF11", "1.1%"));
        funds.add(new ClassificationFundDTO(10, "MXRF11", "1.1%"));
        return funds.subList(0, size);
    }

    public String today() {
        Insight insight = new Insight();
        insight.setText("Você sabia que é importante diversificar?");
        return insight.getText();
    }
}
