package com.raphaelframos.refii.chat.factory.sell;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;
import static com.raphaelframos.refii.chat.ConstantsUtils.RATING_TYPE_CHAT;

public class EndSell implements Chat {
    @Override
    public String text() {
        return "Obrigado, até o próximo!";
    }

    @Override
    public int position() {
        return 3;
    }

    @Override
    public int nextPosition() {
        return 4;
    }

    @Override
    public boolean isValid(String value) {
        try{
            int rating = Integer.parseInt(value);
            if(rating >= 1 && rating <= 5){
                return true;
            }else{
                throw new NumberFormatException("Número inválido");
            }
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public int type() {
        return NAME_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse;
        if(isValid(value)){
            chatResponse = new ChatResponse(nextPosition(), text(), type());
        }else{
            chatResponse = new ChatResponse(position(), RATING_CHAT, RATING_TYPE_CHAT);
        }
        return chatResponse;
    }
}