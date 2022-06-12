package me.raphaelframos.fii.data;

public class FundDTO {

    private String name;
    private String admin;
    private String symbol;

    public FundDTO() {}

    public FundDTO(String name, String admin, String symbol) {
        this.name = name;
        this.admin = admin;
        this.symbol = symbol;
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
}
