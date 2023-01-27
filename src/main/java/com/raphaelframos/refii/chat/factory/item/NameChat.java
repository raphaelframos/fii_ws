package com.raphaelframos.refii.chat.factory.item;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class NameChat implements Chat {

    @Override
    public String text() {
        return NAME_CHAT;
    }

    @Override
    public int position() {
        return 0;
    }

    @Override
    public int nextPosition() {
        return 1;
    }

    @Override
    public boolean isValid(String value) {
        return true;
    }

    @Override
    public int type() {
        return NAME_TYPE_CHAT;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        return new ChatResponse(nextPosition(), text(), type());
    }
}
