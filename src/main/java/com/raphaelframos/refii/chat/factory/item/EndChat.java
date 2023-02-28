package com.raphaelframos.refii.chat.factory.item;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class EndChat implements Chat {
    @Override
    public String text() {
        return END_CHAT;
    }

    @Override
    public int position() {
        return 5;
    }

    @Override
    public int nextPosition() {
        return 10;
    }

    @Override
    public boolean isValid(String value) {
        return value.equalsIgnoreCase("Sim");
    }

    @Override
    public int type() {
        return END_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse;
        if(isValid(value)){
            chatResponse = new ChatResponse(1, NAME_CHAT, NAME_TYPE_CHAT);
        }else{
            chatResponse = new ChatResponse(nextPosition(), text(), type());
        }
        return chatResponse;
    }
}
