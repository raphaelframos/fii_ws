package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

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


    public ChatResponse newFund(String value, int position) {
        String text = "";
        int type = 0;
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
                text = "Em uma escala de 1 a 5, com 0 sendo ruim e 5 bom, que nota você daria para esse fundo?";
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
                    position = RATING;
                    type = RATING_TYPE;
                }
                break;
            case 5:
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
                break;
        }
        return new ChatResponse(position, text, type);
    }

    private String getMessageName() {
        return "Qual o nome do fundo imobiliário?";
    }
}
