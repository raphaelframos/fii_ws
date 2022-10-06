package com.raphaelframos.refii.common.service;

import com.raphaelframos.refii.common.entity.FundWalletEntity;
import com.raphaelframos.refii.common.entity.NewFundEntity;
import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.common.utils.MoneyUtils;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.profile.ProfileEntity;
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
    private FundRepository repository;
    @Autowired
    private NewFundRepository newFundRepository;
    @Autowired
    private FundWalletRepository fundWalletRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public FundService(FundRepository repository, NewFundRepository newFundRepository, FundWalletRepository fundWalletRepository, ProfileRepository profileRepository) {
        this.repository = repository;
        this.newFundRepository = newFundRepository;
        this.fundWalletRepository = fundWalletRepository;
        this.profileRepository = profileRepository;
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

    public ChatResponse create(Long id, String value, int position) {
        Optional<NewFundEntity> newFundEntity = newFundRepository.findById(id);
        NewFundEntity newFund;
        newFund = newFundEntity.orElseGet(NewFundEntity::new);
        newFund.setId(id);
        String text = "";
        if(position == 0){
            text = "Qual o nome do fii?";
            ++position;
        }else if(position == 1){
            if(isValidFii(value)){
                ++position;
                text = "Quantas cotas?";
                newFund.setName(value);
            }else{

            }
        }else if(position == 2){
            if(isValidAmount(value)){
                ++position;
                text = "E o preço unitário de cada Fii?";
                newFund.setAmount(MoneyUtils.stringToInt(value));
            }else{

            }
        }else if(position == 3){
            ++position;
            newFund.setPrice(MoneyUtils.stringToBigDecimal(value));
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
        newFundRepository.save(newFund);
        createNewFund(id, newFund);

        return new ChatResponse(position, text);
    }

    private void createNewFund(Long id, NewFundEntity newFund) {
        if(newFund.isCompleted()){
            Optional<ProfileEntity> profileEntity = profileRepository.findById(id);
            if(profileEntity.isPresent()){
                ProfileEntity profile = profileEntity.get();
                FundWalletEntity fundWalletEntity = new FundWalletEntity();
                fundWalletEntity.setAmount(newFund.getAmount());
                fundWalletEntity.setName(newFund.getName());
                fundWalletEntity.setPrice(newFund.getPrice());
                fundWalletEntity.setProfile(profile);
                fundWalletEntity = fundWalletRepository.save(fundWalletEntity);
                profile.add(fundWalletEntity);
                profileRepository.save(profile);
            }
        }
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
