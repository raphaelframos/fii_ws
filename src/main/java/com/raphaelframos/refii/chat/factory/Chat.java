package com.raphaelframos.refii.chat.factory;

import com.raphaelframos.refii.common.model.ChatResponse;

public interface Chat {
    String text();
    int position();
    int nextPosition();
    boolean isValid(String value);
    int type();
    ChatResponse getChatResponse(String value);
}
