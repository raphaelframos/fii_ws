package com.raphaelframos.refii.chat.factory.item;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class PriceChat implements Chat {
    @Override
    public String text() {
        return PRICE_CHAT;
    }

    @Override
    public int position() {
        return 2;
    }

    @Override
    public int nextPosition() {
        return 3;
    }

    @Override
    public boolean isValid(String value) {
        int result;
        try{
            result = Integer.parseInt(value);
        }catch (Exception e){
            result = 0;
        }
        return result > 0;
    }

    @Override
    public int type() {
        return PRICE_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse;
        if(isValid(value)){
            chatResponse = new ChatResponse(nextPosition(), text(), type());
        }else{
            chatResponse = new ChatResponse(position(), AMOUNT_CHAT, AMOUNT_TYPE_CHAT);
        }
        return chatResponse;
    }
}
