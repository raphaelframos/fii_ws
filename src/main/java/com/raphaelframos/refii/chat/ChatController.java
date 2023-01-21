package com.raphaelframos.refii.chat;

import com.raphaelframos.refii.common.model.ChatResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("refii/chat/")
public class ChatController {

    @Autowired
    private final ChatService service;

    public ChatController(ChatService service) {
        this.service = service;
    }

    @RequestMapping("fund/{position}")
    public ChatResponse newFund(@PathVariable("position") int position,
            @RequestParam("message") String value
    ){
        return service.newFund(value, position);
    }
}
