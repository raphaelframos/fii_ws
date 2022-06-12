package me.raphaelframos.fii.data;

public class StockFundDTO {

    private String price;
    private String percent;

    public StockFundDTO(String price, String percent) {
        this.percent = percent;
        this.price = price;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }
}
