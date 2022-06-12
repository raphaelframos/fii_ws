package me.raphaelframos.fii.data;

import java.util.ArrayList;

public class DetailFundDTO {

    private IndicatorFundDTO indicator;
    private ArrayList<StockFundDTO> stocks;

    public IndicatorFundDTO getIndicator() {
        return indicator;
    }

    public void setIndicator(IndicatorFundDTO indicator) {
        this.indicator = indicator;
    }

    public ArrayList<StockFundDTO> getStocks() {
        return stocks;
    }

    public void setStocks(ArrayList<StockFundDTO> stocks) {
        this.stocks = stocks;
    }
}
