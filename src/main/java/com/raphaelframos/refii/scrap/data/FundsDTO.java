package com.raphaelframos.refii.scrap.data;

import java.util.ArrayList;

public class FundsDTO {

    private int total;
    private ArrayList<FundDTO> funds;

    public FundsDTO() {}

    public FundsDTO(int total) {
        this.total = total;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<FundDTO> getFunds() {
        return funds;
    }

    public void setFunds(ArrayList<FundDTO> funds) {
        this.funds = funds;
    }

    public void add(FundDTO fundDTO) {
        if(funds == null) funds = new ArrayList<>();

        funds.add(fundDTO);
    }
}
