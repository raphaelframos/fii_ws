package me.raphaelframos.fii.data;

public class FundDTO {

    private String name;
    private String admin;
    private String symbol;
    private String href;

    public FundDTO() {}

    public FundDTO(String name, String admin, String symbol, String href) {
        this.name = name;
        this.admin = admin;
        this.symbol = symbol;
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
