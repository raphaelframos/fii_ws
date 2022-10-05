package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FundService {

    @Autowired
    private FundRepository repository;

    public FundService(FundRepository repository) {
        this.repository = repository;
    }

    public void create(ArrayList<FundDTO> funds) {
        for(FundDTO fund : funds){
            if(fund.isValid() && notSaved(fund.getSymbol())){
                repository.save(fund.toEntity());
            }
        }
    }

    private boolean notSaved(String symbol) {
        return !repository.existsBySymbol(symbol);
    }
}
