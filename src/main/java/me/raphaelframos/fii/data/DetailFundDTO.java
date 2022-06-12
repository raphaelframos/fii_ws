package me.raphaelframos.fii.data;

import java.util.ArrayList;

public class DetailFundDTO {

    private ArrayList<IndicatorFundDTO> indicators;
    private ArrayList<InfoFundDTO> informations;
    private StockFundDTO stock;


    public StockFundDTO getStock() {
        return stock;
    }

    public void setStock(StockFundDTO stock) {
        this.stock = stock;
    }

    public ArrayList<IndicatorFundDTO> getIndicators() {
        return indicators;
    }

    public void setIndicators(ArrayList<IndicatorFundDTO> indicators) {
        this.indicators = indicators;
    }

    public ArrayList<InfoFundDTO> getInformations() {
        return informations;
    }

    public void setInformations(ArrayList<InfoFundDTO> informations) {
        this.informations = informations;
    }

    public void add(InfoFundDTO info) {
        if(informations == null){
            informations = new ArrayList<>();
        }
        informations.add(info);
    }

    public void add(IndicatorFundDTO indicatorFundDTO) {
        if(indicators == null){
            indicators = new ArrayList<>();
        }
        indicators.add(indicatorFundDTO);
    }
}
