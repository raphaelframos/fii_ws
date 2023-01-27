package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import com.raphaelframos.refii.common.entity.NewFundEntity;
import com.raphaelframos.refii.common.entity.ProfileEntity;
import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.common.utils.MoneyUtils;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.profile.ProfileService;
import com.raphaelframos.refii.profile.repository.ProfileRepository;
import com.raphaelframos.refii.scrap.data.FundDTO;
import com.raphaelframos.refii.wallet.repository.FundWalletRepository;
import com.raphaelframos.refii.wallet.repository.NewFundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FundService {

    @Autowired
    private final FundRepository repository;
    @Autowired
    private final NewFundRepository newFundRepository;
    @Autowired
    private final FundWalletRepository fundWalletRepository;
    @Autowired
    private final ProfileRepository profileRepository;
    @Autowired
    private final ProfileService profileService;

    public FundService(FundRepository repository, NewFundRepository newFundRepository, FundWalletRepository fundWalletRepository, ProfileRepository profileRepository, ProfileService profileService) {
        this.repository = repository;
        this.newFundRepository = newFundRepository;
        this.fundWalletRepository = fundWalletRepository;
        this.profileRepository = profileRepository;
        this.profileService = profileService;
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
