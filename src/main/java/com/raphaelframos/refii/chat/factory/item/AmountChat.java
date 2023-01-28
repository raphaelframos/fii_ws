package com.raphaelframos.refii.chat.factory.item;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class AmountChat implements Chat {

    @Override
    public String text() {
        return AMOUNT_CHAT;
    }

    @Override
    public int position() {
        return 1;
    }

    @Override
    public int nextPosition() {
        return 2;
    }

    @Override
    public boolean isValid(String value) {
        return !value.isEmpty();
    }

    @Override
    public int type() {
        return AMOUNT_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse;
        if(isValid(value)){
            chatResponse = new ChatResponse(nextPosition(), text(), type());
        }else{
            chatResponse = new ChatResponse(position(), NAME_CHAT, NAME_TYPE_CHAT);
        }
        return chatResponse;
    }
}
