package com.raphaelframos.refii.profile;

import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    public Profile findBy(Long id) {
        return new Profile();
    }

    public ChatResponse create(Long id, String value, int position) {
        ChatResponse chatResponse = new ChatResponse();
        if(position == 0){
            ++position;
            chatResponse.setText("Olá, meu nome é Joe e vou ser seu assistente financeiro. Para comecar nossa história, qual é o seu nome?");
        }else if(position == 1){
            if(value.isEmpty()){
                chatResponse.setText("Não me abandone! Qual seu nome?");
            }else{
                ++position;
                chatResponse.setText("Tudo bem, " + value + "! Me diga qual é sua profissão?");
            }
        }
        chatResponse.setPosition(position);
        return chatResponse;
    }
}
