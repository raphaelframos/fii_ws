package com.raphaelframos.refii.chat.factory.sell;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

import static com.raphaelframos.refii.chat.ConstantsUtils.*;

public class AmountSell implements Chat {

    private int amount = 0;
    private String symbol;

    public AmountSell(int amount, String symbol) {
        setAmount(amount);
        setSymbol(symbol);
    }

    public AmountSell(int amount) {
        setAmount(amount);
    }

    @Override
    public String text() {
        return "Qual preço?";
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
        int result;
        try{
            result = Integer.parseInt(value);
        }catch (Exception e){
            result = 0;
        }
        return result < getAmount();
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
            chatResponse = new ChatResponse(position(), "Você possui " + getAmount() + " cotas do " + getSymbol() + ". Deseja vender quantas?", NAME_TYPE_CHAT);
        }
        return chatResponse;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
