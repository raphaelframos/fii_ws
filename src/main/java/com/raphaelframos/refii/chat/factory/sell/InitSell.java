package com.raphaelframos.refii.chat.factory.sell;

import com.raphaelframos.refii.chat.factory.Chat;
import com.raphaelframos.refii.common.model.ChatResponse;

public class InitSell implements Chat {

    private int amount = 0;
    private String symbol;

    public InitSell(int amount, String symbol) {
        setAmount(amount);
        setSymbol(symbol);
    }

    @Override
    public String text() {
        return "Você possui " + getAmount() + " cotas do " + getSymbol() + ". Deseja vender quantas?";
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
        return 0;
    }

    @Override
    public ChatResponse getChatResponse(String value) {
        ChatResponse chatResponse = new ChatResponse(nextPosition(), text(), type());
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
