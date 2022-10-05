package com.raphaelframos.refii.profile;

import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refii/profiles")
public class ProfileController {

    @Autowired
    private ProfileService service;

    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @RequestMapping("/")
    public ResponseEntity<ChatResponse> create(@RequestParam("id") Long id,
            @RequestParam("value") String value, @RequestParam("position") int position){
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
        return ResponseEntity.ok(chatResponse);
    }
}
