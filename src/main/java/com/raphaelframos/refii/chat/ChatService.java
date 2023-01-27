package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.chat.factory.ChatFactory;
import com.raphaelframos.refii.common.entity.ChatFundEntity;
import com.raphaelframos.refii.common.entity.FundWalletEntity;
import com.raphaelframos.refii.common.entity.ProfileEntity;
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

    public ChatResponse newFund(String value, int position, Long userId){
        value = value.toUpperCase(Locale.ROOT);
        Chat chat = ChatFactory.get(position);
        if(chat.isValid(value)){
            boolean isValid = true;
            if(position == ChatFactory.AMOUNT){
                List<String> fundsNames = fundRepository.findNames();
                isValid = fundsNames.contains(value);
            }
            if(isValid){
                Optional<ProfileEntity> profileEntity = profileService.findBy(userId);
                if(profileEntity.isPresent()){
                    saveChat(value, position, userId, profileEntity.get());
                }else{
                    value = "";
                }
            }else{
                value = "";
            }
        }
        return chat.getChatResponse(value);
    }

    private void saveChat(String value, int position, Long userId, ProfileEntity profileEntity) {
        Optional<ChatFundEntity> chatFundEntity = repository.findByPositionAndUserId(position, userId);
        ChatFundEntity entity;
        if(chatFundEntity.isPresent()){
            entity = chatFundEntity.get();
            entity.setValue(value);
        }else{
            entity = new ChatFundEntity(profileEntity, position, value);
        }
        repository.save(entity);
        if(position == ChatFactory.RELOAD){
            saveFund(userId, profileEntity);
        }
    }

    private void saveFund(Long userId, ProfileEntity profileEntity) {
        FundWalletEntity fund = new FundWalletEntity();
        fund.setProfile(profileEntity);
        List<ChatFundEntity> answers = repository.findByUserId(userId);
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
