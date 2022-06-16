package me.raphaelframos.fii.data;

public class ClassificationFundDTO {

    private String symbol;
    private int position;
    private String value;

    public ClassificationFundDTO(int position, String symbol, String value) {
        setPosition(position);
        setSymbol(symbol);
        setValue(value);
    }

    public ClassificationFundDTO() {}

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
