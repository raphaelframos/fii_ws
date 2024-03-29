package com.raphaelframos.refii.chat.factory.item;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class RatingChat implements Chat {
    @Override
    public String text() {
        return RATING_CHAT;
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
        double result;
        try{
            result = Double.parseDouble(value);
        }catch (Exception e){
            result = 0;
        }
        return result > 0;
    }

    @Override
    public int type() {
        return RATING_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse;
        if(isValid(value)){
            chatResponse = new ChatResponse(nextPosition(), text(), type());
        }else{
            chatResponse = new ChatResponse(position(), PRICE_CHAT, PRICE_TYPE_CHAT);
        }
        return chatResponse;
    }
}
