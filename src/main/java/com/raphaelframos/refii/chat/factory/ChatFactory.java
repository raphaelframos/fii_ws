package com.raphaelframos.refii.chat.factory;

import com.raphaelframos.refii.chat.factory.item.*;

public class ChatFactory {

    public static final int NAME = 0;
    public static final int AMOUNT = 1;
    public static final int PRICE = 2;
    public static final int RATING = 3;
    public static final int RELOAD = 4;
    public static final int END = 5;

    public static Chat get(String value, int position) {
        Chat chat;
        switch (position){
            case NAME:
                chat = new NameChat();
                break;
            case AMOUNT:
                chat = new AmountChat();
                break;
            case PRICE:
                chat = new PriceChat();
                break;
            case RATING:
                chat = new RatingChat();
                break;
            case RELOAD:
                chat = new ReloadChat();
                break;
            case END:
                chat = new EndChat();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + position);
        }
        return chat;
    }
}
