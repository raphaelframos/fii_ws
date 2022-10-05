package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    public ChatResponse create(String value, int position) {
        String text = "";
        if(position == 0){
            text = "Qual o nome do fii?";
            ++position;
        }else if(position == 1){
            if(isValidFii(value)){
                ++position;
                text = "Quantas cotas?";
            }else{

            }
        }else if(position == 2){
            if(isValidAmount(value)){
                ++position;
                text = "E o preço unitário de cada Fii?";
            }else{

            }
        }else if(position == 3){
            ++position;
            text = "Deseja cadastrar um novo Fii?";
        }else if(position == 4){
            position = 0;
            if(value.equalsIgnoreCase("Sim")){
                text = "Qual o nome do fii?";
                ++position;
            }else{
                text = "";
            }
        }
        return new ChatResponse(position, text);
    }

    private boolean isValidAmount(String value) {
        try{
            return (Integer.parseInt(value) > 0);
        }catch (Exception e){
            return false;
        }
    }

    private boolean isValidFii(String value){
        List<String> names = repository.findNames();
        return names.contains(value.toUpperCase());
    }

    public List<String> names() {
        return repository.findNames();
    }
}
