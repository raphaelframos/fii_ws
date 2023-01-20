package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.common.entity.FundEntity;
import com.raphaelframos.refii.common.model.DetailFund;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FundService {

    @Autowired
    private FundRepository repository;

    public FundService(FundRepository repository) {
        this.repository = repository;
    }

    public void create(ArrayList<FundDTO> funds) {
        for(FundDTO fund : funds){
            if(fund.verify() && notSaved(fund.getSymbol())){
                repository.save(fund.toEntity());
            }
        }
    }

    private boolean notSaved(String symbol) {
        return !repository.existsFundsBySymbol(symbol);
    }

    public List<FundDTO> findAll() {
        List<FundDTO> funds = repository.findAll().stream()
                .map(f -> new FundDTO(f.getName(), f.getAdmin(), f.getSymbol(), f.getHref(), f.getSegment()))
                .collect(Collectors.toList());
        ArrayList<FundDTO> test = new ArrayList<FundDTO>();
        test.add(new FundDTO("MXRF nome grande", "Teste", "MXR", "", ""));
        test.add(new FundDTO("MXRF nome grande", "Teste", "MXR", "", ""));
        test.add(new FundDTO("MXRF nome grande", "Teste", "MXR", "", ""));
        return test;
    }

    public ResponseEntity<DetailFund> findById(Long id) {
        Optional<FundEntity> fund = repository.findById(id);
        return ResponseEntity.ok(new DetailFund());
    }
}
