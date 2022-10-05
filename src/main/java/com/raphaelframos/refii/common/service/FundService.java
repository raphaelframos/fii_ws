package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.fund.FundResponse;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        return !repository.existsFundsBySymbol(symbol);
    }

    public List<FundDTO> findAll() {
        List<FundDTO> funds = repository.findAll().stream()
                .map(f -> new FundDTO(f.getName(), f.getAdmin(), f.getSymbol(), f.getHref()))
                .collect(Collectors.toList());
        return funds;
    }
}
