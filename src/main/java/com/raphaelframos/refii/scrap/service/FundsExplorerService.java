package com.raphaelframos.refii.scrap.service;

import com.raphaelframos.refii.scrap.data.DetailFundDTO;
import com.raphaelframos.refii.scrap.data.FundRankingDTO;
import com.raphaelframos.refii.scrap.data.FundsDTO;

import java.util.ArrayList;

public interface FundsExplorerService {
    FundsDTO listFunds();
    DetailFundDTO details(String fund);
    ArrayList<FundRankingDTO> ranking();
    ArrayList<FundRankingDTO> ranking(String type, String category);
    void create();
}
