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

    private final int NAME = 0;
    private final int NAME_TYPE = 0;
    private final int AMOUNT = 1;
    private final int AMOUNT_TYPE = 1;
    private final int PRICE = 2;
    private final int PRICE_TYPE = 2;
    private final int RATING = 3;
    private final int RATING_TYPE = 4;
    private final int RELOAD = 4;
    private final int RELOAD_TYPE = 3;
    private final int END = 5;

    public ChatService(ChatRepository repository, ProfileService profileService, FundRepository fundRepository, FundWalletRepository fundWalletRepository) {
        this.repository = repository;
        this.profileService = profileService;
        this.fundRepository = fundRepository;
        this.fundWalletRepository = fundWalletRepository;
    }

    public ChatResponse newFund(String value, int position, Long userId){
        Chat chat = ChatFactory.get(value, position);
        if(chat.isValid(value)){
            boolean isValid = true;
            if(position == AMOUNT){
                List<String> fundsNames = fundRepository.findNames();
                isValid = fundsNames.contains(value);
            }
            if(isValid){
                Optional<ProfileEntity> profileEntity = profileService.findBy(userId);
                if(profileEntity.isPresent()){
                    Optional<ChatFundEntity> chatFundEntity = repository.findByPositionAndUserId(position, userId);
                    ChatFundEntity entity;
                    if(chatFundEntity.isPresent()){
                        entity = chatFundEntity.get();
                        entity.setValue(value);
                    }else{
                        entity = new ChatFundEntity(profileEntity.get(), position, value);
                    }
                    repository.save(entity);
                    if(position == RELOAD){
                        FundWalletEntity fund = new FundWalletEntity();
                        List<ChatFundEntity> answers = repository.findByUserId(userId);
                        answers.forEach(c-> {
                            switch (c.getPosition()){
                                case ChatFactory.AMOUNT:
                                    fund.setName(c.getValue());
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
                }else{

                }
            }else{
                value = "";
            }

        }
        return chat.getChatResponse(value);
    }


    public ChatResponse newFund(String value, int position) {
        String text = "";
        int type;
        boolean isValid = false;
        switch (position){
            case NAME:
                position++;
                text = getMessageName();
                type = NAME_TYPE;
                break;
            case AMOUNT:
                text = "Quantas cotas?";
                position++;
                type = AMOUNT_TYPE;
                break;
            case PRICE:
                text = "E o preço unitário de cada Fii?";
                position++;
                type = PRICE_TYPE;
                break;
            case RATING:
                text = getMessageRating();
                position++;
                type = RATING_TYPE;
                break;
            case RELOAD:
                try{
                    int rating = Integer.parseInt(value);
                    if(rating >= 1 && rating <= 5){
                        text = "Deseja cadastrar um novo Fii?";
                        position++;
                        type = RELOAD_TYPE;
                    }else{
                        throw new NumberFormatException("Número inválido");
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    text = getMessageRating();
                    position = RATING;
                    type = RATING_TYPE;
                }
                break;
            case END:
                if(value.equalsIgnoreCase("Sim")){
                    text = getMessageName();
                }else{
                    text = "Obrigado, até o próximo!";
                }
                position = NAME;
                type = NAME_TYPE;
                break;

            default:
                text = "Pode repetir?";
                type = NAME_TYPE;
                break;
        }
        return new ChatResponse(position, text, type);
    }

    private String getMessageRating() {
        return "Em uma escala de 1 a 5, com 0 sendo ruim e 5 bom, que nota você daria para esse fundo?";
    }

    private String getMessageName() {
        return "Qual o nome do fundo imobiliário?";
    }

}
