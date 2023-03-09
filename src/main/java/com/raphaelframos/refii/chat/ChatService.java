package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.chat.factory.ChatFactory;
import com.raphaelframos.refii.chat.factory.sell.AmountSell;
import com.raphaelframos.refii.chat.factory.sell.EndSell;
import com.raphaelframos.refii.chat.factory.sell.InitSell;
import com.raphaelframos.refii.chat.factory.sell.RatingSell;
import com.raphaelframos.refii.common.entity.ChatFund;
import com.raphaelframos.refii.common.entity.Fund;
import com.raphaelframos.refii.common.entity.FundWallet;
import com.raphaelframos.refii.common.entity.Profile;
import com.raphaelframos.refii.common.model.ChatResponse;
import com.raphaelframos.refii.fund.repository.FundRepository;
import com.raphaelframos.refii.profile.ProfileService;
import com.raphaelframos.refii.wallet.repository.FundWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ChatService {

    @Autowired
    private final ChatRepository repository;
    @Autowired
    private final ProfileService profileService;
    @Autowired
    private final FundRepository fundRepository;
    @Autowired
    private final FundWalletRepository fundWalletRepository;

    public ChatService(
            ChatRepository repository,
            ProfileService profileService,
            FundRepository fundRepository,
            FundWalletRepository fundWalletRepository
    ) {
        this.repository = repository;
        this.profileService = profileService;
        this.fundRepository = fundRepository;
        this.fundWalletRepository = fundWalletRepository;
    }

    public ChatResponse newFund(String value, int position, Long userId, Long fundId){
        value = value.toUpperCase(Locale.ROOT);
        ChatResponse chat;
        if(fundId == 0){
            chat = newFund(value, position, userId);
        }else {
            chat = sellFund(value, position, userId, fundId);
        }
        return chat;
    }

    private ChatResponse sellFund(String value, int position, Long userId, Long fundId) {
        Optional<Profile> profileEntity = profileService.findBy(userId);
        List<FundWallet> funds = fundWalletRepository.findByFundIdAndUser(fundId, userId);
        Fund fund = funds.get(0).getFund();
        Chat chat;
        int amount = sum(funds);
        if(position == 0){
            chat = new InitSell(amount, fund.getSymbol());
        }else if (position == 1) {
            chat = new AmountSell(amount, fund.getSymbol());
        }else if( position == 2){
            chat = new RatingSell();
        }else {
            chat = new EndSell();
        }
        if(chat.isValid(value)){
            Optional<ChatFund> chatFundEntity = repository.findByPositionAndUserId(position, userId);
            if(profileEntity.isPresent()){
                Profile profile = profileEntity.get();
                ChatFund entity;
                if(chatFundEntity.isPresent()){
                    entity = chatFundEntity.get();
                    entity.setValue(value);
                }else{
                    entity = new ChatFund(profile, position, value);
                }
                repository.save(entity);
                if(position == 3){
                    FundWallet fundWallet = new FundWallet();
                    fundWallet.setProfile(profile);
                    fundWallet.setFund(fund);
                    List<ChatFund> answers = repository.findByUserId(userId);
                    answers.forEach(c-> {
                        switch (c.getPosition()){
                            case ChatFactory.AMOUNT_SELL:
                                fundWallet.setAmount(Integer.parseInt(c.getValue()) * -1);
                                break;
                            case ChatFactory.PRICE_SELL:
                                fundWallet.setPrice(new BigDecimal(c.getValue()));
                                break;
                            case ChatFactory.RATING_SELL:
                                fundWallet.setRating(Integer.parseInt(c.getValue()));
                                break;
                        }
                    });

                    fundWallet.setType(0);
                    fundWalletRepository.save(fundWallet);
                    repository.delete(userId);
                }
            }
        }
        return chat.getChatResponse(value);
    }

    private int sum(List<FundWallet> funds) {
        int sum = 0;
        for(FundWallet fundWallet : funds){
            sum += fundWallet.getAmount();
        }
        return sum;
    }

    private ChatResponse newFund(String value, int position, Long userId) {
        Chat chat = ChatFactory.newFundChat(position);
        boolean isValid = true;
        if (position == ChatFactory.AMOUNT) {
            List<String> fundsNames = fundRepository.findNames();
            isValid = fundsNames.contains(value);
        }
        if (chat.isValid(value) && isValid) {
            Optional<Profile> profileEntity = profileService.findBy(userId);
            if (profileEntity.isPresent()) {
                saveChat(value, position, userId, profileEntity.get());
            } else {
                value = "";
            }
        } else {
            value = "";
        }
        return chat.getChatResponse(value);
    }

    private void saveChat(String value, int position, Long userId, Profile profileEntity) {
        Optional<ChatFund> chatFundEntity = repository.findByPositionAndUserId(position, userId);
        ChatFund entity;
        if(chatFundEntity.isPresent()){
            entity = chatFundEntity.get();
            entity.setValue(value);
        }else{
            entity = new ChatFund(profileEntity, position, value);
        }
        repository.save(entity);
        if(position == ChatFactory.RELOAD){
            saveFund(userId, profileEntity);
        }
    }

    private void saveFund(Long userId, Profile profileEntity) {
        FundWallet fund = new FundWallet();
        fund.setProfile(profileEntity);
        fund.setType(1);
        List<ChatFund> answers = repository.findByUserId(userId);
        answers.forEach(c-> {
            switch (c.getPosition()){
                case ChatFactory.AMOUNT:
                    fund.setFund(fundRepository.findBySymbol(c.getValue()));
                    break;
                case ChatFactory.PRICE:
                    fund.setAmount(Integer.parseInt(c.getValue()));
                    break;
                case ChatFactory.RATING:
                    fund.setPrice(new BigDecimal(c.getValue()));
                    break;
                case ChatFactory.RELOAD:
                    fund.setRating(Integer.parseInt(c.getValue()));
                    break;
            }
        });
        fundWalletRepository.save(fund);
        repository.delete(userId);
    }
}
