package me.raphaelframos.fii.service;

import me.raphaelframos.fii.data.DetailFundDTO;
import me.raphaelframos.fii.data.FundRankingDTO;
import me.raphaelframos.fii.data.FundsDTO;

import java.util.ArrayList;

public interface FundsExplorerService {
    FundsDTO listFunds();
    DetailFundDTO details(String fund);
    ArrayList<FundRankingDTO> ranking();
}
