package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.common.entity.Fund;
import com.raphaelframos.refii.fund.model.FundDetail;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.profile.ProfileService;
import com.raphaelframos.refii.profile.repository.ProfileRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import com.raphaelframos.refii.wallet.repository.FundWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FundService {

    @Autowired
    private final FundRepository repository;
    @Autowired
    private final FundWalletRepository fundWalletRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final ProfileService profileService;

    public FundService(FundRepository repository, FundWalletRepository fundWalletRepository, ProfileRepository profileRepository, ProfileService profileService) {
        this.repository = repository;
        this.fundWalletRepository = fundWalletRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
    }

    public void create(ArrayList<FundDTO> funds, HashMap<String, String> sectors) {
        for(FundDTO fund : funds){
            if(fund.isValid() && notSaved(fund.getSymbol())){
                fund.setSegment(getSegment(sectors, fund));
                repository.save(fund.toEntity());
            }
        }
    }

    private String getSegment(HashMap<String, String> sectors, FundDTO fund) {
        String result = sectors.get(fund.getSymbol());
        if(result == null){
            result = "Outros";
        }
        return result;
    }

    private boolean notSaved(String symbol) {
        return !repository.existsFundsBySymbol(symbol);
    }

    public List<FundDTO> findAll() {
        return repository.findAll().stream()
                .map(f -> new FundDTO(f.getName(), f.getAdmin(), f.getSymbol(), f.getHref()))
                .collect(Collectors.toList());
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

    public FundDetail detail(Long fundId) {
        Optional<Fund> fundEntity = repository.findById(fundId);
        FundDetail fundDetail = new FundDetail();
        if(fundEntity.isPresent()){
            Fund fund = fundEntity.get();
            fundDetail.setName(fund.getName());
            fundDetail.setAdmin(fund.getAdmin());
            fundDetail.setSymbol(fund.getSymbol());
            fundDetail.setSegment(fund.getSegment());
            ArrayList<String> links = new ArrayList<>();
            links.add("https://www.fundsexplorer.com.br/funds/" + fund.getSymbol());
            links.add("https://statusinvest.com.br/fundos-imobiliarios/" + fund.getSymbol());
            links.add("https://fiis.com.br/" + fund.getSymbol());
            fundDetail.setLinks(links);
        }
        return fundDetail;
    }
}
